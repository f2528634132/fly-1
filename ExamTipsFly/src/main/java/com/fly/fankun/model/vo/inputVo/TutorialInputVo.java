package com.fly.fankun.model.vo.inputVo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Title:
 * @author: fly
 * @date:
 */
@Data
public class TutorialInputVo {
    private Integer id;
    private String tutorialName;
    private String tutorialPress;
    private String tutorialSynopsis;
    private BigDecimal tutorialPrice;
    private String tutorialComment;
    private Integer status;
    private String tutorialPicture;
    private Integer tutorialType;
}
