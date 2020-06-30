package com.xwtech.xwecp.test;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryScoreExchangeDetailService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryScoreExchangeDetailServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY030003Result;
import com.xwtech.xwecp.service.logic.pojo.ScoreExchange;

public class QRY030003Test {
private static final Logger logger = LoggerFactory.getLogger(QRY040004Test.class);
	
	public static void main(String[] args) throws Exception
	{
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
		lic.setUserBrand("动感地带");
		lic.setUserCity("14");
		lic.setUserMobile("13913814503");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("contextParameter", "12345");
		ic.addContextParameter("testParameter", 54321);
		ic.addContextParameter("login_msisdn", "13913814503");
		ic.addContextParameter("route_type", "2");
		ic.addContextParameter("route_value", "13913814503");
		ic.addContextParameter("ddr_city", "14");
		
		ic.addContextParameter("user_id", "");
		
		lic.setContextParameter(ic);
		
		IQueryScoreExchangeDetailService service = new QueryScoreExchangeDetailServiceClientImpl();
		QRY030003Result res = service.queryScoreExchangeDetail("18803695181", "20111218", "20150324");
		List<ScoreExchange> scoreExchangeList = res.getScoreExchange();
		
		logger.info(" ====== 开始返回参数 ======");
		if (res != null)
		{
			logger.info(" === re.getResultCode() === " + res.getResultCode());
			logger.info(" === re.getErrorCode() === " + res.getErrorCode());

			logger.info(" === re.getErrorMessage() === " + res.getErrorMessage());
			for(ScoreExchange obj:res.getScoreExchange()){
				System.out.println(obj.toString());
			}
			
		}
		
		
	}
	
	
	
}
