package com.fly.fankun.service;

import com.fly.fankun.model.result.PageBean;
import com.fly.fankun.model.vo.inputVo.TutorialInputVo;
import com.fly.fankun.model.vo.outVo.TutorialOutputVo;

/**
 * @Title:
 * @author: fly
 * @date:
 */

public interface TutorialService {
    //编辑教材真题
    void edit(TutorialInputVo tutorialInputVo);

    PageBean<TutorialOutputVo> queryPageByEntity(Integer pageNum, Integer pageSize, Integer deleted);

    PageBean<TutorialOutputVo> queryPageByOnline(Integer pageNum, Integer pageSize, Integer deleted);
}
