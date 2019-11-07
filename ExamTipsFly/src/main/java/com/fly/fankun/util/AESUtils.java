 /*
  * Copyright (C), 2015-2019
  * FileName: AESUtils
  * Author:   buke
  * Date:     2019/6/26 16:48
  * Description: AES加密工具类
  */
 package com.fly.fankun.util;

 import cn.hutool.crypto.Mode;
 import cn.hutool.crypto.Padding;
 import cn.hutool.crypto.symmetric.AES;
 import org.apache.commons.lang3.StringUtils;

 /**
  * AES加密工具类
  * @author fly
  * @date 2019/6/26
  */
 public class AESUtils {

	 /**
	  * AES 加密
	  * @param content 明文
	  * @param key 秘钥
	  * @return 加密后的密文
	  */
	 public static String aesEncrypt(String content, String key) {

		 AES aes = getAes(key);
		 return aes.encryptBase64(content);
	 }

	 /**
	  * AES解密
	  * @param encryptStr 密文
	  * @param key 秘钥
	  * @return 解密后的明文
	  */
	 public static String aesDecrypt(String encryptStr, String key) {
		 AES aes = getAes(key);
		 String decrypt = aes.decryptStrFromBase64(encryptStr);

		 if (StringUtils.isBlank(decrypt)){
		 	throw new IllegalArgumentException("解密失败，密文:" + decrypt);
		 }

		 return decrypt;
	 }
	 
	 private static AES getAes(String key) {
		 return new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes());
	 }

	 public static void main(String[] args) {
		 System.out.println(aesEncrypt("buke123456","aA0fR0fA5rF3cH1h"));
		 System.out.println(aesDecrypt("qVwZJk4OE+EpNQsaf67bKA==","aA0fR0fA5rF3cH1h"));
	 }
	 
 }
