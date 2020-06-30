package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryPayListNewService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryPayListNewServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.PayHistoryNewList;
import com.xwtech.xwecp.service.logic.pojo.QRY040205Result;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

public class QRY040205Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY040205Test.class);
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
		IQueryPayListNewService co = new QueryPayListNewServiceImpl();
		QRY040205Result result = null;
		try {
			result = co.queryPayHistory("17639153514","20190901","20191130");
			System.out.println(JSONObject.toJSONString(result));
		} catch (LIException e) {
			e.printStackTrace();
		}
		logger.info(" ====== 开始】返回参数 ======"+result);
		if(result != null){
			logger.info(" ====== getResultCode ======" + result.getResultCode());
			logger.info(" ====== getErrorMessage ======" + result.getErrorMessage());
			List<PayHistoryNewList> payHistoryNewLists = result.getPayHistoryNewLists();
			if (payHistoryNewLists != null && payHistoryNewLists.size()>0) {
				for (int i=0 ;i<payHistoryNewLists.size();i++){
					PayHistoryNewList re = payHistoryNewLists.get(i);
					logger.info(" ====== getPayType ======" + re.getPayType());
					logger.info(" ====== getCustName ======" + re.getCustName());
					logger.info(" ====== getPayFee ======" + re.getPayFee());
					logger.info(" ====== getOperator ======" + re.getOperator());
					logger.info(" ====== getOperatTime ======" + re.getOperatTime());
					//logger.info(" ====== getRemark ======" + re.getRemark());
					logger.info(" ====== getOutId ======" + re.getOutId());
					logger.info(" ====== getCertificateType ======" + re.getCertificateType());
					logger.info(" ====== getPayPath ======" + re.getPayPath());
					logger.info(" ====== getOptCode ======" + re.getOptCode());
					logger.info(" =============================================================");
				}
			}
		}
	}
}
