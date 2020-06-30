package com.xwtech.xwecp.test;

import java.util.Properties;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryBusinessStoreService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryBusinessStoreServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY20170621Result;

public class QRY20170621Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY20170621Test.class);

	public static void main(String[] args) {
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");

		XWECPLIClient client = XWECPLIClient.createInstance(props);
		//逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("动感地带");
		lic.setUserCity("14");
		lic.setUserMobile("15850573615");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "15850573615");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "14");
		ic.addContextParameter("ddr_city", "14");

		ic.addContextParameter("user_id", "1419300006484492");

		lic.setContextParameter(ic);

		QRY20170621Result ret = null;
		IQueryBusinessStoreService queryBusinessStoreService = new QueryBusinessStoreServiceImpl();
		System.out.println(System.currentTimeMillis());
		ret = queryBusinessStoreService.queryBusinessStore("15738357786");
		System.out.println(JSONObject.toJSONString(ret));
		System.out.println(System.currentTimeMillis());

	}

}
