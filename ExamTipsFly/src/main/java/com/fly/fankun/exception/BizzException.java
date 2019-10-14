 /*
  * Copyright (C), 2015-2019
  * FileName: BizzException
  * Author:   buke
  * Date:     2019/5/17 15:50
  * Description: 业务异常类
  */
 package com.fly.fankun.exception;

 import com.fly.fankun.model.result.ResultStatus;

 /**
  * 业务异常类
  *
  * @author fly
  * @date 2019/5/17
  */
 public class BizzException extends ZPBaseException {

	 public BizzException(String message) {
		 super(ResultStatus.FAIL.code, message);
	 }

	 public BizzException(Integer code, String message) {
		 super(code, message);
	 }
 }
