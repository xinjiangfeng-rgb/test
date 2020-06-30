package com.xwtech.xwecp.test;

import java.util.List;
import java.util.Properties;


import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryMGroupInfoService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryMGroupInfoClientImpl;
import com.xwtech.xwecp.service.logic.pojo.MGroupInfo;
import com.xwtech.xwecp.service.logic.pojo.QRY040097Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QRY040097Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY040097Test.class);
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
		IQueryMGroupInfoService co = new QueryMGroupInfoClientImpl();
		// 动感地带 13401312424 brand_id：11 city_id：17 user_id：1738200005062065
		// 全球通 13913032424 user_id：1419200008195160
		QRY040097Result re = co.queryMGroupInfo("18703845086");

		logger.info(" ====== 开始返回参数 ======");
		logger.info(" ====== 开始返回参数 ======");
		if (re != null) {
			logger.info(" === re.getResultCode() === " + re.getResultCode());
			logger.info(" === re.getErrorCode() === " + re.getErrorCode());
			logger.info(" === re.getErrorMessage() === " + re.getErrorMessage());
			logger.info(" === re.getBossCode() === " + re.getBossCode());
			List<MGroupInfo> info = re.getMGroupInfoList();
			for (MGroupInfo i : info){
				System.out.println(i);
			}
		}
	}
}