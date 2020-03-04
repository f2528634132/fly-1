package com.fly.fankun.controller;

import com.fly.fankun.model.result.BaseResult;
import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.vo.inputVo.AdminInputVo;
import com.fly.fankun.model.vo.outVo.AdminOutVo;
import com.fly.fankun.model.vo.outVo.UserFunctionNodeOutVo;
import com.fly.fankun.service.AdminService;
import com.fly.fankun.service.SysFunctionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
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
 * @Title: 角色权限管理
 * @author: fly
 * @date: 2019 /10/14
 */
@RestController
@Slf4j
@RequestMapping("/role")
@Api(value = "角色权限管理", tags = "角色权限管理")
public class RoleController extends BaseController{

    @Autowired
    private SysFunctionService sysFunctionService;

    @GetMapping("/queryUserFunction")
    @ApiOperation(value = "查询用户角色权限")
    public BaseResult queryUserFunction() {
     List<UserFunctionNodeOutVo> resp =  sysFunctionService.queryUserFunction(this.getUserInfo());
        System.out.println(resp);
        return BaseResult.success(resp);
    }
}
