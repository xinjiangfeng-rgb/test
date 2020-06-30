package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryMobileService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryMobileServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY010003Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
/*
* ESB_QUERY_BALANCE_PHONE_170611
*
* */
public class QRY010003Test {

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

        IQueryMobileService queryMobileService = new QueryMobileServiceImpl();
        QRY010003Result ret ;
        ret = queryMobileService.queryMobile("15837179491");


        System.out.println(JSON.toJSONString(ret));
    }


}
