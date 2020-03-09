package com.fly.fankun.mapper;

import com.fly.fankun.model.entity.Tutorial;
import com.fly.fankun.model.vo.outVo.TutorialOutputVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title:
 * @author: fly
 * @date:
 */
public interface TutorialMapper {

    int insertSelective(Tutorial tutorial);

    int updateByPrimaryKey(Tutorial tutorial);

    List<TutorialOutputVo> tutorialList(@Param(value = "deleted") Integer deleted,@Param(value="tutorialType") Integer i);
}
