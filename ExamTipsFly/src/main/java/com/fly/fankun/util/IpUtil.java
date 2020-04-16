package com.zhaopin.zhaokao.core.utils;

import com.zhaopin.zhaokao.core.exception.BizException;
import java.net.InetAddress;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * ip地址util
 *
 * @author fan.kun
 * @date 2019/5/6
 */
@Slf4j
public class IpUtil {
	/**
	 * The constant unknown.
	 */
	private static final String UNKNOWN = "unknown";
	/**
	 * The constant localHostIp.
	 */
    private static final String LOCALHOSTIP = "127.0.0.1";
	/**
	 * The constant localHostAddress.
	 */
    private static final String LOCALHOSTADDRESS = "0:0:0:0:0:0:0:1";
	/**
	 * The constant ipAddressLength.
	 */
    private static final int IPADDRESSLENGTH = 15;
	/**
	 * The constant ipAddressLength.
	 */
    private static final String SEPARATOR_COMMA = ",";

	/**
	 * Gets ip addr.
	 *
	 * @param request the request
	 * @return the ip addr
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (LOCALHOSTIP.equals(ipAddress) || LOCALHOSTADDRESS.equals(ipAddress)) {
				// 根据网卡取本机配置的IP
				try {
					InetAddress inet = InetAddress.getLocalHost();
					ipAddress = inet.getHostAddress();
				} catch (Exception e) {
					log.error("获取IP地址错误", e);
					throw new BizException("Ip地址有误");
				}
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		// "***.***.***.***".length() = 15
		if (ipAddress != null && ipAddress.length() > IPADDRESSLENGTH) {
			if (ipAddress.indexOf(SEPARATOR_COMMA) >= 1 ) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(SEPARATOR_COMMA));
			}
		}
		return ipAddress;
	}

	public static String getIpAddrCatch(HttpServletRequest request){
		try {
			return getIpAddr(request);
		}catch (Exception e){
			log.error("获取IP地址错误", e);
		}
		return null;
	}

}
