 /*
  * Copyright (C), 2015-2019
  * FileName: NeedLogin
  * Author:   buke
  * Date:     2019/5/17 14:49
  * Description:
  */
 package com.fly.fankun.annotation;

 import java.lang.annotation.Documented;
 import java.lang.annotation.ElementType;
 import java.lang.annotation.Retention;
 import java.lang.annotation.RetentionPolicy;
 import java.lang.annotation.Target;

 /**
  * 登陆检测
  *	修饰在需要做登陆滤过的接口方法
  * @author fly
  * @date 2019/10/17
  */
 @Documented
 @Target(ElementType.METHOD)
 @Retention(RetentionPolicy.RUNTIME)
 public @interface NeedLogin {

 }
