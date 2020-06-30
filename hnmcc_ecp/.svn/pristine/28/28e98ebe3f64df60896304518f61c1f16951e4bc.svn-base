package com.xwtech.xwecp.test;

import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryFluxInfService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryFluxInfClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY040111Result;
import com.xwtech.xwecp.service.logic.pojo.QRY040111ResultBean;
/**
 * 流量不清零查询
 * 
 * @author wangjiajia
 * 2015-11-11
 */
public class QRY040111Test
{
	private static final Logger logger = LoggerFactory.getLogger(QRY040111Test.class);
	
	public static void main(String[] args) throws Exception
	{
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
//		props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
		//props.put("platform.url", "http://10.32.229.82:10008/sms_ecp/xwecp.do");
		//props.put("platform.url", "http://10.32.122.166:10009/js_ecp/xwecp.do");
		//props.put("platform.url", "http://10.32.65.238:8081/sms_ecp/xwecp.do");
		//props.put("platform.url", "http://10.32.65.238/js_ecp/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");
		
		XWECPLIClient client = XWECPLIClient.createInstance(props);
		//逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("动感地带");
		lic.setUserCity("14");
		lic.setUserMobile("13815575493");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "18351002158");
		ic.addContextParameter("route_type", "2");
		ic.addContextParameter("route_value", "18351002158");
		ic.addContextParameter("ddr_city", "14");
		lic.setContextParameter(ic);
		IQueryFluxInfService co = new QueryFluxInfClientImpl();
//		QRY010099Result re = co.queryGmisInfo("4475","514442");
//		6402    125810 
		QRY040111Result re = co.queryFluxInf("15890158325");
		System.out.println(" ====== 开始返回参数 ======");
		if (re != null)
		{
			System.out.println(" ====== 返回结果码 ======" + re.getResultCode());
			System.out.println(" ====== re.getAuthState() ======" +re.toString());
			for(QRY040111ResultBean rb:re.getQry040111beanList()){
				System.out.println(rb.getItem_id()+"---"+rb.getItem_name()+"----"+rb.getProd_name()+"---"+rb.getFree_res());
			}
			
		}
	}
}
