package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryJfLableService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryJfLableServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.LableDetail;
import com.xwtech.xwecp.service.logic.pojo.QRY060002Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

public class QRY060002Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY060002Test.class);

	public static void main(String[] args) {
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
//		props.put("platform.url", "http://127.0.0.1:8081/hnmcc_ecp/xwecp.do");

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

		IQueryJfLableService co = new QueryJfLableServiceImpl();
		QRY060002Result result = null;
		try {
			result = co.qryLables("18737108266", "371","MOBILE2_HALL");
			System.out.println(JSON.toJSONString(result));
		} catch (LIException e) {
			e.printStackTrace();
		}
		logger.info(" ====== 开始返回参数 ======"+result);
		if(result != null){
			logger.info(" ====== getResultCode ======" + result.getResultCode());
			logger.info(" ====== getErrorMessage ======" + result.getErrorMessage());
			logger.info(" =============================================");
			List list = result.getPkgInfoList();
			for (int i = 0; i < list.size(); i++) {
				LableDetail re = (LableDetail)list.get(i);
				logger.info(" ====== getPropertyId ======" + re.getPropertyId());
				logger.info(" ====== getPropertyName ======" + re.getPropertyName());
				logger.info(" ====== getPropertyOriginalValue ======" + re.getPropertyOriginalValue());
				logger.info(" ====== getPropertyType ======" + re.getPropertyType());
				logger.info(" ====== getPropertyValue ======" + re.getPropertyValue());
			}
		}
	}
}
