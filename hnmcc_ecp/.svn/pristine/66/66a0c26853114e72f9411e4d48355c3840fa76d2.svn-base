package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryFlowGeneralSituationService;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryPayListNewService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryFlowGeneralSituationServiceImpl;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryPayListNewServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.PayHistoryNewList;
import com.xwtech.xwecp.service.logic.pojo.QRY040205Result;
import com.xwtech.xwecp.service.logic.pojo.QRY161204Result;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

public class QRY161204Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY161204Test.class);

	public static void main(String[] args) {
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
		lic.setUserBrand("12");
		lic.setUserCity("12");
		lic.setUserMobile("15890158325");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "15890158325");
		ic.addContextParameter("route_type", "2");
		ic.addContextParameter("route_value", "15890158325");
		
		
		lic.setContextParameter(ic);

		IQueryFlowGeneralSituationService co = new QueryFlowGeneralSituationServiceImpl();
		QRY161204Result result = null;
		try {
			result = co.queryFlowGeneralSituation("13603860707");
			System.out.println(JSON.toJSONString(result));
		} catch (LIException e) {
			e.printStackTrace();
		}
		logger.info(" ====== 开始返回参数 ======"+result);
		if(result != null){
			logger.info(" ====== getResultCode ======" + result.getResultCode());
			logger.info(" ====== getErrorMessage ======" + result.getErrorMessage());
			logger.info(" =============================================================");
			logger.info(" ====== getTotalFlow ======" + result.getTotalFlow());
			logger.info(" ====== getUsedFlow ======" + result.getUsedFlow());
			logger.info(" ====== getDayFlow ======" + result.getDayFlow());
		}
	}
}
