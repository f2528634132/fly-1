package com.fly.fankun.controller;

import com.fly.fankun.model.vo.outVo.ExamReportOutVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title:
 * @author: fly
 * @date: 2020/3/4
 */
@RestController
@Slf4j
@RequestMapping("/reports")
@Api(value = "数据分析", tags = "数据分析")
public class ReportController extends BaseController {
    @RequestMapping("/groupByExam")
    public @ResponseBody List<ExamReportOutVo> groupByExam(){
        ExamReportOutVo company1=new ExamReportOutVo("中小学教师资格考试",97);
        ExamReportOutVo company2=new ExamReportOutVo("计算机技术考试",88);
        ExamReportOutVo company3=new ExamReportOutVo("研究生考试",57);
        ExamReportOutVo company4=new ExamReportOutVo("CET-6",73);
        ExamReportOutVo company5=new ExamReportOutVo("医护英语水平考试(METS)",22);
        List<ExamReportOutVo> list=new ArrayList<>();
        list.add(company1);
        list.add(company2);
        list.add(company3);
        list.add(company4);
        list.add(company5);
        return list;
    }
}
