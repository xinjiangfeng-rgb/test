package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryFTYCInfoService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryFTYCInfoServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.Apportionment;
import com.xwtech.xwecp.service.logic.pojo.QRY020024Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

public class QRY020024Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY020024Test.class);

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
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
		// props.put("platform.url",
		// "http://10.32.229.82:10008/sms_ecp/xwecp.do");
		// props.put("platform.url",
		// "http://10.32.122.166:10009/js_ecp/xwecp.do");
		// props.put("platform.url",
		// "http://10.32.65.238:8081/sms_ecp/xwecp.do");
		// props.put("platform.url", "http://10.32.65.238/js_ecp/xwecp.do");
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

		IQueryFTYCInfoService co = new QueryFTYCInfoServiceClientImpl();
		QRY020024Result re = co.queryFTYCInfo("18236464534");
		System.out.println(JSON.toJSONString(re));
		logger.info(" ====== 开始返回参数 ======");
		if (re != null) {
			logger.info(" ====== getResultCode ======" + re.getResultCode());
			logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());

			List list = re.getApportionments();
			for(int i=0;i<list.size();i++){
				Apportionment apportionment = (Apportionment)list.get(i);
				logger.info(" ====== getErrorMessage ======" + apportionment.getItemDesc());
				logger.info(" ====== getErrorMessage ======" + apportionment.getAmount());
				logger.info(" ====== getErrorMessage ======" + apportionment.getOvermount());
				logger.info(" ====== getErrorMessage ======" + apportionment.getInterval());
				logger.info(" ====== getErrorMessage ======" + apportionment.getValidApportion());
				logger.info(" ====== getErrorMessage ======" + apportionment.getBeginDate());
				logger.info(" ====== getErrorMessage ======" + apportionment.getEndDate());
				logger.info(" ====== getErrorMessage ======" + apportionment.getTimes());
				logger.info(" ====== getErrorMessage ======" + apportionment.getPeriod());

				logger.info(" ====== getErrorMessage ======" + apportionment.getAcctItem());
				logger.info(" ====== getErrorMessage ======" + apportionment.getAcctFlag());
				logger.info(" ====== getErrorMessage ======" + apportionment.getOperDate());
				logger.info(" ====== getErrorMessage ======" + apportionment.getA_name());
				logger.info(" ====== getErrorMessage ======" + apportionment.getM_amount());
				logger.info(" ====== getErrorMessage ======" + apportionment.getBindDesc());
			}
		}
	}
}
