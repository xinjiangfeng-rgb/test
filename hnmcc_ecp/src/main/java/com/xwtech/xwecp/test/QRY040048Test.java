package com.xwtech.xwecp.test;

import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryGPSFluxInfoService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryGPSFluxInfoServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.FluxUsedInfo;
import com.xwtech.xwecp.service.logic.pojo.QRY040048Result;

public class QRY040048Test {
	
	private static final Logger logger = LoggerFactory.getLogger(QRY040048Test.class);
	
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
		lic.setUserMobile("13813382746");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "13813382746");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "14");
		ic.addContextParameter("ddr_city", "14");
		ic.addContextParameter("user_id", "1419200010139095");
		
		lic.setContextParameter(ic);
		
		IQueryGPSFluxInfoService co = new QueryGPSFluxInfoServiceClientImpl();
		QRY040048Result re = co.queryGPSFluxInfo("18768865951", "201507");
		System.out.println(" ====== 开始返回参数 ======");
		if (re != null)
		{
			System.out.println(" ====== getResultCode ======" + re.getResultCode());
			System.out.println(" ====== getErrorMessage ======" + re.getErrorMessage());
			for(FluxUsedInfo flux:re.getFluxUsedInfoList()){
				System.out.println("------------");
				System.out.println(flux.getItemName()+"---------"+flux.getFreeRes());
			}
		}
	}
}