package com.fly.fankun.dict;

/**
 * @Title: 用户认证类型
 * @author: fly
 * @date: 2019/4/26
 */
public enum AccountAuthTypeEnum {
    CONSUMER(0, "前端用户"),
    SERVICE(1, "管理账户");
    private Integer code;
    private String name;

    AccountAuthTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
