package com.fly.fankun.mapper;

import com.fly.fankun.model.entity.ExamHot;

public interface ExamHotMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamHot record);

    int insertSelective(ExamHot record);

    ExamHot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamHot record);

    int updateByPrimaryKey(ExamHot record);
}