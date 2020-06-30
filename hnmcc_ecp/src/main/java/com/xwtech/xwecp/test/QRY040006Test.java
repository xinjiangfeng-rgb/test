package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IChangeUserPassService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.ChangeUserPassServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY040006Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
/**
 * 
 * 密码重置
 *
 */
public class QRY040006Test
{
	private static final Logger logger = LoggerFactory.getLogger(QRY040006Test.class);
	
	public static void main(String[] args) throws Exception
	{
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
//		props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");
		XWECPLIClient client = XWECPLIClient.createInstance(props);

		//逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("动感地带");
		lic.setUserCity("用户县市");
		lic.setUserMobile("18236464534");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "18236464534");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "14");
		ic.addContextParameter("ddr_city", "14");
		ic.addContextParameter("context_loginiplock_login_ip", "127.0.0.1");
		ic.addContextParameter("user_id", "1423200000471569");  //2056200011182291
		lic.setContextParameter(ic);
		//组装自有业务的list
		IChangeUserPassService co = new ChangeUserPassServiceClientImpl();
//		List<String> phone = Arrays.asList("15737110177", "15136132828", "18236751288", "15838013030", "15138695299", "18703605556", "15093329986", "13703715898");
//		for (String p : phone) {
			QRY040006Result re = co.changeUserPass("13526802996", "WwWwWw", "321123");
			System.out.println(JSONObject.toJSONString(re));
//		}

	}
}
