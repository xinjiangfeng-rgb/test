package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryEquityBusinessDataService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryEquityBusinessDataServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/*
*权益业务局数据查询
*
*
* */
public class QRY100802Test {
    private static final Logger logger = LoggerFactory.getLogger(QRY100802Test.class);

    public static void main(String[] args) throws LIException {
        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
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
        IQueryEquityBusinessDataService queryEquityBusinessDataService = new QueryEquityBusinessDataServiceImpl();
        JSONObject publics=new JSONObject();
        publics.put("transactionId","123");
        publics.put("transactionTime","20190909140000");
        publics.put("system","1000");
        publics.put("messageType","1004");
        JSONObject busiInfo=new JSONObject();
        String [] boss={"123"};
        busiInfo.put("bossCodes",boss);
        System.out.println(JSON.toJSONString(queryEquityBusinessDataService.queryEquityBusinessData(publics,busiInfo)));
    }
}
