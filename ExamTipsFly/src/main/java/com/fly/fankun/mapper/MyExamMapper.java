package com.fly.fankun.mapper;

import com.fly.fankun.model.entity.MyExam;
import com.fly.fankun.model.vo.outVo.MyExamOutVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyExamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyExam record);

    int insertSelective(MyExam record);

    MyExam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyExam record);

    int updateByPrimaryKey(MyExam record);

    List<MyExamOutVo> queryMyExam(Integer userId);

    MyExam queryByExamId(@Param(value = "examId") Integer examId,@Param(value = "personId") Integer personId);
}