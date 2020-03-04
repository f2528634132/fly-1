package com.fly.fankun.controller;

import com.fly.fankun.globals.GlobalConstans;
import com.fly.fankun.model.result.BaseResult;
import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.vo.inputVo.AdminInputVo;
import com.fly.fankun.model.vo.inputVo.ExamTipsInputVo;
import com.fly.fankun.model.vo.outVo.AdminOutVo;
import com.fly.fankun.model.vo.outVo.ExamStatisticsOutVo;
import com.fly.fankun.model.vo.outVo.ExamTipsOutVo;
import com.fly.fankun.model.vo.outVo.ExamTypeOutVo;
import com.fly.fankun.service.AdminService;
import com.fly.fankun.service.ExamTipsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;
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
 * @Title: 考试信息控制层
 * @author: fly
 * @date: 2019 /10/14
 */
@RestController
@Slf4j
@RequestMapping("/examTips")
@Api(value = "考试信息管理", tags = "考试信息管理")
public class ExamTipsController extends BaseController{

    @Autowired
    private ExamTipsService examTipsService;

    @PostMapping(value = "/editExamTips",produces="application/json;charset=UTF-8")
    @ApiOperation(value = "编辑考试信息",response = ExamTipsOutVo.class)
    public BaseResult editExamTips(@Valid  @RequestBody ExamTipsInputVo examTipsInputVo) {
        ExamTipsOutVo resp=  examTipsService.editExamTips(examTipsInputVo);
        return BaseResult.success(resp);
    }

    @GetMapping("/editDeleted")
    @ApiOperation(value = "禁用/启用考试信息")
    public BaseResult editDeleted(@RequestParam Integer id,@RequestParam Integer deleted) {
        if(null == deleted || (!GlobalConstans.ZERO .equals(deleted) && !GlobalConstans.ONE .equals(deleted))){
            return BaseResult.fail();
        }
       examTipsService.editDeleted(id,deleted);
        return BaseResult.success("操作成功");
    }

    @GetMapping("/queryPage")
    @ApiOperation(value = "分页查询考试信息",response = ExamTipsOutVo.class)
    public BaseResult<PageBean<ExamTipsOutVo>> queryPage(@RequestParam(required = false) Integer deleted,@ApiParam(value = "当前页",required = true) @RequestParam Integer pageNum,@RequestParam@ApiParam(value = "分页大小",required = true) Integer pageSize) {
        PageBean<ExamTipsOutVo> resp = examTipsService.queryPage(deleted,pageNum,pageSize);
        return BaseResult.success(resp);
    }

    @GetMapping("/queryExam")
    @ApiOperation(value = "查询考试信息",response = ExamTipsOutVo.class)
    public BaseResult queryExam(@RequestParam@ApiParam(value = "考试ID",required = true) Integer examId) {
        ExamTipsOutVo resp = examTipsService.queryExam(examId);
        return BaseResult.success(resp);
    }

    //查询所有考试目录
    @GetMapping("/queryExamTypeList")
    @ApiOperation(value = "查询考试类目",response = ExamTypeOutVo.class)
    public BaseResult queryExamTypeList() {
        List<ExamTypeOutVo> resp = examTipsService.queryExamTypeList();
        return BaseResult.success(resp);
    }

    //查询所有考试目录
    @GetMapping("/queryExamType/statistics")
    @ApiOperation(value = "考试类目统计",response = ExamStatisticsOutVo.class)
    public BaseResult examStatistics() {
        List<ExamStatisticsOutVo> resp = examTipsService.examStatistics();
        return BaseResult.success(resp);
    }

}
