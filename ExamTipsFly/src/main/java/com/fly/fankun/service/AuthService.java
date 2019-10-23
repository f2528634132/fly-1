package com.fly.fankun.service;

import com.fly.fankun.model.vo.inputVo.PersonInputVo;
import com.fly.fankun.model.vo.outVo.AuthOutVo;

/**
 * @Title:
 * @author: fan.kun
 * @date: 2019/10/15
 */
public interface AuthService {
    void register(PersonInputVo personInputVo);

    AuthOutVo login(String userName, String passWord, Integer type);
}
