package com.xwtech.xwecp.test;

import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IFWBService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.FuWuBaoServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.DEL010031Result;

public class DEL010031Test {
	private static final Logger logger = LoggerFactory.getLogger(DEL010031Test.class);

	public static void main(String[] args) {
		// 初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		// props.put("platform.url",
		// "http://localhost:8080/hnmcc_ecp/xwecp.do");
		props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");

		XWECPLIClient client = XWECPLIClient.createInstance(props);
		// 逻辑接口调用片段
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

		IFWBService co = new FuWuBaoServiceImpl();
		DEL010031Result re = null;
		try {
			re = co.transactFWB("15890158325", "15890158325", 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(" ====== 开始返回参数 ======" + re);
		if (re != null) {
			logger.info(" ====== getResultCode ======" + re.getResultCode());
			logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());
		}
	}
}
