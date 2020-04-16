package com.fly.fankun.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.fly.fankun.model.result.BaseResult;
import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.vo.inputVo.TutorialInputVo;
import com.fly.fankun.model.vo.outVo.TutorialOutputVo;
import com.fly.fankun.service.TutorialService;
import com.fly.fankun.util.IpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import sun.net.util.IPAddressUtil;

/**
 * @Title:
 * @author: fly
 * @date: 2020-3-8
 */
@RestController
@Slf4j
@RequestMapping("/tutorial")
@Api(value = "教材真题",tags = "教材真题")
public class TutorialController extends BaseController{

    @Autowired
    private TutorialService tutorialService;

    @PostMapping(value = "/edit",produces="application/json;charset=UTF-8")
    @ApiOperation(value = "插入/编辑教材真题")
    public BaseResult edit(@Valid @RequestBody TutorialInputVo tutorialInputVo){
        tutorialService.edit(tutorialInputVo);
        return BaseResult.success();
    }

    @GetMapping("/queryPageByEntity")
    @ApiOperation(value = "分页查询考试信息",response = TutorialInputVo.class)
    public BaseResult<PageBean<TutorialOutputVo>> queryPageBySelf(@ApiParam(value = "当前页",required = true) @RequestParam Integer pageNum, @RequestParam@ApiParam(value = "分页大小",required = true) Integer pageSize, Integer deleted) {
        PageBean<TutorialOutputVo> resp = tutorialService.queryPageByEntity(pageNum,pageSize,deleted);
        return BaseResult.success(resp);
    }

    @GetMapping("/queryPageByOnline")
    @ApiOperation(value = "分页查询考试信息",response = TutorialInputVo.class)
    public BaseResult<PageBean<TutorialOutputVo>> queryPageByThird(@ApiParam(value = "当前页",required = true) @RequestParam Integer pageNum,@RequestParam@ApiParam(value = "分页大小",required = true) Integer pageSize,Integer deleted) {
        PageBean<TutorialOutputVo> resp = tutorialService.queryPageByOnline(pageNum,pageSize,deleted);
        return BaseResult.success(resp);
    }
    @GetMapping("/test/getIp")
    @ApiOperation(value = "测试获取IP地址")
    public BaseResult<String> getIpAddress() {
        String ipAddrCatch = IpUtil.getIpAddrCatch(getRequest());
        String url = String.format("https://restapi.amap.com/v3/ip?ip=%s&key=689cff252ca2b1e28dc8b893362101e3",ipAddrCatch);
       String result = HttpUtil.get(url, null, 10 * 1000);
        return BaseResult.success(result);
    }


}
