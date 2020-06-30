package com.xwtech.xwecp.test;

import java.util.Properties;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IBusinessUnsubscribeService;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryCurrentDayFeeService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.BusinessUnsubscribeServiceImpl;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryCurrentDayFeeServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.DEL201704Result;
import com.xwtech.xwecp.service.logic.pojo.QRY010097Result;

public class DEL201704Test {private static final Logger logger = LoggerFactory.getLogger(DEL201704Test.class);

	/**
	 * 查询实时余额
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
//		props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
		//props.put("platform.url", "http://10.32.229.82:10008/sms_ecp/xwecp.do");
		//props.put("platform.url", "http://10.32.122.166:10009/js_ecp/xwecp.do");
		//props.put("platform.url", "http://10.32.65.238:8081/sms_ecp/xwecp.do");
		//props.put("platform.url", "http://10.32.65.238/js_ecp/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");

		XWECPLIClient client = XWECPLIClient.createInstance(props);
		//逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("1");
		lic.setUserCity("12");
		lic.setUserMobile("18236464534");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "18738123092");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "12");


		lic.setContextParameter(ic);


		IBusinessUnsubscribeService businessUnsubscribeService = new BusinessUnsubscribeServiceImpl();
		DEL201704Result ret = businessUnsubscribeService.businessUnsubscribe("15837179491", "a");
		System.out.println(JSONObject.toJSONString(ret));
	}

}
