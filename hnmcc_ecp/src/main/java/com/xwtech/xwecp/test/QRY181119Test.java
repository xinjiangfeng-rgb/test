package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.ICheckStandPaymentSignService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.CheckStandPaymentSignServiceImpl;
import org.jboss.logging.Logger;

import java.text.ParseException;
import java.util.Properties;


public class QRY181119Test {
    /*
    *
    * 收银台支付验签接口服务
    *
    * */
    private static final Logger logger = Logger.getLogger(QRY181119Test.class);

    public static void main(String[] args) throws LIException, ParseException {
        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
        props.put("platform.user", "hnmcc");
        props.put("platform.password", "hnmcc");
        XWECPLIClient client = XWECPLIClient.createInstance(props);
        //逻辑接口调用片段
        LIInvocationContext lic = LIInvocationContext.getContext();
        lic.setBizCode("biz_code_19234");
        lic.setOpType("开通/关闭/查询/变更");
        lic.setUserBrand("12");
        lic.setUserCity("12");
        lic.setUserMobile("15890158325");
        InvocationContext ic = new InvocationContext();
        ic.addContextParameter("login_msisdn", "15890158325");
        ic.addContextParameter("route_type", "2");
        ic.addContextParameter("route_value", "15890158325");
        ICheckStandPaymentSignService co = new CheckStandPaymentSignServiceImpl();

        System.out.println(JSON.toJSONString(co.checkStandPaymentSign("1","1","0","20","","111111","河南移动智慧生活缴费","河南移动智慧生活缴费","http://192.168.59.200/ProductShow","VIRTUAL","https://h5.ha.chinamobile.com/hnmccClientWap/pay/index.html","","01","15093421454","37100310000","01XX","60m","U105_002")));
        lic.setContextParameter(ic);
    }
}
