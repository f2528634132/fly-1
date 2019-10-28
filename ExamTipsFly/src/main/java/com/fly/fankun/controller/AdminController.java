package com.fly.fankun.controller;

import com.fly.fankun.model.result.BaseResult;
import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.result.PageObject;
import com.fly.fankun.model.vo.inputVo.AdminInputVo;
import com.fly.fankun.model.vo.outVo.AdminOutVo;
import com.fly.fankun.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Admin controller.
 *
 * @Title: 管理人员控制层
 * @author: fly
 * @date: 2019 /10/14
 */
@RestController
@Slf4j
@RequestMapping("/admin")
@Api(value = "后台人员管理", tags = "后台人员管理")
public class AdminController extends BaseController{

    @Autowired
    private AdminService adminService;
    /**
     * 分页查询后端用户
     *
     * @param pageNum the page num
     * @param pageSize the page size
     * @return base result
     */
    @GetMapping("/queryPage")
    @ApiOperation(value = "分页查询后端用户",response = AdminOutVo.class)
    public BaseResult<PageBean<AdminOutVo>> queryPage(@ApiParam(value = "当前页",required = true) @RequestParam Integer pageNum,@RequestParam@ApiParam(value = "分页大小",required = true) Integer pageSize) {
        PageBean<AdminOutVo> resp = adminService.queryPage(pageNum,pageSize);
        return BaseResult.success(resp);
    }
    @PostMapping("/editAdmin")
    @ApiOperation(value = "保存/修改用户",response = AdminOutVo.class)
    public BaseResult<AdminOutVo> editAdmin(@RequestBody AdminInputVo inputVo) {
        AdminOutVo resp = adminService.editAdmin(inputVo);
        return BaseResult.success(resp);
    }
    @GetMapping("/delAdmin")
    @ApiOperation(value = "删除用户")
    public BaseResult delAdmin(@RequestParam Integer id) {
       adminService.delAdmin(id);
        return BaseResult.success("删除成功");
    }
}
