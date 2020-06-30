package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryPayListService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryPayListServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.PayHistoryList;
import com.xwtech.xwecp.service.logic.pojo.QRY010034Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class QRY010034Test
{
	private static final Logger logger = LoggerFactory.getLogger(QRY010034Test.class);
	
	public static void main(String[] args) throws Exception
	{
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
		//props.put("platform.url", "http://localhost:8090/hnmcc_ecp/xwecp.do");
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
		lic.setUserBrand("动感地带");
		lic.setUserCity("14");
		lic.setUserMobile("13813071284");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "13813071284");
		ic.addContextParameter("route_type", "3");
		ic.addContextParameter("route_value", "14");
		ic.addContextParameter("ddr_city", "14");
		
		lic.setContextParameter(ic);
		//
	
		
		IQueryPayListService co = new QueryPayListServiceClientImpl();
		QRY010034Result re = co.queryPayHistory("15093421454", "20190216", "20191220");
		System.out.println(JSONObject.toJSONString(re));
		System.out.println(" ====== 开始返回参数 ======");
		if (re != null)
		{
			System.out.println(" ====== 返回结果码 ======" + re.getResultCode());
//			System.out.println(" ====== 状态（0为没有大于100，1为大于100）： ======" + re.getTypeReuslt());
//			System.out.println(" ====== 错误编码 ======" + re.getErrorCode());
//			System.out.println(" ====== 错误信息 ======" + re.getErrorMessage());
			for(PayHistoryList obj:re.getPayHistoryList()){
				System.out.println("-----------pay----------------");
				System.out.println(obj.getPhoneNum());
				System.out.println(obj.getFee());
				System.out.println(obj.getNote());
				System.out.println(obj.getOptTime());
				System.out.println(obj.getFeeType());
				System.out.println(obj.getOperation());
			}
			
		}
	}
}
