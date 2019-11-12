package com.fly.fankun.service;

import com.fly.fankun.model.vo.TokenUserVo;
import com.fly.fankun.model.vo.outVo.UserFunctionNodeOutVo;
import java.util.List;

/**
 * @Title:
 * @author: fan.kun
 * @date: 2019/11/12
 */
public interface SysFunctionService {
    List<UserFunctionNodeOutVo> queryUserFunction(TokenUserVo userInfo);
}
