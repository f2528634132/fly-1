package com.fly.fankun.mapper;

import com.fly.fankun.model.entity.Admin;
import com.fly.fankun.model.vo.outVo.AdminOutVo;
import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    /**
     * 查询列表
     * @return
     */
    List<AdminOutVo> list();

    Admin selectByUserName(String username);
}