package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryMonthBillService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryMonthBillServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.MonthBill;
import com.xwtech.xwecp.service.logic.pojo.QRY161203Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

public class QRY161203Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY161203Test.class);

	public static void main(String[] args) {
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
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

		IQueryMonthBillService co = new QueryMonthBillServiceImpl();
		QRY161203Result result = null;
		try {
			result = co.queryMonthBill("18737183066","202005");
			System.out.println(JSON.toJSONString(result));
		} catch (LIException e) {
			e.printStackTrace();
		}
		logger.info(" ====== 开始返回参数 ======"+JSONObject.toJSONString(result));
		if(result != null){
			logger.info(" ====== getResultCode ======" + result.getResultCode());
			logger.info(" ====== getErrorMessage ======" + result.getErrorMessage());
			List<MonthBill> payHistoryNewLists = result.getMonthFluxsList();
			if (payHistoryNewLists != null && payHistoryNewLists.size()>0) {
				for (int i=0 ;i<payHistoryNewLists.size();i++){
					MonthBill re = payHistoryNewLists.get(i);
					logger.info(" ====== getMonth ======" + re.getMonth());
					logger.info(" ====== getFlow ======" + re.getFlow());
					logger.info(" ====== getUtil ======" + re.getUtil());
					logger.info(" =============================================================");
				}
			}
		}
	}
}
