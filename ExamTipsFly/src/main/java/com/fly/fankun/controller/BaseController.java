/*
 * Copyright (C), 2015-2019
 * FileName: BaseController
 * Author:   b 2019/5/8 17:55uke
 * Date:     2019/5/8 17:55
 * Description: controller基础封装类
 */
package com.fly.fankun.controller;

import com.alibaba.fastjson.JSONObject;
import com.fly.fankun.dict.AccountAuthTypeEnum;
import com.fly.fankun.exception.BizzException;
import com.fly.fankun.globals.AuthConstant;
import com.fly.fankun.mapper.AdminMapper;
import com.fly.fankun.mapper.PersonMapper;
import com.fly.fankun.model.entity.Admin;
import com.fly.fankun.model.entity.Person;
import com.fly.fankun.model.vo.TokenUserVo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private PersonMapper personMapper;
	@Autowired
	private AdminMapper adminMapper;
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

	protected Integer getUserId() {
		return getUserInfo().getId();
	}

	protected TokenUserVo getUserInfo() {
		TokenUserVo tokenUserVo = (TokenUserVo) getRequest().getAttribute(AuthConstant.HEADER_NAME_TOKEN_RESPONSE);
		System.out.println(JSONObject.toJSONString(tokenUserVo));
		if(null == tokenUserVo){
			throw  new BizzException("暂未登录");
		}
		Integer id = tokenUserVo.getId();
		//个人用户
		if(AccountAuthTypeEnum.CONSUMER.getCode().equals(tokenUserVo.getUserType())){
			Person person = personMapper.selectByPrimaryKey(id);
			if(null == person){
				throw  new BizzException("用户不存在");
			}
		}else if(AccountAuthTypeEnum.SERVICE.getCode().equals(tokenUserVo.getUserType())){
			Admin admin = adminMapper.selectByPrimaryKey(id);
			if(null == admin){
				throw  new BizzException("后端用户不存在");
			}
		}
		return  tokenUserVo;
	}

	protected Integer getUserType() {
		return getUserInfo().getUserType();
	}
}
