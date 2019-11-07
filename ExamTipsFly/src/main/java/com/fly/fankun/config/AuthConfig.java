/*
 * Copyright (C), 2015-2019
 * FileName: AuthConfig
 * Author:   buke
 * Date:     2019/6/26 17:01
 * Description: 认证配置类
 */
package com.fly.fankun.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 认证配置类
 *
 * @author fly
 * @date 2019/6/26
 */
@Data
@Component
@ConfigurationProperties(prefix = "sys.auth")
public class AuthConfig {
    private String aesKey;
    private String signKey;
}
