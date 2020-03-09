package com.fly.fankun.model.vo.outVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Title:
 * @author: fly
 * @date: 2020-3-9
 */
@Data
public class TutorialOutputVo {
    private Integer id;
    @ApiModelProperty("教材/真题名称")
    private String tutorialName;
    @ApiModelProperty("教材/真题出处")
    private String tutorialPress;
    @ApiModelProperty("教材简介")
    private String tutorialSynopsis;
    @ApiModelProperty("教材价格")
    private BigDecimal tutorialPrice;
    @ApiModelProperty("教材销量")
    private Integer tutorialSellNumber;
    @ApiModelProperty("教材描述")
    private String tutorialComment;
    @ApiModelProperty("教材种类：0：实体教材；1：线上真题")
    private Integer tutorialType;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("封面")
    private String tutorialPicture;

}
