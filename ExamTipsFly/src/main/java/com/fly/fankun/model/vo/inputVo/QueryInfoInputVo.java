package com.fly.fankun.model.vo.inputVo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Title:模糊查询+分页
 * @author: fly
 * @date: 2020-3-5
 */
@Data
public class QueryInfoInputVo {

    @NotBlank(message = "参数不能为空")
    private Integer deleted;

    @ApiModelProperty("模糊查询字符")
    private String str;

    @ApiModelProperty("当前页")
    private Integer pageNum;

    @ApiModelProperty("每页大小")
    private Integer pageSize;
}
