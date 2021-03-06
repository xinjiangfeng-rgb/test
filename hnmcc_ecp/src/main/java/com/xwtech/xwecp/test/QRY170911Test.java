package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQryCurrentStreamService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QryCurrentStreamServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY170911Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class QRY170911Test {
    private static final Logger logger = LoggerFactory.getLogger(QRY170911Test.class);

    public static void main(String[] args) {
        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://218.206.204.151:8080/hnmcc_ecp/");
//        props.put("platform.url", "http://localhost:8080/hnmcc_ecp/xwecp.do");
        props.put("platform.user", "hnmcc");
        props.put("platform.password", "hnmcc");

        XWECPLIClient client = XWECPLIClient.createInstance(props);

        //逻辑接口调用片段
        LIInvocationContext lic = LIInvocationContext.getContext();
        lic.setBizCode("biz_code_170911");
        lic.setOpType("开通/关闭/查询/变更");
        lic.setUserBrand("12");
        lic.setUserCity("12");
        lic.setUserMobile("15890158325");
        InvocationContext ic = new InvocationContext();
        ic.addContextParameter("login_msisdn", "15890158325");
        ic.addContextParameter("route_type", "2");
        ic.addContextParameter("route_value", "15890158325");

        lic.setContextParameter(ic);


        IQryCurrentStreamService co = new QryCurrentStreamServiceImpl();
        QRY170911Result result = null;

        Long phone = 13592455910l;
        result = co.queryCurrentSteam("15737120860");
        System.out.println(JSON.toJSONString(result));

//        while(true){
//            result = co.queryCurrentSteam(phone.toString());
//            if(result != null){
//                if (!"0.00MB".equals(result.getTw()) && !"用户实例不存在！".equals(result.getTw()) && !"查询信息不存在！".equals(result.getTw())) {
//
//                    System.out.println(result.getTw());
//                    System.out.println(phone.toString());
//                    break;
//                }
//            }
//            phone ++;
//        }


    }
}
