package com.fly.fankun.model.entity;

import java.util.Date;

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

    private Integer clickNumber;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamTypeId() {
        return examTypeId;
    }

    public void setExamTypeId(Integer examTypeId) {
        this.examTypeId = examTypeId;
    }

    public String getExamUrl() {
        return examUrl;
    }

    public void setExamUrl(String examUrl) {
        this.examUrl = examUrl;
    }

    public String getExamTipsTitle() {
        return examTipsTitle;
    }

    public void setExamTipsTitle(String examTipsTitle) {
        this.examTipsTitle = examTipsTitle;
    }

    public String getExamComment() {
        return examComment;
    }

    public void setExamComment(String examComment) {
        this.examComment = examComment;
    }

    public Date getSignupBegintime() {
        return signupBegintime;
    }

    public void setSignupBegintime(Date signupBegintime) {
        this.signupBegintime = signupBegintime;
    }

    public Date getSignupEndtime() {
        return signupEndtime;
    }

    public void setSignupEndtime(Date signupEndtime) {
        this.signupEndtime = signupEndtime;
    }

    public Date getExamBegintime() {
        return examBegintime;
    }

    public void setExamBegintime(Date examBegintime) {
        this.examBegintime = examBegintime;
    }

    public Date getExamEndtime() {
        return examEndtime;
    }

    public void setExamEndtime(Date examEndtime) {
        this.examEndtime = examEndtime;
    }

    public Integer getClickNumber() {
        return clickNumber;
    }

    public void setClickNumber(Integer clickNumber) {
        this.clickNumber = clickNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}