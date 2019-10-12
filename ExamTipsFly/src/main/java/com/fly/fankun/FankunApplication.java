package com.fly.fankun;

import com.fly.fankun.mapper.PersonMapper;
import com.fly.fankun.model.entity.BaseEntity;
import com.fly.fankun.model.entity.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
//@MapperScan("com.fly.fankun.mapper")
@RestController
public class FankunApplication {

    public static void main(String[] args) {
        SpringApplication.run(FankunApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name){
        Person p = new Person();
        p.setName(name);
        //数据库操作
        //you see you one day one day
        System.out.println("name入库 名字叫做："+name);
        return p.getName();
    }
}
