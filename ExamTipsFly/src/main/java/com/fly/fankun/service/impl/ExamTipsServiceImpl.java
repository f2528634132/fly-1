package com.fly.fankun.service.impl;

import com.fly.fankun.exception.BizzException;
import com.fly.fankun.mapper.ExamTipsMapper;
import com.fly.fankun.mapper.ExamTypeMapper;
import com.fly.fankun.model.entity.ExamTips;
import com.fly.fankun.model.entity.ExamType;
import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.vo.inputVo.ExamTipsInputVo;
import com.fly.fankun.model.vo.outVo.ExamTipsOutVo;
import com.fly.fankun.service.ExamTipsService;
import com.fly.fankun.util.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title:
 * @author: fan.kun
 * @date: 2019/10/15
 */
@Service
@Slf4j
public class ExamTipsServiceImpl implements ExamTipsService {

    @Autowired
    private ExamTipsMapper examTipsMapper;
    @Autowired
    private ExamTypeMapper examTypeMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExamTipsOutVo editExamTips(ExamTipsInputVo examTipsInputVo) {
        //查询考试类型是否存在
        Integer examTypeId = examTipsInputVo.getExamTypeId();
        ExamType examType = examTypeMapper.selectByPrimaryKey(examTypeId);
        if(null == examType){
            throw  new BizzException("考试类型不存在");
        }
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
    @Transactional(rollbackFor = Exception.class)
    public void editDeleted(Integer id, Integer deleted) {
        ExamTips examTips = examTipsMapper.selectByPrimaryKey(id);
        if(null  == examTips){
            throw  new BizzException("考试不存在");
        }
        //修改状态'
        examTips.setDeleted(deleted);
        examTips.setUpdateTime(new Date());
        examTipsMapper.updateByPrimaryKeySelective(examTips);
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
}
