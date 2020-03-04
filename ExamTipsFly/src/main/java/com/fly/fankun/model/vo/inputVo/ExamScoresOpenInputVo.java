package com.fly.fankun.model.vo.inputVo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Title:
 * @author: fly
 * @date: 2020/3/5
 */
@Data
public class ExamScoresOpenInputVo implements Serializable {
    private static final long serialVersionUID= 3679828060195000081L;

    @ApiModelProperty("主键id,修改时传入")
    @NotNull(message = "id不能为空")
    private Integer id;
    @ApiModelProperty("成绩公布时间")
    private Date examScoresTime;
    @ApiModelProperty("成绩公布说明")
    private String examScoresComment;
    @ApiModelProperty("成绩公布网址")
    private String examScoresUrl;
}
