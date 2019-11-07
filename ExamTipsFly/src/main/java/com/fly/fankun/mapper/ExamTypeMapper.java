package com.fly.fankun.mapper;

import com.fly.fankun.model.entity.ExamType;
import com.fly.fankun.model.vo.outVo.ExamTypeOutVo;
import java.util.List;

public interface ExamTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamType record);

    int insertSelective(ExamType record);

    ExamType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamType record);

    int updateByPrimaryKey(ExamType record);

    List<ExamTypeOutVo> queryAll();
}