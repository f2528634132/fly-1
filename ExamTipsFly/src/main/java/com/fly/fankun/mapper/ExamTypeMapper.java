package com.fly.fankun.dao;

import com.fly.fankun.model.entity.ExamType;

public interface ExamTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamType record);

    int insertSelective(ExamType record);

    ExamType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamType record);

    int updateByPrimaryKey(ExamType record);
}