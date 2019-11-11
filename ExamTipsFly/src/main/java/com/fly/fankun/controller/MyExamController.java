package com.fly.fankun.controller;

import com.fly.fankun.model.result.BaseResult;
import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.vo.outVo.ExamTipsOutVo;
import com.fly.fankun.model.vo.outVo.MyExamOutVo;
import com.fly.fankun.service.MyExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Admin controller.
 *
 * @Title: 我的考试控制层
 * @author: fly
 * @date: 2019 /10/14
 */
@RestController
@Slf4j
@RequestMapping("/myExam")
@Api(value = "我的考试管理", tags = "我的考试管理")
public class MyExamController extends BaseController{

    @Autowired
    private MyExamService myExamService;

    @PostMapping("/joinMyExam")
    @ApiOperation(value = "添加考试信息")
    public BaseResult joinMyExam(@RequestParam Integer examId) {
         myExamService.joinMyExam(examId,this.getUserId());
        return BaseResult.success("添加成功");
    }

    @PostMapping("/queryMyExam")
    @ApiOperation(value = "查询我的考试信息",response = ExamTipsOutVo.class)
    public BaseResult queryMyExam() {
//       String a="待考试";
      List<MyExamOutVo> resp = myExamService.queryMyExam(this.getUserId());
      //  MyExamOutVo resp = myExamService.queryMyExam(this.getUserId());
//       if(0==resp.getStatus())
//           Integer.toString(resp.status);
//           Integer.toString(resp.setStatus());
       return BaseResult.success(resp);
    }

    @PostMapping("/delMyExam")
    @ApiOperation(value = "删除考试信息")
    public BaseResult delMyExam(@RequestParam Integer id) {
        myExamService.delMyExam(id,this.getUserId());
        return BaseResult.success("删除成功");
    }

    @PostMapping("/editMyExamStatus")
    @ApiOperation(value = "编辑考试状态")
    public BaseResult editMyExamStatus(@RequestParam Integer id,@RequestParam Integer status) {
        myExamService.editMyExamStatus(id,this.getUserId(),status);
        return BaseResult.success("操作成功");
    }

    @GetMapping("/queryPage")
    @ApiOperation(value = "分页查询我的报名",response = MyExamOutVo.class)
    public BaseResult<PageBean<MyExamOutVo>> queryPage(@ApiParam(value = "当前页", required = true)
                                                       @RequestParam Integer pageNum,
                                                       @RequestParam @ApiParam(value = "分页大小", required = true) Integer pageSize,
                                                       Integer deleted) {
        PageBean<MyExamOutVo> resp = myExamService.queryPage(deleted,pageNum,pageSize,this.getUserId());
        return BaseResult.success(resp);
    }

    @GetMapping("/MyCollectExam")
    @ApiOperation(value = "分页查询考试收藏",response = MyExamOutVo.class)
    public BaseResult<PageBean<MyExamOutVo>> MyCollectExam(@ApiParam(value = "当前页", required = true) @RequestParam Integer pageNum, @RequestParam @ApiParam(value = "分页大小", required = true) Integer pageSize, Integer deleted,Integer status) {
        PageBean<MyExamOutVo> resp = myExamService.MyCollectExam(deleted,pageNum,pageSize,this.getUserId(),status);
        return BaseResult.success(resp);
    }

    @GetMapping("/ExamScores")
    @ApiOperation(value = "分页查询考试公布",response = MyExamOutVo.class)
    public BaseResult<PageBean<MyExamOutVo>> ExamScores(@ApiParam(value = "当前页", required = true) @RequestParam Integer pageNum, @RequestParam @ApiParam(value = "分页大小", required = true) Integer pageSize, Integer deleted,Integer status) {
        PageBean<MyExamOutVo> resp = myExamService.ExamScores(deleted,pageNum,pageSize,this.getUserId(),status);
        return BaseResult.success(resp);
    }
}
