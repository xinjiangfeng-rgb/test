package com.xwtech.xwecp.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryFluxService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryFluxServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.PkgInfo;
import com.xwtech.xwecp.service.logic.pojo.QRY040046Result;

public class QRY040046Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY040046Test.class);
	
		
	public static void main(String[] args) throws Exception {

		// 初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");

		XWECPLIClient client = XWECPLIClient.createInstance(props);
		// 逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("动感地带");
		lic.setUserCity("12");
		lic.setUserMobile("18236464534");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "18236464534");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "12");
		ic.addContextParameter("ddr_city", "12");

		ic.addContextParameter("user_id", ""); // 2056200011182291

		lic.setContextParameter(ic);
		IQueryFluxService co = new QueryFluxServiceClientImpl();
		QRY040046Result re = co.qryFlux("15837179491","");
		System.out.println(JSONObject.toJSONString(re));
		logger.info(" ====== 开始返回参数 ======");
		logger.info(" ====== 开始返回参数 ======");
		if (re != null) {
			logger.info(" === re.getResultCode() === " + re.getResultCode());
			logger.info(" === re.getErrorCode() === " + re.getErrorCode());
			logger.info(" === re.getErrorMessage() === " + re.getErrorMessage());
			logger.info(" === re.getBossCode() === " + re.getBossCode());
			List<PkgInfo> info = re.getPkgInfoList();
			for (PkgInfo i : info){
				System.out.println(i);
			}
		}
	}
}