package com.fly.fankun.service.impl;

import com.fly.fankun.mapper.TutorialMapper;
import com.fly.fankun.model.entity.Tutorial;
import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.vo.inputVo.TutorialInputVo;
import com.fly.fankun.model.vo.outVo.InstitutionFormOutVo;
import com.fly.fankun.model.vo.outVo.TutorialOutputVo;
import com.fly.fankun.service.TutorialService;
import com.fly.fankun.util.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Title:
 * @author: fly
 * @date: 2020-3-8
 */
@Service
public class TutorialServiceImpl implements TutorialService {

    @Autowired
    private TutorialMapper tutorialMapper;

    @Override
    public void edit(TutorialInputVo tutorialInputVo) {
        Tutorial tutorial = BeanUtil.copy(tutorialInputVo,Tutorial.class);
        if (tutorialInputVo.getId()==null){
            tutorial.setCreatetime(new Date());
            tutorialMapper.insertSelective(tutorial);
        }else {
            tutorial.setUpdatetime(new Date());
            tutorialMapper.updateByPrimaryKey(tutorial);
        }
    }

    @Override
    public PageBean<TutorialOutputVo> queryPageByEntity(Integer pageNum, Integer pageSize, Integer deleted) {
        Page page= PageHelper.startPage(pageNum,pageSize);
        Integer tutorialType=0;
        List<TutorialOutputVo> list=tutorialMapper.tutorialList(deleted,tutorialType);
        PageBean<TutorialOutputVo> pageBean=new PageBean<>(pageNum,pageSize,(int)page.getTotal());
        pageBean.setItems(list);
        return pageBean;
    }

    @Override
    public PageBean<TutorialOutputVo> queryPageByOnline(Integer pageNum, Integer pageSize, Integer deleted) {
        Page page=PageHelper.startPage(pageNum,pageSize);
        Integer tutorialType=1;
        List<TutorialOutputVo> list=tutorialMapper.tutorialList(deleted,tutorialType);
        PageBean pageBean=new PageBean(pageNum,pageSize,(int)page.getTotal());
        pageBean.setItems(list);
        return pageBean;
    }
}
