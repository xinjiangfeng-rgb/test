
package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryBillBalService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryBillBalServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.BalanceDetail;
import com.xwtech.xwecp.service.logic.pojo.QRY010021Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

public class QRY010021Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY010021Test.class);
	
	public static void main(String[] args) throws Exception
	{
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
//		props.put("platform.url", "http://192.168.16.51:8080/hnmcc_ecp/xwecp.do");
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
		
		IQueryBillBalService co = new QueryBillBalServiceClientImpl();
		/**
		 * idType = (long)6;
		modeId = 10000001
		qryType = (Integer)1;
		startCycle传 201112的格式。到月份就行了
		Integer billType = 2;// 账单类型(默认2)
		Integer billLevel = 3;	

		 */
		long starttime=System.currentTimeMillis();
		QRY010021Result rs = co.queryBillBal("18236464534" ,"201706");
		System.out.println(JSONObject.toJSONString(rs));
		System.out.println("getCycle: "+rs.getBossCode());
		List<BalanceDetail> resList = rs.getBalanceDetails();
		System.out.println("----------------"+(System.currentTimeMillis()-starttime));
		for(BalanceDetail tem:resList){
			System.out.println(tem.getFeeName()+"----------"+tem.getFee());
		}

	}
}
