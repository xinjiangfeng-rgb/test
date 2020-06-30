package com.xwtech.xwecp.test;

import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.ICheckPasswordService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.CheckPasswordServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY040003Result;

public class QRY040003Test
{
	private static final Logger logger = LoggerFactory.getLogger(QRY040003Test.class);
	
	public static void main(String[] args) throws Exception
	{
		
			//初始化ecp客户端片段
			Properties props = new Properties();
			props.put("client.channel", "hnmcc_channel");
			props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
			props.put("platform.user", "hnmcc");
			props.put("platform.password", "hnmcc");
			
			XWECPLIClient.createInstance(props);
			//逻辑接口调用片段
			LIInvocationContext lic = LIInvocationContext.getContext();
			lic.setBizCode("biz_code_19234");
			lic.setOpType("开通/关闭/查询/变更");
			lic.setUserBrand("动感地带");
			lic.setUserCity("用户县市");
			lic.setUserMobile("18236464534");
			InvocationContext ic = new InvocationContext();
			ic.addContextParameter("login_msisdn", "18236464534");
			ic.addContextParameter("route_type", "2");
			ic.addContextParameter("route_value", "18236464534");
			ic.addContextParameter("ddr_city", "14");
			
			ic.addContextParameter("user_id", "1738200005062065");  //2056200011182291
			
			lic.setContextParameter(ic);
			ICheckPasswordService checkPasswordService = new CheckPasswordServiceClientImpl();
			QRY040003Result result = checkPasswordService.checkPassword("13937162580", "");
			if (result != null)
			{
				logger.info(" ====== getResultCode ======" + result.getResultCode());
				logger.info(" ====== getErrorCode ======" + result.getErrorCode());
				logger.info(" ====== getErrorMessage ======" + result.getErrorMessage());
				logger.info(" ====== getBossCode ======" + result.getBossCode());
			}
		
	}
}
