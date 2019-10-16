package com.fly.fankun.mapper;

import com.fly.fankun.model.entity.ExamTips;
import com.fly.fankun.model.vo.outVo.ExamTipsOutVo;
import java.util.List;

public interface ExamTipsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamTips record);

    int insertSelective(ExamTips record);

    ExamTips selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamTips record);

    int updateByPrimaryKey(ExamTips record);

    List<ExamTipsOutVo> list();
}