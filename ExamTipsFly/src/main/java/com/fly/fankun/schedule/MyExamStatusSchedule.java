package com.fly.fankun.schedule;

import com.fly.fankun.globals.GlobalConstans;
import com.fly.fankun.mapper.ExamTipsMapper;
import com.fly.fankun.mapper.MyExamMapper;
import com.fly.fankun.model.entity.MyExam;
import com.fly.fankun.model.vo.outVo.ExamTipsOutVo;
import com.google.common.collect.Lists;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: 考试/报名状态定时任务修改
 * @author: fan.kun
 * @date: 2019/10/18
 */
@Component
@Slf4j
public class MyExamStatusSchedule {

    @Autowired
    private MyExamMapper myExamMapper;

    @Autowired
    private ExamTipsMapper examTipsMapper;

    private static final Integer MY_EXAM_STATUS_2 =2;
    private static final Integer MY_EXAM_STATUS_3 =3;
    private static final Integer MY_EXAM_STATUS_5 =5;

    /**
     * 定时任务每天定时更新我的考试状态 10点整
     *
     */
    @Async
    @Scheduled(cron = "0 0 10 * * ? ")
    @Transactional(rollbackFor = Exception.class)
    public void myExamStatusSchedule() {
        //查询所有考试
        List<ExamTipsOutVo> list = examTipsMapper.list(GlobalConstans.ZERO);
        Date dateNow = new Date();
        List<MyExam> updateList = Lists.newArrayList();
        for (ExamTipsOutVo examTipsOutVo:list) {
//        获取所有加入的考试
            List<MyExam> myExamList = myExamMapper.queryByExamId(Lists.newArrayList(examTipsOutVo.getId()));
            if(CollectionUtils.isNotEmpty(myExamList)){
                for (MyExam myExam:myExamList ) {
                    //所有未报名的 如果报名结束时间大于今天则修改未报名已过期
                    MyExam updateExam = new MyExam();
                    updateExam.setId(myExam.getId());
                    if(GlobalConstans.ZERO.equals(myExam.getStatus()) && dateNow.after(examTipsOutVo.getSignupEndtime())){
                        updateExam.setStatus(MY_EXAM_STATUS_2);
                        updateList.add(updateExam);
                    }
                    //所有待考试的 如果考试结束时间大于今天则修改未考试已过期
                    else if (MY_EXAM_STATUS_3.equals(myExam.getStatus()) && dateNow.after(examTipsOutVo.getExamEndtime())){
                        updateExam.setStatus(MY_EXAM_STATUS_5);
                        updateList.add(updateExam);
                    }
                }
            }
        }
        myExamMapper.updateBatchStatus(updateList);
    }
}
