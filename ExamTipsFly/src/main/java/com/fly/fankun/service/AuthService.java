package com.fly.fankun.service;

import com.fly.fankun.model.vo.inputVo.PersonInputVo;

/**
 * @Title:
 * @author: fan.kun
 * @date: 2019/10/15
 */
public interface AuthService {
    void register(PersonInputVo personInputVo);

    void login(String userName, String passWord, Integer type);
}
