package com.fly.fankun;

import com.github.pagehelper.PageHelper;
import java.util.Properties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@MapperScan("com.fly.fankun.mapper")
public class ExamTipsFlyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamTipsFlyApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name){
        //数据库操作
        //you see you one day one day
        System.out.println("name入库 名字叫做："+name);
        return "";
    }

}
