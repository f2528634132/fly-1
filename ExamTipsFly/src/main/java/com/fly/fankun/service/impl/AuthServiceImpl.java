package com.fly.fankun.service.impl;

import com.fly.fankun.exception.BizzException;
import com.fly.fankun.globals.GlobalConstans;
import com.fly.fankun.mapper.AdminMapper;
import com.fly.fankun.mapper.PersonMapper;
import com.fly.fankun.model.entity.Admin;
import com.fly.fankun.model.entity.Person;
import com.fly.fankun.model.vo.inputVo.PersonInputVo;
import com.fly.fankun.model.vo.outVo.AuthOutVo;
import com.fly.fankun.service.AuthService;
import com.fly.fankun.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private AdminMapper adminMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(PersonInputVo personInputVo) {
        Person person = BeanUtil.copy(personInputVo, Person.class);
        try {
            personMapper.insertSelective(person);
        } catch (org.springframework.dao.DuplicateKeyException e) {
            log.info("register has error",e);
            throw new BizzException("用户名已存在");
        }
    }

    @Override
    public AuthOutVo login(String username, String password, Integer type) {
        AuthOutVo authOutVo = new AuthOutVo();
        //用户/
        if(GlobalConstans.ONE.equals(type)){
          Person person =  personMapper.selectByUserName(username);
          if(null == person){
              throw  new BizzException("用户不存在");
          }
          else if(!StringUtils.equals(password, person.getPassword())){
              throw  new BizzException("用户名或密码错误");
          }
            authOutVo.setType(type);
            authOutVo.setId(person.getId());
            authOutVo.setUserName(person.getUsername());
            authOutVo.setName(person.getName());
        }
        //管理员
        else{
            Admin admin =  adminMapper.selectByUserName(username);
            if(null == admin){
                throw  new BizzException("管理员不存在");
            }
            else if(!StringUtils.equals(password, admin.getPassword())){
                throw  new BizzException("用户名或密码错误");
            }
            authOutVo.setType(type);
            authOutVo.setId(admin.getId());
            authOutVo.setUserName(admin.getUsername());
            authOutVo.setName(admin.getName());
        }
        return authOutVo;
    }
}
