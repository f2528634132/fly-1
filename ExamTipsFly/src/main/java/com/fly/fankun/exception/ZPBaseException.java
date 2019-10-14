 /*
  * Copyright (C), 2015-2019
  * FileName: ZPBaseException
  * Author:   buke
  * Date:     2019/5/17 15:34
  * Description: 自定义异常基类
  */
 package com.fly.fankun.exception;

 /**
  * 自定义异常基类
  *
  * @author fly
  * @date 2019/5/17
  */
 public class ZPBaseException extends RuntimeException {

	 protected Integer code;
	 protected String message;
	 

	 public ZPBaseException(Integer code, String message) {
		 this.code = code;
		 this.message = message;
	 }

	 public Integer getCode() {
		 return code;
	 }

	 public void setCode(Integer code) {
		 this.code = code;
	 }

	 @Override
	 public String getMessage() {
		 return message;
	 }

	 public void setMessage(String message) {
		 this.message = message;
	 }
 }
