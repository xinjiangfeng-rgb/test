package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IGetCheckCodeService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.GetCheckCodeServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY040094Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class QRY040094Test
{
	private static final Logger logger = LoggerFactory.getLogger(QRY040094Test.class);
	
	public static void main(String[] args) throws Exception
	{
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
//		props.put("platform.url", "http://192.168.16.51:8080/hnmcc_ecp/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");
		
		XWECPLIClient.createInstance(props);
		//逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("动感地带");
		lic.setUserCity("用户县市");
		lic.setUserMobile("15890010099");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "18236464534");
		ic.addContextParameter("route_type", "2");
		ic.addContextParameter("route_value", "18236464534");
		ic.addContextParameter("ddr_city", "14");
		
		ic.addContextParameter("user_id", "1738200005062065");  //2056200011182291
		
		lic.setContextParameter(ic);
		
		IGetCheckCodeService checkCodeService = new GetCheckCodeServiceImpl();
		QRY040094Result result = checkCodeService.getCheckCode("18839042192","尊敬的客户，您本次掌上营业厅缴费的短信验证码是%e。工作人员不会向您索取该短信密码，请注意保密，如非本人操作请忽略。");
		System.out.println(JSONObject.toJSONString(result));
		//		ICheckPasswordService checkPasswordService = new CheckPasswordServiceClientImpl();
//		QRY040003Result result = checkPasswordService.checkPassword("18236464534", "123123");
		//动感地带 13401312424  brand_id：11 city_id：17 user_id：1738200005062065
		//全球通 13913032424 user_id：1419200008195160
		/*
		QRY040003Result result = checkPasswordService.checkPassword(phoneNum, password);
		logger.info(" ====== 开始返回参数 ======");
		 */
		if (result != null)
		{
			logger.info(" ====== getResultCode ======" + result.getResultCode());
			logger.info(" ====== getErrorCode ======" + result.getErrorCode());
			logger.info(" ====== getErrorMessage ======" + result.getErrorMessage());
			logger.info(" ====== getBossCode ======" + result.getBossCode());
			logger.info(" ====== getCheckCode ======" + result.getCheckCode());
		}
	}
}
