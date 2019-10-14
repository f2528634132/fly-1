 /*
  * Copyright (C), 2015-2019
  * FileName: PhoneUtils
  * Author:   buke
  * Date:     2019/5/28 15:42
  * Description: 手机号码工具类
  */
 package com.fly.fankun.util;

 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import org.apache.commons.lang3.StringUtils;

 /**
  * 手机号码工具类
  *
  * @author buke
  * @date 2019/5/28
  */
 public class PhoneUtils {

	 public static final String PHONE_REGEX = "^1\\d{10}$";

	 /**
	  * 判断输入的字符串是否是手机号码，
	  * 字符串为空，长度不满足11位也会返回false
	  * @param phone 需要判断的字符串
	  * @return 判断结果，true：是，false：否
	  */
	 public static boolean isPhone(String phone) {

		 if (StringUtils.isEmpty(phone) || phone.length() != 11) {
			 return false;
		 }

		 Pattern p = Pattern.compile(PHONE_REGEX);
		 Matcher m = p.matcher(phone);

		 return m.matches();
	 }


	 public static void main(String[] args) {

		 System.out.println(isPhone("12811111111"));
	 }

 }
