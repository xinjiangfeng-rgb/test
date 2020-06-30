package com.xwtech.xwecp.test;

import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IEsbOrderInvoiceService;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryPkgConsumeService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.EsbOrderInvoiceServiceImpl;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryPkgConsumeServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.DEL170107Result;
import com.xwtech.xwecp.service.logic.pojo.QRY170411Result;

public class DEL170107Test {


	private static final Logger logger = LoggerFactory.getLogger(DEL170107Test.class);


	public static void main(String args[]){
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
//		props.put("platform.url", "http://localhost:8080/hnmcc_ecp/xwecp.do");
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
		
		
		IEsbOrderInvoiceService esbOrderInvoiceService =new EsbOrderInvoiceServiceImpl();
		DEL170107Result ret = esbOrderInvoiceService.esbOrderInvoice("18236464534", "ADD", "1", "1146910383@qq.com", "", "3");
		System.out.println(JSONObject.toJSONString(ret));
		
	}

}
