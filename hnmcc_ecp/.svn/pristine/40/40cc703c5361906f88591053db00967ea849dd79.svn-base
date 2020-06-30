package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryZXYZInfoService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryZXYZInfoServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY020022Result;
import org.apache.log4j.Logger;

import java.util.Properties;

public class QRY020022Test {
	private static final Logger logger = Logger.getLogger(QRY020022Test.class);

	/**
	 * 专项月租
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		// props.put("platform.url", "http://127.0.0.1:8080/obsh_ecp/xwecp.do");
		props.put("platform.url",
				"http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");

		XWECPLIClient client = XWECPLIClient.createInstance(props);
		// 逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("1");
		lic.setUserCity("12");
		lic.setUserMobile("13915170950");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "13838268859");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "12");

		lic.setContextParameter(ic);


		IQueryZXYZInfoService co = new QueryZXYZInfoServiceClientImpl();
		QRY020022Result re = co.queryZXYZInfo("15093421454");
		System.out.println(JSONObject.toJSONString(re));
		logger.info(" ====== 开始返回参数 ======");
		if (re != null) {
			logger.info(" ====== getResultCode ======" + re.getResultCode());
			logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());
		}
	}
}
