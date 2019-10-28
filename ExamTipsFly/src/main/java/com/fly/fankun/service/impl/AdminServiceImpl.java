package com.fly.fankun.service.impl;

import com.fly.fankun.exception.BizzException;
import com.fly.fankun.mapper.AdminMapper;
import com.fly.fankun.model.entity.Admin;
import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.result.PageObject;
import com.fly.fankun.model.result.ResultStatus;
import com.fly.fankun.model.vo.inputVo.AdminInputVo;
import com.fly.fankun.model.vo.outVo.AdminOutVo;
import com.fly.fankun.service.AdminService;
import com.fly.fankun.util.BeanUtil;
import com.fly.fankun.util.PhoneUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title:
 * @author: fly
 * @date: 2019/10/14
 */
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public PageBean<AdminOutVo> queryPage(Integer pageNum, Integer pageSize) {
        Page page = PageHelper.startPage(pageNum, pageSize);
        List<AdminOutVo> resultList = adminMapper.list();
        PageBean<AdminOutVo> pageData = new PageBean<AdminOutVo>(pageNum,
            pageSize, (int)page.getTotal());
        pageData.setItems(resultList);
        return pageData;
    }

    @Override
    public AdminOutVo editAdmin(AdminInputVo inputVo) {
        //检验手机号码是否正确
        boolean phone = PhoneUtils.isPhone(inputVo.getPhone());
        if (!phone) {
            throw new BizzException(ResultStatus.BAD_REQUEST.code, "请输入正确的手机号码");
        }
        Admin admin = BeanUtil.copy(inputVo, Admin.class);
        //新增
        if(null == inputVo.getId()){
            adminMapper.insertSelective(admin);
        }else{
            adminMapper.updateByPrimaryKeySelective(admin);
        }
        return BeanUtil.copy(admin, AdminOutVo.class);
    }

    @Override
    public void delAdmin(Integer id) {
        adminMapper.deleteByPrimaryKey(id);
    }
}
