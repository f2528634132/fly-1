package com.fly.fankun.service.impl;

import com.fly.fankun.exception.BizzException;
import com.fly.fankun.mapper.PersonMapper;
import com.fly.fankun.model.entity.Person;
import com.fly.fankun.model.vo.inputVo.PersonInputVo;
import com.fly.fankun.service.AuthService;
import com.fly.fankun.util.BeanUtil;
import java.util.Date;
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
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PersonMapper personMapper;


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
    public void login(String username, String passWord, Integer type) {

    }
}
