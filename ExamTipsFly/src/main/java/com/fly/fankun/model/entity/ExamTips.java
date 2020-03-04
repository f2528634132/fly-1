package com.fly.fankun.model.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ExamTips {
    private Integer id;

    private Integer examTypeId;

    private String examUrl;

    private String examTipsTitle;

    private String examComment;

    private Date signupBegintime;

    private Date signupEndtime;

    private Date examBegintime;

    private Date examEndtime;

    private Date examScorestime;

    private Integer clickNumber;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    private Integer status;

    private Date examScoresTime;

    private String examScoresComment;

    private String examScoresUrl;

}