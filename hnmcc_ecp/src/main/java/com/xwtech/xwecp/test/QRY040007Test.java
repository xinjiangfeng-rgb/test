package com.xwtech.xwecp.test;

import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryPukService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryPukServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY040007Result;

public class QRY040007Test
{
	private static final Logger logger = LoggerFactory.getLogger(QRY040007Test.class);
	
	/**
	 * 新大陆提供的密钥，需要每两位转成1个字节
	 */
	private static byte[] BOSS_SECRET_KEY = {
		0x0b,0x33,(byte)0xe7,(byte)0xb2,0x51,0x0d,0x75,(byte)0xc3,0x4e,
		(byte)0xdd,(byte)0x3b,(byte)0x51,0x24,0x36,(byte)0xa8,(byte)0x28,
		0x0b,0x33,(byte)0xe7,(byte)0xb2,0x51,0x0d,0x75,(byte)0xc3	
	};
	
	public static void main(String[] args) throws Exception
	{
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
        props.put("platform.user", "hnmcc");
        props.put("platform.password", "hnmcc");
		
		XWECPLIClient.createInstance(props);
		//逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("动感地带");
		lic.setUserCity("14");
		lic.setUserMobile("13905166045");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "");
		ic.addContextParameter("route_type", "");
		ic.addContextParameter("route_value", "");
		ic.addContextParameter("ddr_city", "");
		
		ic.addContextParameter("user_id", "");  //2056200011182291
		
		lic.setContextParameter(ic);
		
		IQueryPukService queryPukService = new QueryPukServiceClientImpl();
		QRY040007Result result = queryPukService.queryPuk("18803695181");
		if (result != null)
		{
			
			logger.info(" ====== getResultCode ======" + result.getResultCode());
			logger.info(" ====== getErrorCode ======" + result.getErrorCode());
			logger.info(" ====== getErrorMessage ======" + result.getErrorMessage());
			logger.info(" ====== getResultCode1 ======" + result.getPuk());
		}
	}
}
