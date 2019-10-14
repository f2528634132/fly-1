package com.fly.fankun.exception;

import com.fly.fankun.model.result.BaseResult;
import com.fly.fankun.model.result.ResultStatus;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Title: 统一异常处理
 *  当系统捕捉到下面异常时不会 在页面显示具体错误信息，界面友好
 * @author: fan.kun
 * @date: 2019/3/21
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandle {


    @ExceptionHandler(value = Exception.class)
    public BaseResult defaultExceptionHandler(HttpServletRequest req, Exception e) {

        log.error("系统异常，异常url: {}  \r\n错误信息：", req.getRequestURI(), e);

        return BaseResult.systemError();
    }

    @ExceptionHandler(value = ZPBaseException.class)
    public BaseResult handler(HttpServletRequest req, ZPBaseException e) {

        log.warn("业务异常, url: {}  \r\n错误信息：{}", req.getRequestURI(), e.getMessage());

        return BaseResult.result(e.getCode(), e.getMessage());
    }

    /**
     * 参数检验异常处理
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public BaseResult handler(HttpServletRequest req, Exception e) {


        BindingResult bindingResult = null;
        if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        } else if (e instanceof BindException) {
            bindingResult = ((BindException) e).getBindingResult();
        } else {
            return BaseResult.systemError();
        }

        FieldError fieldError = bindingResult.getFieldError();
        if (null == fieldError) {
            log.error("参数校验异常，异常url：{}\r\n错误信息：{}", req.getRequestURI(), e);
            return BaseResult.systemError();
        }

        log.warn("参数校验异常，异常url：{}\r\n错误信息：{}", req.getRequestURI(), fieldError.getDefaultMessage());

        return BaseResult.result(ResultStatus.BAD_REQUEST.code, fieldError.getDefaultMessage());
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public BaseResult handler(HttpServletRequest req, HttpMessageConversionException e) {

        log.error("请求异常, url: {}  \r\n错误信息：", req.getRequestURI(), e);

        return BaseResult.result(ResultStatus.BAD_REQUEST);
    }

    /**
     * 参数检验异常处理
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public BaseResult handler(HttpServletRequest req, MissingServletRequestParameterException e) {

        log.error("参数校验异常，异常url：{}\r\n错误信息：", req.getRequestURI(), e);

        return BaseResult.result(ResultStatus.BAD_REQUEST.code, "必填参数不能为空");
    }


    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public BaseResult handler(HttpServletRequest req, HttpRequestMethodNotSupportedException e) {

        log.error("不支持的请求方式[{}]，异常url：{}：", req.getMethod(),req.getRequestURI());

        return BaseResult.result(ResultStatus.BAD_REQUEST.code, "不支持的请求方式:" + req.getMethod());
    }

}
