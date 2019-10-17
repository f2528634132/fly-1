package com.fly.fankun.model.vo.outVo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * @Title:
 * @author: fan.kun
 * @date: 2019/10/17
 */
@Data
public class MyExamOutVo implements Serializable {
    private static final long serialVersionUID = -3218505432269217953L;

    @ApiModelProperty("考试信息")
    private List<ExamTipsOutVo> examTipsOutVoList;
    @ApiModelProperty("主键id")
    private Integer id;
    /**
     * 0:未报名，1：已报名，2：已过期，3：待考试，4：已考试，5：未考试
     */
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("加入时间")
    private Date createtime;
}
