/*
 * Copyright (C), 2015-2019
 * FileName: BaseResult
 * Author:   b 2019/5/8 16:15uke
 * Date:     2019/5/8 16:15
 * Description: Vo基类
 */
package com.fly.fankun.model.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/*
 * 〈Vo基类〉
 *
 * @author fly
 * @date 2019/5/8
 */
@Data
@EqualsAndHashCode()
@Accessors(chain = true)
public class BaseResult<T> {

	private int code;
	private String msg;
	private T data;
	private long timestamp;


	public BaseResult(){

	}


	public BaseResult(int code, String msg, T data) {

		this.code = code;
		this.msg = msg;
		this.data = data;

		timestamp = System.currentTimeMillis();
	}

	/**
	 * 请求结果
	 *
	 * @param resultStatus 服务状态码
	 * @param data         返回的数据
	 * @param <T>          返回的数据类型
	 * @return 返回实体类
	 */
	public static <T> BaseResult<T> result(ResultStatus resultStatus, T data) {

		return new BaseResult<>(resultStatus.code, resultStatus.message, data);
	}

	public static <T> BaseResult<T> result(ResultStatus resultStatus) {

		return BaseResult.result(resultStatus, null);
	}

	public static <T> BaseResult result(int code, String message) {

		return new BaseResult<T>(code, message, null);
	}

	/**
	 * 请求成功
	 *
	 * @return 返回实体类
	 */
	public static <T> BaseResult<T> success() {

		return result(ResultStatus.SUCCESS, null);
	}

	/**
	 * 请求成功
	 *
	 * @param data 请求数据
	 * @param <T>  数据类型
	 * @return 返回实体类
	 */
	public static <T> BaseResult<T> success(T data) {

		return result(ResultStatus.SUCCESS, data);
	}

	/**
	 * 请求成功
	 *
	 * @param message 消息内容
	 * @return 返回实体类
	 */
	public static BaseResult success(String message) {

		return new BaseResult(ResultStatus.SUCCESS.code, message, null);
	}

	/**
	 * 系统异常
	 *
	 * @param <T> 返回体
	 * @return 返回实体类
	 */
	public static <T> BaseResult<T> systemError() {

		return result(ResultStatus.SYSTEM_ERROR, null);
	}

	/**
	 * 业务异常
	 *
	 * @param <T> 返回体
	 * @return 返回实体类
	 */
	public static <T> BaseResult<T> fail() {

		return result(ResultStatus.FAIL, null);
	}

	/**
	 * 业务异常
	 *
	 * @param message 异常信息
	 * @param <T>     返回体
	 * @return 返回实体类
	 */
	public static <T> BaseResult<T> fail(String message) {

		return new BaseResult(ResultStatus.FAIL.code, message, null);
	}

	@JsonIgnore
	public boolean isSuccess() {

		return this.code == ResultStatus.SUCCESS.code;
	}

}
