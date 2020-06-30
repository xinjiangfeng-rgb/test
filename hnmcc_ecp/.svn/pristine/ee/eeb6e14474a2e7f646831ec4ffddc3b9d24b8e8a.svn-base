package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryJfRecommendService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryJfRecommendServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY060001Result;
import com.xwtech.xwecp.service.logic.pojo.RecommendDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

public class QRY060001Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY060001Test.class);

	public static void main(String[] args) {
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
//		props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
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
//		RECO20170320YWBLJG1
//				RECO20170321110510SYCL
//		RECO20170320JFSY
//				RECO20170320LLGJHD2
//		RECO20170320LLGJCP1
//				RECO20170320B1
		IQueryJfRecommendService co = new QueryJfRecommendServiceImpl();
		QRY060001Result result = null;
		try {
			// RECO20170301173113RMTHREE
			result = co.qryRecommend("15137177173","371","{\"PropertyItem\":{\"PropertyName\": \"CatalogId\",\"PropertyValue\": \"RECO20170320B1\"}}");
			System.out.println(JSONObject.toJSONString(result));
// result = co.qryRecommend("15890158325","371","{\"PropertyItem\":{\"PropertyName\": \"\",\"PropertyValue\": \"\"}}");
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
				RecommendDetail re = (RecommendDetail)list.get(i);
				logger.info(" ====== getCatalogId ======" + re.getCatalogId());
				logger.info(" ====== getCatalogName ======" + re.getCatalogName());
				logger.info(" ====== getMessageSn ======" + re.getMessageSn());
				logger.info(" ====== getOfferId ======" + re.getOfferId());
				logger.info(" ====== getOrderNo ======" + re.getOrderNo());
			}
		}
	}
}
