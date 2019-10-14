 /*
  * Copyright (C), 2015-2019
  * FileName: ZPDefaultException
  * Author:   buke
  * Date:     2019/5/17 15:43
  * Description: 默认的自定义异常
  */
 package com.fly.fankun.exception;

 import com.fly.fankun.model.result.ResultStatus;

 /**
  * 默认的自定义异常
  *
  * @author fly
  * @date 2019/5/17
  */
 public class ZPDefaultException extends ZPBaseException {

	 private ResultStatus resultStatus;

	 public ZPDefaultException(ResultStatus resultStatus) {
		 super(resultStatus.code, resultStatus.message);
		 this.resultStatus = resultStatus;
	 }
	 
	 public ResultStatus getResultStatus() {
		 return resultStatus;
	 }

	 public void setResultStatus(ResultStatus resultStatus) {
		 this.resultStatus = resultStatus;
	 }
 }
