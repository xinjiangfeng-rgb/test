package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryBillDetailsendSmsCodeService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryBillDetailsendSmsCodeServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY940130Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by Administrator on 2017/7/24.
 */
public class QRY940130Test {

    private static final Logger logger = LoggerFactory.getLogger(QRY940130Test.class);

    public static void main(String[] args) throws Exception
    {
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp_nengkai/xwecp.do");

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
        QRY940130Result ret = co.sendSmsCode("17639153514");
//      QRY940130Result ret = co.sendSmsCode("");
        System.out.println(JSON.toJSONString(ret));

    }
}
