package com.fly.fankun.model.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Title:
 * @author: fly
 * @date: 2020-3-8
 */
@Data
public class Tutorial {
    private Integer id;
    private String tutorialName;
    private String tutorialPress;
    private String tutorialSynopsis;
    private BigDecimal tutorialPrice;
    private Integer tutorialSellNumber;
    private String tutorialComment;
    private Integer status;
    private Integer createrAdminId;
    private Date createtime;
    private Date updatetime;
    private String tutorialPicture;
    private Integer deleted;
    private Integer tutorialType;
}
