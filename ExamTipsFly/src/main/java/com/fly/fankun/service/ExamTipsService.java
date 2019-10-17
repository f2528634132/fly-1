package com.fly.fankun.service;

import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.vo.inputVo.ExamTipsInputVo;
import com.fly.fankun.model.vo.outVo.ExamTipsOutVo;

/**
 * @Title:
 * @author: fan.kun
 * @date: 2019/10/15
 */
public interface ExamTipsService {
    ExamTipsOutVo editExamTips(ExamTipsInputVo examTipsInputVo);

    void editDeleted(Integer id, Integer deleted);

    PageBean<ExamTipsOutVo> queryPage(Integer deleted, Integer pageNum, Integer pageSize);
}
