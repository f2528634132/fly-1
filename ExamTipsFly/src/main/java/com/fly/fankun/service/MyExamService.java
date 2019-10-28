package com.fly.fankun.service;

import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.vo.outVo.MyExamOutVo;
import java.util.List;

/**
 * @Title:
 * @author: fly
 * @date: 2019/10/17
 */
public interface MyExamService {

    void joinMyExam(Integer examId, Integer userId);

    List<MyExamOutVo> queryMyExam(Integer userId);

    void delMyExam(Integer id, Integer userId);

    void editMyExamStatus(Integer id, Integer userId, Integer status);

    PageBean<MyExamOutVo> queryPage(Integer deleted, Integer pageNum, Integer pageSize);

}
