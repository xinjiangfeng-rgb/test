package com.xwtech.xwecp.test;

import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IPaymentMixService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.PaymentMixServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.PAY010002Result;

public class PAY010002Test {

	private static final Logger logger = LoggerFactory.getLogger(PAY010002Test.class);
	
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
		
		//20160217102800001---25位
		IPaymentMixService co = new PaymentMixServiceImpl();
		PAY010002Result re = null;
		try {
			re = co.paymentMix("15890158325", "2016040623321989095103851", "0", "0", "", "GJFES", "2", "420002184985", "", "10", "false", "15890158325", "测试混合支付10");
		} catch (LIException e) {
			e.printStackTrace();
		}
		logger.info(" ====== 开始返回参数 ======"+re);
		if (re != null)
		{
			logger.info(" ====== getResultCode ======" + re.getResultCode());
			logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());
			
			logger.info(" ====== getPhoneNum ======" + re.toString());
		}
		
//		getResultCode ======1
//		getErrorMessage ======缴费记录重复，peerSeq=2016021710280000100000001,optseq=2
		
//		getResultCode ======1
//		getErrorMessage ======*******用户15890158325扣积分失败******
	}
}
