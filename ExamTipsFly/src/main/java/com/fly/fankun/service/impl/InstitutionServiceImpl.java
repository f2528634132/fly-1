package com.fly.fankun.service.impl;

import com.fly.fankun.mapper.InstitutionMapper;
import com.fly.fankun.model.entity.Institution;
import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.vo.inputVo.InstitutionFormInputVo;
import com.fly.fankun.model.vo.outVo.ExamTipsOutVo;
import com.fly.fankun.model.vo.outVo.InstitutionFormOutVo;
import com.fly.fankun.service.InstitutionService;
import com.fly.fankun.util.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title:
 * @author: fly
 * @date: 2020/3/7
 */
@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    private InstitutionMapper institutionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(InstitutionFormInputVo inputVo) {
        Institution institution = BeanUtil.copy(inputVo, Institution.class);
        if(null == inputVo.getId()){
            //新增
            institutionMapper.insertSelective(institution);
        }else {
            institution.setUpdatetime(new Date());
            institutionMapper.updateByPrimaryKeySelective(institution);
        }
    }

    @Override
    public void editStatus(Integer id, Integer status) {
        Institution institution = new Institution();
        institution.setId(id);
        institution.setStatus(status);
        institution.setUpdatetime(new Date());
        institutionMapper.updateByPrimaryKeySelective(institution);
    }

    @Override
    public PageBean<InstitutionFormOutVo> queryPageBySelf(Integer pageNum, Integer pageSize) {
        Page page = PageHelper.startPage(pageNum, pageSize);
        //("机构类型institutionType：0：自有课程；1：第三方合作")
        List<Institution> resultList = institutionMapper.list(0);

        PageBean<InstitutionFormOutVo> pageData = new PageBean<>(pageNum,
                pageSize, (int)page.getTotal());
        pageData.setItems(BeanUtil.copy(resultList, InstitutionFormOutVo.class));
        return pageData;
    }

    @Override
    public PageBean<InstitutionFormOutVo> queryPageByThird(Integer pageNum, Integer pageSize) {

        Page page = PageHelper.startPage(pageNum, pageSize);
        //("机构类型institutionType：0：自有课程；1：第三方合作")
        List<Institution> resultList = institutionMapper.list(1);

        PageBean<InstitutionFormOutVo> pageData = new PageBean<>(pageNum,
            pageSize, (int)page.getTotal());
        pageData.setItems(BeanUtil.copy(resultList, InstitutionFormOutVo.class));
        return pageData;
    }

}
