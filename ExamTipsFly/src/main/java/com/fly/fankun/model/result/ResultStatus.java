/*
 * Copyright (C), 2015-2019
 * FileName: ResultStatus
 * Author:   b 2019/5/8 17:30uke
 * Date:     2019/5/8 17:30
 * Description: 服务状态码
 */
package com.fly.fankun.model.result;

/**
 * 服务状态码
 * @author fly
 * @date 2019/5/8
 */
public enum ResultStatus {

    SUCCESS(200,"请求成功"),

    BAD_REQUEST(400,"参数异常"),
    UNAUTHORIZED(401,"认证失败"),
    LOGIN_FAIL(402,"登陆失败，用户名密码或账号错误"),
    UN_LOGIN(403,"用户未登录"),
    URL_NOT_FOUND(404,"接口不存在"),
    NO_PERMISSION(405,"权限不足"),
    NO_USERNAME_PHONE_EMAIL(406,"账号/手机号未注册"),

    FAIL(490,"业务异常"),
    SYSTEM_ERROR(500,"系统繁忙，请稍后再试"),
    ;

    public int code;
    public String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultStatus getByCode(int code){

        ResultStatus[] values = values();

        for (ResultStatus value : values) {

            if (code == value.code){
                return value;
            }
        }

        return FAIL;
    }
}
