package com.fly.fankun.service.impl;

import com.fly.fankun.config.AuthConfig;
import com.fly.fankun.exception.BizzException;
import com.fly.fankun.globals.GlobalConstans;
import com.fly.fankun.help.JwtHelper;
import com.fly.fankun.mapper.AdminMapper;
import com.fly.fankun.mapper.PersonMapper;
import com.fly.fankun.model.entity.Admin;
import com.fly.fankun.model.entity.Person;
import com.fly.fankun.model.vo.inputVo.PersonInputVo;
import com.fly.fankun.model.vo.outVo.AuthOutVo;
import com.fly.fankun.service.AuthService;
import com.fly.fankun.util.AESUtils;
import com.fly.fankun.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title:
 * @author: fly
 * @date: 2019/10/15
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private AuthConfig authConfig;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(PersonInputVo personInputVo) {
        Person person = BeanUtil.copy(personInputVo, Person.class);
        //密码解密成明文
        String decryptPassWord = AESUtils.aesDecrypt(person.getPassword(), authConfig.getAesKey());
        person.setPassword(decryptPassWord);
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
        //密码解密成明文
        String decryptPassWord = AESUtils.aesDecrypt(password, authConfig.getAesKey());
        //用户/
        if(GlobalConstans.ZERO.equals(type)){
          Person person =  personMapper.selectByUserName(username);
          if(null == person){
              throw  new BizzException("用户不存在");
          }
          else if(!StringUtils.equals(decryptPassWord, person.getPassword())){
              throw  new BizzException("用户名或密码错误");
          }
            authOutVo.setType(type);
            authOutVo.setId(person.getId());
            authOutVo.setUserName(person.getUsername());
            authOutVo.setName(person.getName());
        }
        //管理员
        else if(GlobalConstans.ONE.equals(type)){
            Admin admin =  adminMapper.selectByUserName(username);
            if(null == admin){
                throw  new BizzException("管理员不存在");
            }
            else if(!StringUtils.equals(decryptPassWord, admin.getPassword())){
                throw  new BizzException("用户名或密码错误");
            }
            authOutVo.setType(type);
            authOutVo.setId(admin.getId());
            authOutVo.setUserName(admin.getUsername());
            authOutVo.setName(admin.getName());
        }else{
            throw  new BizzException("用户类型不正确");
        }
        //换取token
        authOutVo.setToken(jwtHelper.createEnterpriseToken(authOutVo.getId(),authOutVo.getType()));
        return authOutVo;
    }
}
