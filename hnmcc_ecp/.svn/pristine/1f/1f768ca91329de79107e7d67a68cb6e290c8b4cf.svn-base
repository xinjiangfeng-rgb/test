package com.xwtech.xwecp.test;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryOperDetailService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryOperDetailServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.OperDetail;
import com.xwtech.xwecp.service.logic.pojo.QRY040004Result;

public class QRY040004Test {
private static final Logger logger = LoggerFactory.getLogger(QRY040004Test.class);
	
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
		lic.setUserCity("用户县市");
		lic.setUserMobile("13601400067");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("contextParameter", "12345");
		ic.addContextParameter("testParameter", 54321);
		ic.addContextParameter("login_msisdn", "13913032424");
		ic.addContextParameter("route_type", "2");
		ic.addContextParameter("route_value", "13913032424");
		ic.addContextParameter("ddr_city", "20");
		
		ic.addContextParameter("user_id", "2056200011182291");
		
		lic.setContextParameter(ic);
		
		IQueryOperDetailService co = new QueryOperDetailServiceClientImpl();
		QRY040004Result result = co.queryOperDetail("13619807858","20160606", "20160608");
		List<OperDetail> operDetail = result.getOperDetail();
		System.out.println("---"+result.getResultCode());
		System.out.println("---"+result.getErrorCode());
		System.out.println("---"+result.getBossCode());
		for(int i = 0 ; i < operDetail.size() ; i ++)
		{
			System.out.println(operDetail.get(i).getOprTime() + " " + operDetail.get(i).getFormNum() + " " + operDetail.get(i).getOprChannel());
		}
		
//		String str="办理时间|业务名称|费用:2015-02-28|产品变更之订购增值产品|0.00;2015-02-28|产品变更之订购增值产品|0.00;2015-01-27|增值产品转移|0.00;2015-02-27|分摊预存账务分摊|15.00;更多记录请到营业厅查询";
//		str = str.substring(str.indexOf(":")+1);
//		String[] strArray = str.split(";");
//		for(int i=0 ;i<strArray.length;i++){
//			System.out.println("--------"+strArray[i]);
//			String[] temp = strArray[i].split("\\|");
//			System.out.println(">>>>>>>>"+temp[0]);
//			System.out.println(">>>>>>>>"+temp[1]);
//			System.out.println(">>>>>>>>"+temp[2]);
//		}
	}
	
	public static String transferDate(String date)
	{
		
		String result1 = date.substring(0,4);
		String result2 = date.substring(4,6);
		String result3 = date.substring(6,8);
		String result = result1 + "-" + result2 + "-" + result3;
		return result;
	}
	
}
