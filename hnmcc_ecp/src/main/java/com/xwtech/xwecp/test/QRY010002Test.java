package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryBillDetailService2;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryBillDetailService2impl;
import com.xwtech.xwecp.service.logic.pojo.QRY010002Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by Administrator on 2017/7/24.
 * 不确定
 */
public class QRY010002Test {

    private static final Logger logger = LoggerFactory.getLogger(QRY010002Test.class);


    public static void main(String[] args) throws Exception
    {
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp_nengkai/xwecp.do");
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
        //1 语音详单
        //2 套餐及固定费详单 		
        //3 自有增值业务扣费详单
        //4 代扣费业务详单
        //5 短彩信清单  
        //8 上网类详单  
        //22    其他费用扣费
        IQueryBillDetailService2 co = new QueryBillDetailService2impl();
        QRY010002Result ret = co.queryBillDetail("15093421454", "20190501",
                "20191001","", "9205","1","");
        System.out.println(JSONObject.toJSONString(ret));

    }
}
