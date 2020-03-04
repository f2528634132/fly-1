package com.fly.fankun.model.vo.outVo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Title:
 * @author: fly
 * @date: 2020-3-4
 */
@Data
@ToString
@ApiModel(value = "数据分析页面返回对象")
public class ExamReportOutVo {
    private String examTipsTitle;
    private Integer sigUpNum;
    public ExamReportOutVo(String examTipsTitle,Integer sigUpNum){
        this.examTipsTitle=examTipsTitle;
        this.sigUpNum=sigUpNum;
    }
}
