package com.fly.fankun.filter;

import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSON;
import com.fly.fankun.exception.BizzException;
import com.fly.fankun.globals.AuthConstant;
import com.fly.fankun.help.JwtHelper;
import com.fly.fankun.model.result.BaseResult;
import com.fly.fankun.model.result.ResultStatus;
import com.fly.fankun.model.vo.TokenUserVo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;

/**
 * @Title:
 * @author: fly
 * @date: 2019/11/1
 */
@Slf4j
@WebFilter(filterName = "LoginFilter",urlPatterns = {"/*"})
public class LoginFilter implements Filter {

    @Autowired
    JwtHelper jwtHelper;
    //免登录就可访问的路径(比如:注册,登录,注册页面上的一些获取数据等)
    String[] includeUrls = new String[]{"/swagger-ui.html","/webjars/.*","/swagger-resources/.*","/swagger-resources","/v2/api-docs/.*","/v2/api-docs","/reports/groupByExam",
            "/examTips/.*", "/auth/.*","/file/.*","/static/.*","/institution/.*","/tutorial/.*"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //判断当前url是否需要校验token
        //当前请求的url
        String uri = request.getRequestURI();
        boolean needFilter = isNeedFilter(uri);
        //不需要过滤直接传给下一个过滤器
        if (!needFilter) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //从header中获取token
        String token = request.getHeader(AuthConstant.HEADER_NAME_TOKEN_REQUEST);
        //如果为空则校验失败
        if(StringUtils.isBlank(token)){
//            throw  new BizzException(ResultStatus.UNAUTHORIZED.message);
            onLoginFailure(response, ResultStatus.UNAUTHORIZED);
            return;
        }
        //校验token
        boolean verifyToken = jwtHelper.verifyEnterpriseToken(token);
        if(!verifyToken){
//            throw  new BizzException(ResultStatus.UNAUTHORIZED.message);
            onLoginFailure(response, ResultStatus.UNAUTHORIZED);
            return;
        }
        TokenUserVo tokenUserVo = jwtHelper.getUserInfoByToken(token);
        request.setAttribute(AuthConstant.HEADER_NAME_TOKEN_RESPONSE, tokenUserVo);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }


    /**
     * 登陆失败
     *
     * @param resp
     * @param errorCode
     */
    private void onLoginFailure(ServletResponse resp, ResultStatus errorCode) {
        try {
            log.error(">>>>>>认证失败<<<<<<<<");
            resp.setContentType("application/json;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.write(JSON.toJSONString(BaseResult.result(errorCode)));
            out.close();
        } catch (IOException e) {
            log.error("onLoginFailure",e.getMessage());
        }
    }

    /**
     * @Author: fly
     * @Description: 是否需要过滤
     * @Date: 2019-11-1
     * @param uri
     */
    private boolean isNeedFilter(String uri) {

        for (String includeUrl : includeUrls) {
            boolean match = ReUtil.isMatch(includeUrl, uri);
            if(match) {
                return false;
            }
        }

        return true;
    }
}