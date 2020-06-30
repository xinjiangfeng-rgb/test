package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryMarketingActivityPayLogService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryMarketingActivityPayLogServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY170415Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class QRY170415Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY170415Test.class);

	public static void main(String[] args) {
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
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



		IQueryMarketingActivityPayLogService  queryMarketingActivityPayLogService = new QueryMarketingActivityPayLogServiceImpl();
		QRY170415Result ret = queryMarketingActivityPayLogService.queryMarketingActivityPayLog("15516394402", "201911","123123");
		System.out.println (">>>>>>>>>>>>"+JSONObject.toJSONString(ret));
		
	}
}
