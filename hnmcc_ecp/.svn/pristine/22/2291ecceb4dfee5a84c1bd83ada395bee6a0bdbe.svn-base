package com.xwtech.xwecp.test;

import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IPaymentService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.PaymentServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.PAY010001Result;

public class PAY010001Test {
	private static final Logger logger = LoggerFactory.getLogger(PAY010001Test.class);

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
		
		IPaymentService co = new PaymentServiceImpl();
		PAY010001Result re = null;
		/*try {
			re = co.payment("15736713066", "A", "3", "0", "10000", "600", "GJZFBS", "GJZFBD", "M", "M", "AMTBXWH2016122713402984446324210", "AMTBXWH2016122713402984446324210", "20161227134129");
			//re = co.payment("15890158325", "A", "3", "0", "1", "0", "GJZFBS", "GJZFBD", "M", "M", "A4TBXWH2016040814204122225737039", "A4TBXWH2016040814204122225737039", "20160408142041");
		} catch (LIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		logger.info(" ====== 开始返回参数 ======"+re);
		if (re != null)
		{
			logger.info(" ====== getResultCode ======" + re.getResultCode());
			logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());
			logger.info(" ====== getErrorMessage ======" + re.getIsOk());
			logger.info(" ====== getErrorMessage ======" + re.getPaymentId());
			
			logger.info(" ====== getPhoneNum ======" + re.toString());
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
