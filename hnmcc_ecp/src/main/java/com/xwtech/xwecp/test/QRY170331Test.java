package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryCurrentMonthFeeService;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryFreeFlowService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryCurrentMonthFeeServiceImpl;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryFreeFlowServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY170331Result;
import com.xwtech.xwecp.service.logic.pojo.QRY920013Result;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import java.util.Properties;

public class QRY170331Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY170331Test.class);

	public static void main(String[] args) {
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
        //props.put("platform.url", "http://localhost:8090/hnmcc_ecp/xwecp.do");
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

		IQueryFreeFlowService co = new QueryFreeFlowServiceImpl();
		QRY170331Result re = null;
		try {
			re = co.queryFreeFlow("18737108266");

			System.out.println(JSONObject.toJSONString(re));
		} catch (LIException e) {
			e.printStackTrace();
		}
		logger.info(" ====== 开始返回参数 ======"+re);
		if (re != null)
		{
			logger.info(" ====== getResultCode ======" + re.getResultCode());
			logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());
		}
		
//		getResultCode ======0
//		getErrorMessage ======调用成功
//		getErrorMessage ======Y
//		getErrorMessage ======20160301141108A333702529A
		
//		getResultCode ======0
//		getErrorMessage ======调用成功
//		getErrorMessage ======N
//		getErrorMessage ======该外部流水已被使用

	}
}
