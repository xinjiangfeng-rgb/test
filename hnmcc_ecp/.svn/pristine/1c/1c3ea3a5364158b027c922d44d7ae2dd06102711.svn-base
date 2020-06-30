package com.xwtech.xwecp.test;

import java.util.Properties;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryGPRSPkgFluxService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryGPRSPkgFluxServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY040045Result;

public class QRY040045Test
{
	private static final Logger logger = LoggerFactory.getLogger(QRY040045Test.class);
	
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
		lic.setUserCity("12");
		lic.setUserMobile("13505239567");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "13505239567");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "12");
		ic.addContextParameter("ddr_city", "12");
		ic.addContextParameter("user_id", "1211200010165511");
		
		lic.setContextParameter(ic);
		//
		
		IQueryGPRSPkgFluxService co = new QueryGPRSPkgFluxServiceClientImpl();
		//for(int i=0;i<100;i++){
			QRY040045Result re = co.qryGPRSPkgFlux("13603860707");
		System.out.println(JSON.toJSONString(re));
			//System.out.println(i+" ====== 开始返回参数 ======");
			if (re != null)
			{
				System.out.println(" ====== getResultCode ======" + re.getResultCode());
				System.out.println(re.toString());

			}
		//}
		
	}
}
