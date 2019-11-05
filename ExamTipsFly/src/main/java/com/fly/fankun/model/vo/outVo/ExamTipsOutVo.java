package com.fly.fankun.model.vo.outVo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
//@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
@ApiModel(value = "考试信息编辑显示对象")
public class ExamTipsOutVo implements Serializable {
    private static final long serialVersionUID = -4902230193326317453L;
    @ApiModelProperty("主键id,修改时传入")
    private Integer id;


    @ApiModelProperty("考试类型id")
    @NotNull(message = "考试类型不能为空")
    private Integer examTypeId;

    @ApiModelProperty("考试网址url")
    @NotNull(message = "考试网址url不能为空")
    private String examUrl;

    @ApiModelProperty("考试信息标题")
    @NotNull(message = "考试信息标题不能为空")
    private String examTipsTitle;

    @ApiModelProperty("考试信息内容")
    @NotNull(message = "考试信息内容不能为空")
    private String examComment;

    @ApiModelProperty("报名开始时间")
    @NotNull(message = "报名开始时间不能为空")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date signupBegintime;

    @ApiModelProperty("报名结束时间")
    @NotNull(message = "报名结束时间不能为空")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date signupEndtime;

    @ApiModelProperty("考试开始时间")
    @NotNull(message = "考试开始时间不能为空")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date examBegintime;

    @ApiModelProperty("考试结束时间")
    @NotNull(message = "考试结束时间不能为空")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date examEndtime;

    private Integer clickNumber;

}