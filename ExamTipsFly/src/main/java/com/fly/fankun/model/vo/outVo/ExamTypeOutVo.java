package com.fly.fankun.model.vo.outVo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

/**
 * @Title:
 * @author: fan.kun
 * @date: 2019/11/7
 */
@Data
@ToString
public class ExamTypeOutVo implements Serializable {
    private static final long serialVersionUID = -5005147173265331560L;
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("考试类目名称")
    private String examName;
}
