package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryClaimForEquityService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryClaimForEquityServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
/*
*
* 权益领取接口
*
*
* */
public class QRY300011Test {
    private static final Logger logger = LoggerFactory.getLogger(QRY300011Test.class);

    public static void main(String[] args) throws LIException {
        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp_nengkai/xwecp.do");
//      props.put("platform.url", "http://localhost:8082/hnmcc_ecp/xwecp.do");
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
        IQueryClaimForEquityService queryClaimForEquityService = new QueryClaimForEquityServiceImpl();
        JSONObject publics=new JSONObject();
        publics.put("transactionId","123");
        publics.put("transactionTime","20190909140000");
        publics.put("system","1000");
        publics.put("messageType","1002");
        JSONObject busiInfo=new JSONObject();
        busiInfo.put("msisdn","15093421454");
        busiInfo.put("subscriptionId","20191211150340000025");
        busiInfo.put("subServiceId","1");
      /*  JSONObject tx=new JSONObject();
        tx.put("qq","771613021");*/
   /*     JSONObject student=new JSONObject();
        student.put("name","zs");
        student.put("age","12");
        student.put("sex","男");
        student.put("school","柳州一中");
        busiInfo.put("tx",tx);
        busiInfo.put("student",student);*/
        System.out.println(busiInfo.toJSONString());
        System.out.println(JSON.toJSONString(queryClaimForEquityService.queryClaimForEquity(publics,busiInfo)));
    }
}
