package com.fly.fankun.mapper;

import com.fly.fankun.model.entity.Institution;
import java.util.List;

public interface InstitutionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Institution record);

    int insertSelective(Institution record);

    Institution selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Institution record);

    int updateByPrimaryKey(Institution record);

    List<Institution> list();
}