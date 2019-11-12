package com.fly.fankun.mapper;

import com.fly.fankun.model.entity.SysFunction;
import com.fly.fankun.model.vo.outVo.UserFunctionNodeOutVo;
import java.util.List;

public interface SysFunctionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysFunction record);

    int insertSelective(SysFunction record);

    SysFunction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysFunction record);

    int updateByPrimaryKey(SysFunction record);

    List<SysFunction> queryUserFunction(Integer userType);
}