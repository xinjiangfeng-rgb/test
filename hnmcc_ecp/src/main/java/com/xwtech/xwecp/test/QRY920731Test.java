package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.INewBillQueryService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.NewBillQueryServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY920731Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class QRY920731Test {

	private static final Logger logger = LoggerFactory.getLogger(QRY920731Test.class);

	public static void main(String[] args) throws Exception {
		// 初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");
		XWECPLIClient client = XWECPLIClient.createInstance(props);
		// 逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("动感地带");
		lic.setUserCity("12");
		lic.setUserMobile("18236464534");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "18236464534");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "12");
		ic.addContextParameter("ddr_city", "12");
		ic.addContextParameter("user_id", ""); // 2056200011182291QRY199411
		lic.setContextParameter(ic);
		QRY920731Result ret =null;
		INewBillQueryService newBillQueryService = new NewBillQueryServiceImpl();
		ret  = newBillQueryService.newBillQuery("13607669567", "201912");
		System.out.println(JSONObject.toJSONString(ret));
	}
}
