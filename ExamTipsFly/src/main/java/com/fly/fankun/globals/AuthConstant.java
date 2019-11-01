 /*
  * Copyright (C), 2015-2019
  * FileName: AuthConstant
  * Author:   buke
  * Date:     2019/5/16 16:34
  * Description: 认证相关配置
  */
 package com.fly.fankun.globals;

 /**
  * 认证相关配置
  *
  * @author fly
  * @date 2019/10/16
  */
 public class AuthConstant {

	 /**
	  * 签发token时，token所在响应头的名称
	  */
	 public static final String HEADER_NAME_TOKEN_RESPONSE = "TokenUserVo";

	 /**
	  * 客户端请求时携带token的请求头名称
	  */
	 public static final String HEADER_NAME_TOKEN_REQUEST = "Authorization";

	 /**
	  * 前端请求接口时，传递token添加的前缀
	  * NODE：有空格
	  */
	 public static final String PREFIX_TOKEN = "Bearer ";

	 /**
	  * 传递给下游服务的用户id
	  */
	 public static final String HEADER_NAME_USER_ID = "userId";

	 /**
	  * 传递给下游服务的账号类型
	  * @see com.zhaopin.gwc.core.enums.AccountTypeEnum
	  */
	 public static final String HEADER_NAME_ACCOUNT_TYPE= "accountType";

	 /**
	  * 传递给下游服务的用户名
	  */
	 public static final String HEADER_NAME_USER_NAME = "userName";

	 /**
	  * 初始化密码
	  */
	 public static final String DEFAULT_PASSWORD = "123456";

     /**
      * token刷新间隔时间,二小时为单位
      */
	 public static final int TOKEN_REFRESH_INTERVAL_HOUR = 2 ;


	 private AuthConstant() { throw new IllegalStateException("Utility class"); }

 }
