package com.fly.fankun.controller;

import com.fly.fankun.globals.AuthConstant;
import com.fly.fankun.globals.CacheConsts;
import com.fly.fankun.help.JwtHelper;
import com.fly.fankun.help.RedisHelp;
import com.fly.fankun.model.result.BaseResult;
import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.vo.inputVo.AdminInputVo;
import com.fly.fankun.model.vo.inputVo.PersonInputVo;
import com.fly.fankun.model.vo.outVo.AdminOutVo;
import com.fly.fankun.model.vo.outVo.AuthOutVo;
import com.fly.fankun.service.AdminService;
import com.fly.fankun.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Admin controller.
 *
 * @Title: 登录注册控制层
 * @author: fly
 * @date: 2019 /10/14
 */
@RestController
@Slf4j
@RequestMapping("/auth")
@Api(value = "登录注册", tags = "登录注册")
public class AuthController extends BaseController{

    @Autowired
    private AuthService authService;
    @Autowired
    JwtHelper jwtHelper;
    @Autowired
    RedisHelp redisHelp;

    @GetMapping("/login")
    @ApiOperation(value = "用户登录")
    public BaseResult login(@RequestParam String userName ,@RequestParam String passWord,@RequestParam Integer type) {
        AuthOutVo vo =authService.login(userName,passWord,type);
        vo.setType(type);
        return BaseResult.success(vo);
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public BaseResult register(@RequestBody PersonInputVo personInputVo) {
        authService.register(personInputVo);
        return BaseResult.success("注册成功");
    }

    @GetMapping("/logout")
    public BaseResult logout() {
        //清除token
        String token = this.getRequest().getHeader(AuthConstant.HEADER_NAME_TOKEN_REQUEST);
        if(StringUtils.isNotBlank(token)){
            redisHelp.del("JWT-SESSION-" + jwtHelper.getJwtIdByToken(token));
        }
        return BaseResult.success();
    }
}
