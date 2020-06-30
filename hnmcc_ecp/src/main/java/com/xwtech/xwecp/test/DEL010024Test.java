package com.xwtech.xwecp.test;

import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IaddMemberService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.AddMemberServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.DEL010024Result;

/**
 * 在线入网订单实名制图片更新接口
 * 
 * @author YangXQ 2014-10-24
 */
public class DEL010024Test {
	private static final Logger logger = LoggerFactory.getLogger(DEL010024Test.class);

	public static void main(String[] args) throws Exception {
		// 初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url",
				"http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");

		XWECPLIClient client = XWECPLIClient.createInstance(props);
		// 逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("动感地带");
		lic.setUserCity("12");
		lic.setUserMobile("");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "12");
		ic.addContextParameter("user_id", "");
		lic.setContextParameter(ic);// 13641582424 - 2157200003124230
		IaddMemberService co = new AddMemberServiceClientImpl();
		DEL010024Result re = co.addMember("18803693936", "93713167205", "1",
				"H");
		logger.info(" ====== 开始返回参数 ======");
		if (re != null) {
			logger.info(" ====== " + re.getResultCode());
			logger.info(" ====== " + re.getErrorMessage());
			logger.info(" ====== " + re.getErrorCode());

			logger.info(" === re.getRet_code()   === " + re.getRet_code());
			logger.info(" === re.getRet_msg()    === " + re.getRet_msg());
		}
		System.out.println("4.3.2".substring(0,3));
	}
}
