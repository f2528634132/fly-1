package com.fly.fankun.model.vo.inputVo;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PersonInputVo {

    private Integer id;

    private String name;

    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    private Integer sex;

    private String phone;

    private Integer age;

    private String education;

    private String industry;

    private Integer employStatus;


}