
package com.xwtech.xwecp.test;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryMyBaseInfoService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryMyBaseInfoServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.FeeDetail;
import com.xwtech.xwecp.service.logic.pojo.QRY010018Result;
import com.xwtech.xwecp.service.logic.pojo.QRY040099Result;

public class QRY040099Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY040099Test.class);
	
	public static void main(String[] args) throws Exception
	{
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");
		
		XWECPLIClient client = XWECPLIClient.createInstance(props);
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("1");
		lic.setUserBrand("1");
		lic.setUserCity("14");
		lic.setUserMobile("13913814503");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "13913814503");
		ic.addContextParameter("route_type", "1");
		ic.addContextParameter("route_value", "14");
		ic.addContextParameter("ddr_city", "14");
		
		
		lic.setContextParameter(ic);
		
		IQueryMyBaseInfoService co = new QueryMyBaseInfoServiceClientImpl();
		QRY040099Result rs = co.queryMyBaseInfo("13603843310" ,"201504");

		System.out.println("----"+rs.getResultCode());
		System.out.println("----"+rs.getBalance());
		System.out.println("----"+rs.getFeeResUsed());
		System.out.println("----"+rs.getScore());
		System.out.println("----"+rs.getTotalFee());
	}
}
