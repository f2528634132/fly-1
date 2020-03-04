package com.fly.fankun.model.vo.outVo;

import lombok.Data;

/**
 * @Title:
 * @author: fan.kun
 * @date: 2020/3/4
 */
@Data
public class ExamStatisticsOutVo {
    /**
     * 考试类型id
     */
    private Integer  id;
    /**
     * 考试类型名称
     */
    private String examTypeName;
    /**
     * 点击树
     */
    private Long clickNum = 0L;
    /**
     * 收藏数
     */
    private Long collectNum= 0L;
    /**
     * 已报名人数
     */
    private Long personNum= 0L;
    /**
     * 已考试人数
     */
    private Long hasExamNum= 0L;
    /**
     * 未考试人数
     */
    private Long noExamNum= 0L;
}
