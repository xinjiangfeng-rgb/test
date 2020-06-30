package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IPushInvoiceService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.PushInvoiceServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY201001Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class QRY201001Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY201001Test.class);

	public static void main(String[] args) {
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://218.206.204.153/hnmcc_ecp_nengkai/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");

		XWECPLIClient client = XWECPLIClient.createInstance(props);
		//逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("12");
		lic.setUserCity("12");
		lic.setUserMobile("15890158325");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "15890158325");
		ic.addContextParameter("route_type", "2");
		ic.addContextParameter("route_value", "15890158325");


		lic.setContextParameter(ic);
		

		IPushInvoiceService pushInvoiceService = new PushInvoiceServiceImpl();
		QRY201001Result ret = pushInvoiceService.pushInvoice("0", "201605", "15093421454", "13592645863", "543440189@qq.com");
		System.out.println(JSONObject.toJSONString(ret));
		

	}
}
