package com.fly.fankun.service.impl;

import com.fly.fankun.mapper.RoleSysFunctionMapper;
import com.fly.fankun.mapper.SysFunctionMapper;
import com.fly.fankun.model.entity.SysFunction;
import com.fly.fankun.model.vo.TokenUserVo;
import com.fly.fankun.model.vo.outVo.UserFunctionNodeOutVo;
import com.fly.fankun.service.SysFunctionService;
import com.google.common.collect.Lists;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title:
 * @author: fan.kun
 * @date: 2019/11/12
 */
@Service
@Slf4j
public class SysFunctionServiceImpl implements SysFunctionService {
    @Autowired
    private SysFunctionMapper sysFunctionMapper;
    @Autowired
   private  RoleSysFunctionMapper roleSysFunctionMapper;

    @Override
    public List<UserFunctionNodeOutVo> queryUserFunction(TokenUserVo userInfo) {
        List<SysFunction> sysFunctions = sysFunctionMapper.queryUserFunction(userInfo.getUserType());

        return buildUserFunctionNodeOutVo(sysFunctions);
    }
    private List<UserFunctionNodeOutVo> buildUserFunctionNodeOutVo(List<SysFunction> enterpriseFunctions) {
        if (CollectionUtils.isEmpty(enterpriseFunctions)) {
            return null;
        }
        List<UserFunctionNodeOutVo> results = Lists.newArrayList();
        enterpriseFunctions.forEach(a -> {
            if (StringUtils.isBlank(a.getParentId()) && a.getFuncLevel().equals(1)) {
                UserFunctionNodeOutVo enterpriseAccountFunctionNodeResult = new UserFunctionNodeOutVo();
                enterpriseAccountFunctionNodeResult.setName(a.getFuncName()).setUrl(a.getFuncUrl()).setIcon(a.getIcon()).setType(a.getFuncType()).setChildNodes(Lists.newArrayList());
                List<UserFunctionNodeOutVo> childNodes = Lists.newArrayList();
                enterpriseFunctions.forEach(b -> {
                    if (StringUtils.equals(b.getParentId(), String.valueOf(a.getId()))) {
                        UserFunctionNodeOutVo childNode = new UserFunctionNodeOutVo();
                        childNode.setUrl(b.getFuncUrl()).setName(b.getFuncName()).setType(b.getFuncType());
                        childNodes.add(childNode);
                        enterpriseAccountFunctionNodeResult.setChildNodes(childNodes);
                    }
                });
                results.add(enterpriseAccountFunctionNodeResult);
            }
        });
        return results;
    }
}
