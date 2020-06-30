package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryBillDetailsendSmsCodeService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryBillDetailsendSmsCodeServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY940130Result;

import java.util.Properties;

/**
 * Created by Administrator on 2017/10/9.
 */
public class QRY940131Test {

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
        IQueryBillDetailsendSmsCodeService co = new QueryBillDetailsendSmsCodeServiceImpl();
        QRY940130Result ret = co.sendSmsCode("15837179491");
        System.out.println(JSONObject.toJSONString(ret));

    }

}
