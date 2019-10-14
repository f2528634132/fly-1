/*
 * Copyright (C), 2015-2019
 * FileName: BaseController
 * Author:   b 2019/5/8 17:55uke
 * Date:     2019/5/8 17:55
 * Description: controller基础封装类
 */
package com.fly.fankun.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * controller基础封装类
 *
 * @author fly
 * @date 2019/5/8
 */
@Slf4j
public class BaseController {
	/**
	 * 获取本次请求
	 * @return
	 */
	protected HttpServletRequest getRequest() {

		return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
	}

	protected HttpServletResponse getResponse() {

		return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getResponse();
	}

	protected HttpSession getSession() {

		return getRequest().getSession();
	}

	protected Object getSessionValue(String sessionName) {

		return getRequest().getSession().getAttribute(sessionName);
	}

	protected void addHead(String headerName, String headerValue) {

		getResponse().addHeader(headerName, headerValue);
	}

//	protected Long getUserId() {
//		return TokenUtils.getTokenUser(getRequest()).getUserId();
//	}
//
//	protected TokenUser getUserInfo() {
//		return TokenUtils.getTokenUser(getRequest());
//	}
}
