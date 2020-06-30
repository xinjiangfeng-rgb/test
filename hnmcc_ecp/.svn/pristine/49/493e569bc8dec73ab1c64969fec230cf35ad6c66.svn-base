package com.xwtech.xwecp.test;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryScoreExchangeDetailNewService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryScoreExchangeDetailNewServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY040206Result;
import com.xwtech.xwecp.service.logic.pojo.ScoreExchangeNew;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

public class QRY040206Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY040206Test.class);

	public static void main(String[] args) {
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
//        props.put("platform.url", "http://127.0.0.1:8080/hnmcc_ecp/xwecp.do");
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
		IQueryScoreExchangeDetailNewService co = new QueryScoreExchangeDetailNewServiceImpl();
		QRY040206Result result = null;
		try {
			result = co.queryScoreExchangeDetail("15890158325","2016-03-01","2016-08-10");
		} catch (LIException e) {
			e.printStackTrace();
		}
		logger.info(" ====== 开始返回参数 ======"+ result);
		if (result != null) {
			logger.info(" ====== getResultCode ======" + result.getResultCode());
			logger.info(" ====== getErrorMessage ======" + result.getErrorMessage());

			List<ScoreExchangeNew> scoreExchangeNews = result.getScoreExchangeNewList();
			for (int i=0;i<scoreExchangeNews.size();i++){
				ScoreExchangeNew re = scoreExchangeNews.get(i);
				logger.info(" ====== getOptDate ======" + re.getOptDate());
				logger.info(" ====== getScore ======" + re.getScore());
				logger.info(" ====== getOptCode ======" + re.getOptCode());
				logger.info(" ====== getOptName ======" + re.getOptName());
				logger.info(" ====== getType ======" + re.getType());
				logger.info(" ====== getEffDate ======" + re.getEffDate());
				logger.info(" ====== getExpDate ======" + re.getExpDate());
				logger.info(" ====== getRemark ======" + re.getRemark());
			}
		}
	}
}
