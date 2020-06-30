package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IInternationalBasicService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.InternationalBasicServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.DEL200240Result;
import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * 国际长途、国际漫游基础业务办理接口
 */
public class DEL200240Test {
	private static final Logger logger = Logger.getLogger(DEL200240Test.class);
	
	public static void main(String[] args) throws Exception
	{
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
//		props.put("platform.url", "http://localhost:8082/hnmcc_ecp/xwecp.do");

		props.put("platform.user", "hnmcc");
        props.put("platform.password", "hnmcc");
		
		XWECPLIClient client = XWECPLIClient.createInstance(props);
		//逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("12");
		lic.setUserCity("12");
		lic.setUserMobile("15252318843");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "15252318843");
		ic.addContextParameter("route_type", "2");
		ic.addContextParameter("route_value", "15996190955");

		lic.setContextParameter(ic);

		IInternationalBasicService co = new InternationalBasicServiceImpl();
		long l1 = System.currentTimeMillis();
		DEL200240Result re = co.internationalBasicBusinessTransaction("15093421454", "ADD", 	"","100165000186","1","","","","2018-10-26 15:20:30","2018-11-30 23:59:59","5");
		long l2 = System.currentTimeMillis();
		System.out.println(l2 -l1);
		System.out.println(JSONObject.toJSONString(re));
		logger.info(" ====== 开始返回参数 ======"+re);
		if (re != null)
		{
			logger.info(" ====== getResultCode ======" + re.getResultCode());
			logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());
			logger.info(" ====== getResult ======" + re.toString());
		}
	}
}
