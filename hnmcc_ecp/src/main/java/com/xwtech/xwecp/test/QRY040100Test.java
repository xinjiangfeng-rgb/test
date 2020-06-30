package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryFreeLoginUrlService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryFreeLoginUrlClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY040100Result;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class QRY040100Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY040100Test.class);

	public static void main(String[] args) throws Exception {


		OkHttpClient.Builder builder = new OkHttpClient.Builder().
				connectTimeout(30000, TimeUnit.MILLISECONDS)
				.readTimeout(2000, TimeUnit.MILLISECONDS);
		OkHttpClient okHttpClient = builder.build();
		Request request = new Request.Builder()
				.url("https://www.baidu.com")
				.post(RequestBody.create(MediaType.parse("text/plain"), "ssssssssssss"))
				.build();
		String result = null;
		try {
			Response response = okHttpClient.newCall(request).execute();
			if (!response.isSuccessful()) {
				throw new IOException("服务器端错误: " + response);
			}
			result = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("xxx"+result);

		// 初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");

		XWECPLIClient client = XWECPLIClient.createInstance(props);
		// 逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("动感地带");
		lic.setUserCity("12");
		lic.setUserMobile("18236464534");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "18236464534");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "12");
		ic.addContextParameter("ddr_city", "12");

		ic.addContextParameter("user_id", ""); // 2056200011182291


		lic.setContextParameter(ic);
		IQueryFreeLoginUrlService co = new QueryFreeLoginUrlClientImpl();
//		QRY040100Result re = co.getFreeLoginUrl("15737110188","KHDYHHDCSS");
		//QRY040100Result re = co.getFreeLoginUrl("18803695181","KHDYHHD");
		QRY040100Result re = co.getFreeLoginUrl("15093421454","SHOP_DEFAULT");


		System.out.println(JSONObject.toJSONString(re));
		logger.info(" ====== 开始返回参数 ======");
		logger.info(" ====== 开始返回参数 ======");
		if (re != null) {
			logger.info(" === re.getResultCode() === " + re.getResultCode());
			logger.info(" === re.getErrorCode() === " + re.getErrorCode());
			logger.info(" === re.getErrorMessage() === " + re.getErrorMessage());
			logger.info(" === re.getBossCode() === " + re.getBossCode());
			logger.info(" === re.getBossCode() === " + re.getContent());
		}
	}
}