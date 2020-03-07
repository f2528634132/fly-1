package com.fly.fankun.model.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Institution {
    private Integer id;

    private Integer examTypeId;

    private Integer courseType;

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

    private Date courserTime;

    private Integer institutionType;

    private String courseUrl;

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

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCoursePicture() {
        return coursePicture;
    }

    public void setCoursePicture(String coursePicture) {
        this.coursePicture = coursePicture;
    }

    public String getCourseComment() {
        return courseComment;
    }

    public void setCourseComment(String courseComment) {
        this.courseComment = courseComment;
    }

    public Integer getCreaterAdminId() {
        return createrAdminId;
    }

    public void setCreaterAdminId(Integer createrAdminId) {
        this.createrAdminId = createrAdminId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public BigDecimal getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(BigDecimal coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Date getCourserTime() {
        return courserTime;
    }

    public void setCourserTime(Date courserTime) {
        this.courserTime = courserTime;
    }

    public Integer getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(Integer institutionType) {
        this.institutionType = institutionType;
    }

    public String getCourseUrl() {
        return courseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        this.courseUrl = courseUrl;
    }
}