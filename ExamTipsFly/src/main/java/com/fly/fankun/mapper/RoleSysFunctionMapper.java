package com.fly.fankun.mapper;

import com.fly.fankun.model.entity.RoleSysFunction;

public interface RoleSysFunctionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleSysFunction record);

    int insertSelective(RoleSysFunction record);

    RoleSysFunction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleSysFunction record);

    int updateByPrimaryKey(RoleSysFunction record);
}