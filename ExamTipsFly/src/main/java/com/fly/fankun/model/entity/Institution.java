package com.fly.fankun.model.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class Institution {
    private Integer id;

    private Integer examTypeId;

    private String courseType;

    private String courseTitle;

    private String coursePicture;

    private String courseComment;

    private Integer createrAdminId;

    private Integer status;

    private Date createtime;

    private Date updatetime;

    private Integer deleted;

    private String institution;

    private BigDecimal coursePrice;

    private Date courseTime;

    private Integer institutionType;

    private String courseUrl;

}