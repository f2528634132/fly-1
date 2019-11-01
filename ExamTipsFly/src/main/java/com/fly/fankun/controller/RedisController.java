package com.fly.fankun.controller;

import com.fly.fankun.help.RedisHelp;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title:
 * @author: fan.kun
 * @date: 2019/11/1
 */
@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisController {

    private static int ExpireTime = 60;   // redis中存储的过期时间60s

    @Resource
    private RedisHelp redisUtil;

    @GetMapping("set")
    public boolean redisset(String key, String value){

        //return redisUtil.set(key,userEntity,ExpireTime);

        return redisUtil.set(key,value);
    }

    @GetMapping("get")
    public Object redisget(String key){
        return redisUtil.get(key);
    }

    @GetMapping("expire")
    public boolean expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }
}