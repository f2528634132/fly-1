package com.fly.fankun.model.vo.outVo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

/**
 * @Title:
 * @author: fly
 * @date:
 */
@Data
public class ExamScoresOutVo {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("考试名称")
    private String examTipsTitle;

    @ApiModelProperty("成绩公布时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date examScoresTime;

    @ApiModelProperty("成绩公布网址")
    private String examScoresUrl;

    @ApiModelProperty("成绩公布说明")
    private String examScoresComment;

}
