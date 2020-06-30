package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IElectronicInvoiceDetailsService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.ElectronicInvoiceDetailsServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY202002Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/*
*电子发票:电子发票详情。
*
* 请求超时
* */
public class QRY202002Test {
    private static final Logger logger = LoggerFactory.getLogger(QRY202002Test.class);
    public static void main(String[] args) {
        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp_nengkai/xwecp.do");
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
        lic.setContextParameter(ic);
        IElectronicInvoiceDetailsService co = new ElectronicInvoiceDetailsServiceImpl();
        QRY202002Result result = null;
        try {
            result = co.electronicInvoiceDetails("33676373","202002","18803696960");
        } catch (LIException e) {
            e.printStackTrace();
        }
        System.out.println(JSONObject.toJSONString(result));
    }

}
