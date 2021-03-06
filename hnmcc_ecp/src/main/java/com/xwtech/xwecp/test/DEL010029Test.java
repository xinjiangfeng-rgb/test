package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IZZCPBService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.ZengZhiChanPinServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.DEL010029Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class DEL010029Test {
	private static final Logger logger = LoggerFactory.getLogger(DEL010029Test.class);

	public static void main(String[] args) {
		// 初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
		// props.put("platform.url",
		// "http://localhost:8080/hnmcc_ecp/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");

		XWECPLIClient client = XWECPLIClient.createInstance(props);
		// 逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("12");
		lic.setUserCity("12");
		lic.setUserMobile("15890041910");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "15890041910");
		ic.addContextParameter("route_type", "2");
		ic.addContextParameter("route_value", "15890041910");

		lic.setContextParameter(ic);

		IZZCPBService co = new ZengZhiChanPinServiceImpl();
		DEL010029Result re = null;
		try {
			String phone = "18236464534";
			String bossCode = "100168000750";
			int oprType = 1;
			// HFTX_MZYC(每周一次),HFTX_MLTYC(每两天一次),HFTX_MTYC(每天一次)
			re = co.transactZZCP(phone, bossCode, "HFTX_MZYC", oprType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(" ====== 开始返回参数 ======" + re);
		if (re != null) {
			logger.info(" ====== getResultCode ======" + re.getResultCode());
			logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());
		}
		System.out.println(JSONObject.toJSONString(re));
	}
}
