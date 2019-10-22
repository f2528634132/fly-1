package com.fly.fankun.controller;

import com.fly.fankun.model.result.BaseResult;
import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.vo.inputVo.AdminInputVo;
import com.fly.fankun.model.vo.inputVo.PersonInputVo;
import com.fly.fankun.model.vo.outVo.AdminOutVo;
import com.fly.fankun.service.AdminService;
import com.fly.fankun.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Admin controller.
 *
 * @Title: 登录注册控制层
 * @author: fly
 * @date: 2019 /10/14
 */
@RestController
@Slf4j
@RequestMapping("/auth")
@Api(value = "登录注册", tags = "登录注册")
public class AuthController extends BaseController{

    @Autowired
    private AuthService authService;


    @GetMapping("/login")
    @ApiOperation(value = "用户登录")
    public BaseResult login(@RequestParam String userName ,@RequestParam String passWord,@RequestParam Integer type) {
        authService.login(userName,passWord,type);
        return BaseResult.success("登录成功");
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public BaseResult register(@RequestBody PersonInputVo personInputVo) {
        authService.register(personInputVo);
        return BaseResult.success("注册成功");
    }
}
