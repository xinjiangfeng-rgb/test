package com.xwtech.xwecp.test;

import java.util.Properties;

import com.xwtech.xwecp.service.logic.LIException;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryCurrentDayFeeService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryCurrentDayFeeServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY010097Result;

public class QRY010097Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY010097Test.class);
	
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
//		props.put("platform.url", "http://127.0.0.1:8080/hnmcc_ecp/xwecp.do");

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
		
		IQueryCurrentDayFeeService co = new QueryCurrentDayFeeServiceClientImpl();
		QRY010097Result re = null;
		try {
			re = co.queryCurrentDayFee("15238006703");
		} catch (LIException e) {
			e.printStackTrace();
		}
		 logger.info(" ====== 开始返回参数 ======"); 
		if (re != null)
		{
			logger.info(" ====== getResultCode ======" + re.getResultCode());
			logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());
			logger.info(" ====== get ======" + re.getBossCode());
			logger.info(" ====== daiFuFee ======" + re.getDaiFuFee());
			logger.info(" ====== geRenFee ======" + re.getGeRenFee());
		}
		System.out.println(JSONObject.toJSONString(re));
	}
}
