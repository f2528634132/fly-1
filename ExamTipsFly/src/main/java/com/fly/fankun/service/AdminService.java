package com.fly.fankun.service;

import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.result.PageObject;
import com.fly.fankun.model.vo.inputVo.AdminInputVo;
import com.fly.fankun.model.vo.outVo.AdminOutVo;

/**
 * @Title:
 * @author: fan.kun
 * @date: 2019/10/14
 */
public interface AdminService {
    /**
     * 查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageBean<AdminOutVo> queryPage(Integer pageNum, Integer pageSize);

    /**
     * 编辑用户 新增/修改用户
     * @param inputVo
     * @return
     */
    AdminOutVo editAdmin(AdminInputVo inputVo);

    /**
     * 删除用户
     * @param id
     */
    void delAdmin(Integer id);
}
