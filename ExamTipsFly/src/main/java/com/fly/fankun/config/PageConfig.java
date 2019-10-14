package com.fly.fankun.config;

import com.github.pagehelper.PageHelper;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: 分页配置 https://www.cnblogs.com/cksvsaaa/p/6036068.html
 * @author: fan.kun
 * @date: 2019/10/14
 */
@Configuration
public class PageConfig {
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("dialect","PostgreSQL");    //配置PostgreSQL数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
