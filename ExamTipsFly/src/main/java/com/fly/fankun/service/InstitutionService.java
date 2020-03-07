package com.fly.fankun.service;

import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.vo.inputVo.InstitutionFormInputVo;
import com.fly.fankun.model.vo.outVo.InstitutionFormOutVo;

/**
 * @Title:
 * @author: fan.kun
 * @date: 2020/3/7
 */
public interface InstitutionService {
    void edit(InstitutionFormInputVo inputVo);

    void editStatus(Integer id, Integer status);

    PageBean<InstitutionFormOutVo> queryPage(Integer pageNum, Integer pageSize);
}
