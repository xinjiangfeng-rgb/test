
package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryNewRealTimeBillingService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryNewRealTimeBillingServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.FeeDetail;
import com.xwtech.xwecp.service.logic.pojo.QRY010018Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

public class QRY010018Test {
	private static final Logger logger = LoggerFactory.getLogger(QRY010018Test.class);
	
	public static void main(String[] args) throws Exception
	{
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
//		props.put("platform.url", "http://127.0.0.1:8080/hnmcc_ecp/xwecp.do");
		
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
		
		IQueryNewRealTimeBillingService co = new QueryNewRealTimeBillingServiceClientImpl();
		/**
		 * idType = (long)6;
		modeId = 10000001
		qryType = (Integer)1;
		startCycle传 201112的格式。到月份就行了
		Integer billType = 2;// 账单类型(默认2)
		Integer billLevel = 3;
		 */
		QRY010018Result rs = co.queryNewRealTimeBilling("15093383427" ,"201902");
		System.out.println(JSONObject.toJSONString(rs));
		System.out.println("getCycle: "+rs.getCycle());
		System.out.println("getGroupPay: "+rs.getGroupPay());
		System.out.println("getOtherPay: "+rs.getOtherPay());
		System.out.println("getTotalFee: "+rs.getTotalFee());
		System.out.println("other3: "+rs.getOtherPay3());
		System.out.println("other4: "+rs.getOtherPay4());
		List<QRY010018Result> resList = rs.getQry010018List();
		List<FeeDetail> lists;
		System.out.println(resList.size()+"--------");
		for(QRY010018Result tem:resList){
			System.out.println(tem.getMainprodName()+"----------|||"+tem.getTotalFee());
			lists = tem.getFeeDetailList();
			for(FeeDetail fee:lists){
				System.out.println(fee.getFeeName()+"++++++++++++++"+fee.getFee());
			}
		}
		 lists = rs.getFeeDetailList();
		if(lists != null && lists.size() > 0)
		{
			for (int i=0;i<lists.size();i++){
				FeeDetail d1 = lists.get(i);
				System.out.println(d1.getFee()+"++++++++++++++"+d1.getFeeName());
			}

		}

	}
}
