package com.fly.fankun.service;

import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.vo.inputVo.ExamScoresOpenInputVo;
import com.fly.fankun.model.vo.inputVo.ExamTipsInputVo;
import com.fly.fankun.model.vo.inputVo.QueryInfoInputVo;
import com.fly.fankun.model.vo.outVo.ExamStatisticsOutVo;
import com.fly.fankun.model.vo.outVo.ExamTipsOutVo;
import com.fly.fankun.model.vo.outVo.ExamTypeOutVo;
import java.util.List;

/**
 * @Title:
 * @author: fly
 * @date: 2019/10/15
 */
public interface ExamTipsService {
    ExamTipsOutVo editExamTips(ExamTipsInputVo examTipsInputVo);

    void editStatus(Integer id, Integer status);

    void editDeleted(Integer id, Integer deleted);

    ExamTipsOutVo queryExam(Integer id);

    PageBean<ExamTipsOutVo> queryPage(Integer deleted, Integer pageNum, Integer pageSize);

    PageBean<ExamTipsOutVo> queryPageByInfo(QueryInfoInputVo queryInfoInputVo);

    List<ExamTypeOutVo> queryExamTypeList();

    List<ExamStatisticsOutVo> examStatistics();

    void addExamScores(ExamScoresOpenInputVo examScoresOpenInputVo);

}
