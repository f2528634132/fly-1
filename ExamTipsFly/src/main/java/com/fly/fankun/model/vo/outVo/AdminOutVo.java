package com.fly.fankun.model.vo.outVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

/**
 * @Title: 管理人员用户页面返回对象
 * @author: fan.kun
 * @date: 2019/10/14
 */
@Data
@ToString
@ApiModel(value = "管理人员用户页面返回对象")
public class AdminOutVo implements Serializable {
    private static final long serialVersionUID = 1137043343549975277L;

    @ApiModelProperty("主键Id")
    private Integer id;
    @ApiModelProperty("系统？")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("手机号")
    private String phone;
}
