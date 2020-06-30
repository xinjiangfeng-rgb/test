package com.xwtech.xwecp.test;

import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryUserCreditLevelService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryUserCreditLevelServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY040079Result;

/**
 * 用户信用星级评定明细查询
 * @author YangXQ
 * 2014-12-12
 */
public class QRY040079Test
{
	private static final Logger logger = LoggerFactory.getLogger(QRY040079Test.class);
	
	public static void main(String[] args) throws Exception
	{
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
		lic.setUserCity("12"); 
		lic.setUserMobile("13813382424");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "13813382424");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "14");
		ic.addContextParameter("ddr_city", "14");	
		ic.addContextParameter("user_id", "");  		
		lic.setContextParameter(ic);

		
		IQueryUserCreditLevelService is = new QueryUserCreditLevelServiceClientImpl();
		QRY040079Result re = is.getQueryUserCreditLevel("18236464534");//
		
		logger.info(" ====== 开始返回参数 ======");
		if(null != re){
			logger.info(" ====== getResultCode ======" + re.getResultCode());
			logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());
			logger.info(" ====== getErrorMessage ======" + re.getCreditLevel());
			logger.info(" ====== getErrorMessage ======" + re.getCreditLimit());
			logger.info(" ====== getErrorMessage ======" + re.getState());
			logger.info(" ====== getErrorMessage ======" + re.getMonth());
		}
		
	}	
}
