package com.fly.fankun.model.vo.outVo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fly.fankun.model.entity.ExamTips;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * @Title:
 * @author: fly
 * @date: 2019/10/17
 */
@Data
public class MyExamOutVo implements Serializable {
    private static final long serialVersionUID = -3218505432269217953L;

    @ApiModelProperty("考试信息(修改为outVO)")
    private ExamTips examTipsOutVo;
    @ApiModelProperty("主键id")
    private Integer id;
    /**
     * 0:未报名，1：已报名，2：已考试，3：已过期，4：未考试
     */
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("加入时间")
    private Date createTime;
    private Integer personId;
    private Integer examId;

    private String examTipsTitle;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date signupBegintime;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date  signupEndtime;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date examBegintime;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date examEndtime;

    private String examUrl;
}
