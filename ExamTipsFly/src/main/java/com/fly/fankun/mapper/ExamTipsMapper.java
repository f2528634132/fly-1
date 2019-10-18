package com.fly.fankun.mapper;

import com.fly.fankun.model.entity.ExamTips;
import com.fly.fankun.model.vo.outVo.ExamTipsOutVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamTipsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExamTips record);

    int insertSelective(ExamTips record);

    ExamTips selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExamTips record);

    int updateByPrimaryKey(ExamTips record);

    List<ExamTipsOutVo> list(@Param(value = "deleted") Integer deleted);
}