package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQryCustomerPortraitService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QryCustomerPortraitServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 查询某用户号码的画像信息
 */
public class QRY181107Test {
    private static final Logger logger = LoggerFactory.getLogger(QRY181107Test.class);

    public static void main(String[] args) throws LIException {
        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
//        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
//        props.put("platform.url", "http://localhost:8082/hnmcc_ecp/xwecp.do");
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

        IQryCustomerPortraitService co = new QryCustomerPortraitServiceImpl();
//
        System.out.println(JSON.toJSONString(co.qryCustomerPortrait("15890000000", "000201811061437210000000","ckm43rd","3er4#ER$3er4#ER$12")));
//        System.out.println(JSON.toJSONString(co.qryCustomerPortrait("15978554502", "000201811301431310000000","MOBILE2_HALL","2we3$%6YU7")));


        lic.setContextParameter(ic);


    }
}
