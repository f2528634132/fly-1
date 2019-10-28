package com.fly.fankun.service.impl;

import com.fly.fankun.exception.BizzException;
import com.fly.fankun.globals.GlobalConstans;
import com.fly.fankun.mapper.ExamTipsMapper;
import com.fly.fankun.mapper.MyExamMapper;
import com.fly.fankun.model.entity.ExamTips;
import com.fly.fankun.model.entity.MyExam;
import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.vo.outVo.MyExamOutVo;
import com.fly.fankun.service.MyExamService;
import com.fly.fankun.util.DateUtil;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title:
 * @author: fly
 * @date: 2019/10/17
 */
@Service
@Slf4j
public class MyExamServiceImpl implements MyExamService {

    @Autowired
    private MyExamMapper myExamMapper;

    @Autowired
    private ExamTipsMapper examTipsMapper;
    /**
     * 我的考试 状态 status 4
     */
    private static final Integer MY_EXAM_STATUS_4 =4;
    private static final Integer MY_EXAM_STATUS_3 =3;

    @Override
    public PageBean<MyExamOutVo> queryPage(Integer deleted, Integer pageNum, Integer pageSize) {
        Page page = PageHelper.startPage(pageNum, pageSize);
        List<MyExamOutVo> resultList =myExamMapper.list(deleted);
        PageBean<MyExamOutVo> pageData = new PageBean<MyExamOutVo>(pageNum,
                pageSize, (int)page.getTotal());
        pageData.setItems(resultList);
        return pageData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void joinMyExam(Integer examId, Integer userId) {
        ExamTips examTips = examTipsMapper.selectByPrimaryKey(examId);
        if(null == examTips){
            throw new BizzException("考试信息不存在");
        }
        //如果考试已经被删除则不能添加
        if(!GlobalConstans.ZERO.equals(examTips.getDeleted())){
            throw new BizzException("考试信息已被删除，请重新选择");
        }
        //查看考试是否之前有加入过，如果有加入过 判断状态 如果是正常 则提示已添加，如果是删除 则修改状态未正常
        MyExam dbMyExam = myExamMapper.queryByExamIdAndPersonId(examId,userId);
        //如果开始不存在则直接添加
        if(null == dbMyExam){
            MyExam myExam = new MyExam();
            myExam.setExamId(examId);
            myExam.setPersonId(userId);
            myExam.setStatus(GlobalConstans.ZERO);
            try {
                myExamMapper.insertSelective(myExam);
            } catch (org.springframework.dao.DuplicateKeyException e) {
                log.info("joinMyExam has error",e);
                throw new BizzException("考试信息已经存在，不能重复添加");
            }
        }
        //考试存在 并且被删除则 修改状态即可
        else if( GlobalConstans.ONE.equals(dbMyExam.getDeleted())){
            dbMyExam.setDeleted(GlobalConstans.ZERO);
            dbMyExam.setUpdateTime(new Date());
            myExamMapper.updateByPrimaryKey(dbMyExam);
        }
        else {
            throw new BizzException("考试信息已经存在，不能重复添加");
        }
    }

    @Override
    public List<MyExamOutVo> queryMyExam(Integer userId) {
            //查询我的考试信息
        return myExamMapper.queryMyExam(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delMyExam(Integer id, Integer userId) {
        //先查询我的考试是否存在
        MyExam myExam = myExamMapper.selectByPrimaryKey(id);
        if(null == myExam){
            throw new BizzException("考试不存在");
        }
        //判断是否是自己的考试
        if(!userId.equals(myExam.getPersonId())){
            throw new BizzException("只能删除自己的考试");
        }
        //未报名状态做物理删除
        if(GlobalConstans.ZERO.equals(myExam.getStatus())){
            myExamMapper.deleteByPrimaryKey(id);
        }
        //逻辑删除
        else{
            myExam.setDeleted(GlobalConstans.ONE);
            myExam.setUpdateTime(new Date());
            myExamMapper.updateByPrimaryKey(myExam);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editMyExamStatus(Integer id, Integer userId, Integer status) {
        //先查询我的考试是否存在
        MyExam myExam = myExamMapper.selectByPrimaryKey(id);
        if(null == myExam){
            throw new BizzException("考试不存在");
        }
        //判断是否是自己的考试
        if(!userId.equals(myExam.getPersonId())){
            throw new BizzException("只能操作自己的考试");
        }
        //判断考试信息
        ExamTips examTips = examTipsMapper.selectByPrimaryKey(myExam.getExamId());
        if(null == examTips){
            throw new BizzException("考试信息不存在");
        }
        //如果考试已经被删除则不能报名等操作
        if(!GlobalConstans.ZERO.equals(examTips.getDeleted())){
            throw new BizzException("考试信息已被删除，不能操作");
        }
        Date dateNow = new Date();
        //报名 判断是否到了报名时间过了报名时间 状态是否为未报名
        if(MY_EXAM_STATUS_3.equals(status)){
            if(GlobalConstans.ZERO.equals(myExam.getStatus()) && DateUtil.isEffectiveDate(dateNow, examTips.getSignupBegintime(),examTips.getSignupEndtime() )){
                myExam.setStatus(MY_EXAM_STATUS_3);
                myExam.setUpdateTime(new Date());
                myExamMapper.updateByPrimaryKey(myExam);
                return;
            }
        }
        //考试 判断是否到了考试时间过了考试时间 状态是否为未考试
        if(MY_EXAM_STATUS_4.equals(status)){
            if(MY_EXAM_STATUS_3.equals(myExam.getStatus()) && DateUtil.isEffectiveDate(dateNow, examTips.getExamBegintime(),examTips.getExamEndtime() )){
                myExam.setStatus(MY_EXAM_STATUS_4);
                myExam.setUpdateTime(new Date());
                myExamMapper.updateByPrimaryKey(myExam);
                return;
            }
        }
        throw new BizzException("考试状态不正确");
    }
}
