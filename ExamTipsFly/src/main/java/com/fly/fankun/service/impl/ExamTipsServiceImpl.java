package com.fly.fankun.service.impl;

import com.fly.fankun.exception.BizzException;
import com.fly.fankun.mapper.ExamTipsMapper;
import com.fly.fankun.mapper.ExamTypeMapper;
import com.fly.fankun.mapper.MyExamMapper;
import com.fly.fankun.model.entity.ExamTips;
import com.fly.fankun.model.entity.ExamType;
import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.vo.inputVo.ExamScoresOpenInputVo;
import com.fly.fankun.model.vo.inputVo.ExamTipsInputVo;
import com.fly.fankun.model.vo.inputVo.QueryInfoInputVo;
import com.fly.fankun.model.vo.outVo.ExamScoresOutVo;
import com.fly.fankun.model.vo.outVo.ExamStatisticsOutVo;
import com.fly.fankun.model.vo.outVo.ExamTipsOutVo;
import com.fly.fankun.model.vo.outVo.ExamTypeOutVo;
import com.fly.fankun.service.ExamTipsService;
import com.fly.fankun.util.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title:
 * @author: fly
 * @date: 2019/10/15
 */
@Service
@Slf4j
public class ExamTipsServiceImpl implements ExamTipsService {

    @Autowired
    private ExamTipsMapper examTipsMapper;
    @Autowired
    private ExamTypeMapper examTypeMapper;
    @Autowired
    private MyExamMapper myExamMapper;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExamTipsOutVo editExamTips(ExamTipsInputVo examTipsInputVo) {
        //查询考试类型是否存在
//        Integer examTypeId = examTipsInputVo.getExamTypeId();
//        ExamType examType = examTypeMapper.selectByPrimaryKey(examTypeId);
//        if(null == examType){
//            throw  new BizzException("考试类型不存在");
//        }
        ExamTips examTips = BeanUtil.copy(examTipsInputVo, ExamTips.class);
        //新增
        if(null == examTipsInputVo.getId()){
            examTipsMapper.insertSelective(examTips);
        }else{
            examTipsMapper.updateByPrimaryKeySelective(examTips);
        }
        return BeanUtil.copy(examTips, ExamTipsOutVo.class);
    }
    @Override
    public void addExamScores(ExamScoresOpenInputVo examScoresOpenInputVo) {
        if(null  == examScoresOpenInputVo.getId()){
            throw  new BizzException("需添加的考试项id丢失");
        }
        ExamTips examTips = BeanUtil.copy(examScoresOpenInputVo, ExamTips.class);
        System.out.println(examTips);
        examTipsMapper.updateByPrimaryKeySelective(examTips);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editStatus(Integer id, Integer status) {
        ExamTips examTips = examTipsMapper.selectByPrimaryKey(id);
        if(null  == examTips){
            throw  new BizzException("考试不存在");
        }
        //修改状态'
        examTips.setStatus(status);
        examTips.setUpdateTime(new Date());
        examTipsMapper.updateByPrimaryKeySelective(examTips);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editDeleted(Integer id, Integer deleted) {
        ExamTips examTips = examTipsMapper.selectByPrimaryKey(id);
        if(null  == examTips){
            throw  new BizzException("考试不存在");
        }
        //删除
        examTips.setDeleted(deleted);
        examTips.setUpdateTime(new Date());
        examTipsMapper.updateByPrimaryKeySelective(examTips);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExamTipsOutVo queryExam(Integer id) {
//        ExamTips examTips = examTipsMapper.selectByPrimaryKey(id);
        ExamTipsOutVo examTipsOutVo=examTipsMapper.selectByExamId(id);
        if(null  == examTipsOutVo){
            throw  new BizzException("考试不存在");
        }
        return examTipsOutVo;
    }

    @Override
    public PageBean<ExamTipsOutVo> queryPage(Integer deleted, Integer pageNum, Integer pageSize) {
        Page page = PageHelper.startPage(pageNum, pageSize);
        List<ExamTipsOutVo> resultList = examTipsMapper.list(deleted);
        PageBean<ExamTipsOutVo> pageData = new PageBean<ExamTipsOutVo>(pageNum,
            pageSize, (int)page.getTotal());
        pageData.setItems(resultList);
        return pageData;
    }

    @Override
    public PageBean<ExamTipsOutVo> queryPageByInfo(QueryInfoInputVo queryInfoInputVo) {
        Page page = PageHelper.startPage(queryInfoInputVo.getPageNum(), queryInfoInputVo.getPageSize());
        List<ExamTipsOutVo> resultList = examTipsMapper.list(queryInfoInputVo);
        PageBean<ExamTipsOutVo> pageData = new PageBean<ExamTipsOutVo>(queryInfoInputVo.getPageNum(),
                queryInfoInputVo.getPageSize(), (int)page.getTotal());
        pageData.setItems(resultList);
        return pageData;
    }

    @Override
    public PageBean<ExamScoresOutVo> examScoresByAdmin(Integer deleted, Integer pageNum, Integer pageSize) {
        Page page=PageHelper.startPage(pageNum,pageSize);
        List<ExamScoresOutVo> resultList=examTipsMapper.examScoresList(deleted);
        PageBean<ExamScoresOutVo> pageData=new PageBean<>(pageNum,pageSize,(int)page.getTotal());
        pageData.setItems(resultList);
        return pageData;
    }

    @Override
    public List<ExamTypeOutVo> queryExamTypeList() {
        return examTypeMapper.queryAll();
    }

    @Override
    public List<ExamStatisticsOutVo> examStatistics() {
        List<ExamStatisticsOutVo> list = examTypeMapper.getExamStatistics();
        if(CollectionUtils.isEmpty(list)){
            return list;
        }
        for (ExamStatisticsOutVo vo:list) {
            ExamStatisticsOutVo result = myExamMapper.getExamStatistics(vo.getId());
            if(null != result){
                vo.setHasExamNum(result.getHasExamNum());
                vo.setNoExamNum(result.getNoExamNum());
                vo.setPersonNum(result.getPersonNum());
            }

        }
        return list;
    }


}
