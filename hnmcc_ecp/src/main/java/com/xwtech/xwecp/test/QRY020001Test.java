package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryBusinessService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryBusinessServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.GommonBusiness;
import com.xwtech.xwecp.service.logic.pojo.QRY020001Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class QRY020001Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY020001Test.class);

	public static void main(String[] args) throws Exception {
		// 初始化ecp客户端片段
		Properties props = new Properties();
		// props.put("client.channel", "openapi_channel");
		props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
//		props.put("platform.url", "http://127.0.0.1:8080/hnmcc_ecp/xwecp.do");
		// props.put("platform.url",
		// "http://127.0.0.1:8080/hnmcc_ecp/xwecp.do");
		// props.put("platform.user", "fgh");
		// props.put("platform.password", "fgh");

		props.put("client.channel", "hnmcc_channel");
		// props.put("platform.url",
		// "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");

		XWECPLIClient client = XWECPLIClient.createInstance(props);
		// 逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("动感地带");
		lic.setUserCity("12");
		lic.setUserMobile("13693710088");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "15252402978");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "12");
		ic.addContextParameter("ddr_city", "12");

		ic.addContextParameter("user_id", ""); // 2056200011182291

		lic.setContextParameter(ic);
		try {
			IQueryBusinessService co = new QueryBusinessServiceClientImpl();

			QRY020001Result re = co.queryBusiness("15093421454", 2, "findyxhd");
//			QRY020001Result re = co.queryYxhdNoCache("13837123168");
			System.out.println(JSONObject.toJSONString(re));

			logger.info(" ====== 开始返回参数 ======");
			if (re != null) {
				logger.info(" ====== getResultCode ======" + re.getResultCode());
				logger.info(" ====== getErrorCode ======" + re.getErrorCode());
				logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());
				if (null != re.getGommonBusiness() && re.getGommonBusiness().size() > 0) {
					logger.info(" ====== getGommonBusiness.size ======" + re.getGommonBusiness().size());
					for (GommonBusiness g : re.getGommonBusiness()) {
						logger.info(" ====== g.id ======" + g.getId());
						logger.info(" ====== g.getName() ====== " + g.getName());
						logger.info(" ====== g.getState() ====== " + g.getState());
						logger.info(" ====== g.getBeginDate ======" + g.getBeginDate());
						logger.info(" ====== g.getEndDate() ====== " + g.getEndDate());
						logger.info(" ====== g.getReserve1 ======" + g.getReserve1());
						logger.info(" ====== g.getReserve2() ====== " + g.getReserve2());
						logger.info(" ====== g.getIsDefault() ====== " + g.getIsDefault());
						logger.info(" =========================================== ");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
