package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IFlowGjService;
import com.xwtech.xwecp.service.logic.client_impl.common.IPaymentService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.FlowGjServiceImpl;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.PaymentServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.PAY010001Result;
import com.xwtech.xwecp.service.logic.pojo.QRY161108Result;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import java.util.Properties;

public class QRY161108Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY161108Test.class);

	public static void main(String[] args) {
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
        //props.put("platform.url", "http://localhost:8090/hnmcc_ecp/xwecp.do");
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

		IFlowGjService co = new FlowGjServiceImpl();
		QRY161108Result re = null;
		try {
			re = co.qryFlowGj("18838282627");
			System.out.println(JSONObject.toJSONString(re));
		} catch (LIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(" ====== 开始返回参数 ======"+re);
		if (re != null)
		{
			logger.info(" ====== getResultCode ======" + re.getResultCode());
			logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());
			logger.info(" ====== 国内已用 ======" + re.getGnUsed());
			logger.info(" ====== 国内剩余 ======" + re.getGnLeft());
			logger.info(" ====== 省内已用 ======" + re.getSnUsed());
			logger.info(" ====== 省内剩余 ======" + re.getSnLeft());
			logger.info(" ====== 闲时已用 ======" + re.getXsUsed());
			logger.info(" ====== 闲时剩余 ======" + re.getXsLeft());
			logger.info(" ====== 定向已用 ======" + re.getDxUsed());
			logger.info(" ====== 定向剩余 ======" + re.getDxLeft());
			logger.info(" ====== 当前可用总量 ======" + re.getZll());
			logger.info(" ====== 当前可用已使用 ======" + re.getYsyzl());
			logger.info(" ====== 当前可用剩余 ======" + re.getLeftzl());

			logger.info(" ====== 本月已用总流量 ======" + re.getByyyzll());
			logger.info(" ====== 套餐外 ======" + re.getTw());
			logger.info(" ====== 套餐内 ======" + re.getTn());

			logger.info(" ====== 今日已用 ======" + re.getJryy());
		}
	}
}
