package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryFTYCInfoService;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryZKYCInfoService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryFTYCInfoServiceClientImpl;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryZKYCInfoServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.Apportionment;
import com.xwtech.xwecp.service.logic.pojo.QRY020023Result;
import com.xwtech.xwecp.service.logic.pojo.QRY020024Result;
import com.xwtech.xwecp.service.logic.pojo.SpePayment;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

public class QRY020023Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY020023Test.class);

	/**
	 * 查询用户信息
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		// props.put("platform.url", "http://127.0.0.1:8080/obsh_ecp/xwecp.do");
		props.put("platform.url",
				"http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");

		XWECPLIClient client = XWECPLIClient.createInstance(props);
		// 逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("1");
		lic.setUserCity("12");
		lic.setUserMobile("13915170950");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "13838268859");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "12");

		lic.setContextParameter(ic);

		IQueryZKYCInfoService co = new QueryZKYCInfoServiceClientImpl();
		QRY020023Result re = co.queryZKYCInfo("18737108266","1");
		System.out.println(JSONObject.toJSONString(re));
		logger.info(" ====== 开始返回参数 ======");
		if (re != null) {
			logger.info(" ====== getResultCode ======" + re.getResultCode());
			logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());

			List list = re.getSpePayments();
			for(int i=0;i<list.size();i++){
				SpePayment apportionment = (SpePayment)list.get(i);
				logger.info(" ====== getErrorMessage ======" + apportionment.getSpe_payment_id());
				logger.info(" ====== getErrorMessage ======" + apportionment.getSpepaymentname());
				logger.info(" ====== getErrorMessage ======" + apportionment.getGet_date());
				logger.info(" ====== getErrorMessage ======" + apportionment.getGet_amount());
				logger.info(" ====== getErrorMessage ======" + apportionment.getEff_date());
				logger.info(" ====== getExp_date ======" + apportionment.getExp_date());
				logger.info(" ====== getErrorMessage ======" + apportionment.getRemain_amount());
				logger.info(" ====== getErrorMessage ======" + apportionment.getProd_pric_id());
				logger.info(" ====== getErrorMessage ======" + apportionment.getProdpricname());
			}
		}
	}
}
