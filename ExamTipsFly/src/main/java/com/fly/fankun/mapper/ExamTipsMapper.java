package com.fly.fankun.dao;

import com.fly.fankun.model.entity.ExamTips;

public interface ExamTipsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamTips record);

    int insertSelective(ExamTips record);

    ExamTips selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamTips record);

    int updateByPrimaryKey(ExamTips record);
}