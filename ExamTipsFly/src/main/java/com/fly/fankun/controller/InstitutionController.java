package com.fly.fankun.controller;

import com.fly.fankun.globals.GlobalConstans;
import com.fly.fankun.model.result.BaseResult;
import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.vo.inputVo.ExamScoresOpenInputVo;
import com.fly.fankun.model.vo.inputVo.ExamTipsInputVo;
import com.fly.fankun.model.vo.inputVo.InstitutionFormInputVo;
import com.fly.fankun.model.vo.inputVo.QueryInfoInputVo;
import com.fly.fankun.model.vo.outVo.ExamStatisticsOutVo;
import com.fly.fankun.model.vo.outVo.ExamTipsOutVo;
import com.fly.fankun.model.vo.outVo.ExamTypeOutVo;
import com.fly.fankun.model.vo.outVo.InstitutionFormOutVo;
import com.fly.fankun.service.ExamTipsService;
import com.fly.fankun.service.InstitutionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Admin controller.
 *
 * @Title: 课程控制层
 * @author: fly
 * @date: 2019 /10/14
 */
@RestController
@Slf4j
@RequestMapping("/institution")
@Api(value = "课程管理", tags = "课程管理")
public class InstitutionController extends BaseController{

    @Autowired
    private InstitutionService institutionService;

    @PostMapping(value = "/edit",produces="application/json;charset=UTF-8")
    @ApiOperation(value = "编辑课程信息")
    public BaseResult edit(@Valid  @RequestBody InstitutionFormInputVo inputVo) {
        institutionService.edit(inputVo);
        return BaseResult.success();
    }


    @PutMapping("/status")
    @ApiOperation(value = "禁用/启用")
    public BaseResult editStatus(@RequestParam Integer id,@RequestParam Integer status) {
        if(null == status || (!GlobalConstans.ZERO .equals(status) && !GlobalConstans.ONE .equals(status))){
            return BaseResult.fail();
        }
        institutionService.editStatus(id,status);
        return BaseResult.success("操作成功");
    }


    @GetMapping("/queryPage")
    @ApiOperation(value = "分页查询考试信息",response = InstitutionFormOutVo.class)
    public BaseResult<PageBean<InstitutionFormOutVo>> queryPage(@ApiParam(value = "当前页",required = true) @RequestParam Integer pageNum,@RequestParam@ApiParam(value = "分页大小",required = true) Integer pageSize) {
        PageBean<InstitutionFormOutVo> resp = institutionService.queryPage(pageNum,pageSize);
        return BaseResult.success(resp);
    }



}
