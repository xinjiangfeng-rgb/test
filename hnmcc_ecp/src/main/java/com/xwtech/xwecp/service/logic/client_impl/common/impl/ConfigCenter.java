package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ConfigCenter {
	public static final String REMOTE_URL = "http://211.138.17.200:9060/pms_open_intfc_proxy/recommendMarket";
	public static final String PLATFORM_CODE = "MOBILE_HALL";
	public static final String EVENT_CODE = "EVENT_MOBILE_HALL";
	public static final String PASSWORD = "2F4D)PLI7b";
	public static final String VERSION = "0100";
	public static final String USER_ID = "YzzzzXBZT";
	public static final String USER_AREA_CODE = "371";

	public static String nowTime() {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 计算签名md5（渠道标识+事件标识+事件惟一序列号+服务号码+请求时间+密钥）
	 * 
	 * @param param
	 * @return
	 */
	public static String calcSign(String squn_num, String phone, String reqTime) {
		String rs = PLATFORM_CODE + EVENT_CODE + squn_num + phone + reqTime + PASSWORD;
		System.out.println("加密字符串：" + rs);
		rs = DigestUtils.md5Hex(rs);
		System.out.println("计算签名结果：" + rs);
		return rs;
	}

}
