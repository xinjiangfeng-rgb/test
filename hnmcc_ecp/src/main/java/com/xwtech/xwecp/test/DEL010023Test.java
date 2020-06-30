package com.xwtech.xwecp.test;

import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IPresentActivityBusiService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.PresentActivityBusiServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.DEL010023Result;

/**
 * 带附加属性产品（来话管家）订购
 * @author YangXQ
 * 2014-10-15
 */
public class DEL010023Test
{
	private static final Logger logger = LoggerFactory.getLogger(DEL010023Test.class);
	
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
		lic.setUserCity("13"); 
		lic.setUserMobile("13584037474");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "13584037474");	
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "14");
		ic.addContextParameter("ddr_city", "14");
		ic.addContextParameter("user_id", "");   			
		lic.setContextParameter(ic);//13641582424 - 2157200003124230
		IPresentActivityBusiService co = new PresentActivityBusiServiceClientImpl();
		
		//phoneNumA 赠送者号码
		//phoneNum 被赠送者号码
		//falg 付费标识
		//prodprcid 活动ID
		//paypath 支付渠道
		DEL010023Result re = co.presentActivityBusi("18272702424", "18272702424", "U", "U11201510008002", "6");  
		logger.info(" ====== 开始返回参数 ======");
		if (re != null)
		{
			logger.info("----re.getBillInfo()------"+re.getBillInfo());
			logger.info("----re.getBossCode()------"+re.getBossCode());
			logger.info("----re.getSvcNum()------"+re.getSvcNum());
			logger.info("----re.getProdPrcName()------"+re.getProdPrcName());
			logger.info("----re.getConfirmFlag()------"+re.getConfirmFlag());
		}
	}
}
