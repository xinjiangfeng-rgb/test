package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IAddedProductSubscriptionService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.AddedProductSubscriptionServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.DEL930019Result;

import java.util.Properties;

public class DEL930019Test {
    public static void main(String[] args) {
        // 初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
        props.put("platform.user", "hnmcc");
        props.put("platform.password", "hnmcc");
        XWECPLIClient client = XWECPLIClient.createInstance(props);
        // 逻辑接口调用片段
        LIInvocationContext lic = LIInvocationContext.getContext();
        lic.setBizCode("biz_code_19234");
        lic.setOpType("开通/关闭/查询/变更");
        lic.setUserBrand("动感地带");
        lic.setUserCity("14");


        lic.setUserMobile("13813382424");
        InvocationContext ic = new InvocationContext();
        ic.addContextParameter("login_msisdn", "13813382424");
        ic.addContextParameter("route_type", "1");
        ic.addContextParameter("route_value", "14");
        ic.addContextParameter("ddr_city", "14");
        ic.addContextParameter("user_id", "1419200008195116"); // 13813382424-1419200008195116,13913032424-1419200008195160
        lic.setContextParameter(ic);
        IAddedProductSubscriptionService co = new AddedProductSubscriptionServiceImpl();
        DEL930019Result ret = null;
        try {
            ret = co.addedProductSubscription("18236464534", "100168003776", "ADD", "","","", "");
        } catch (LIException e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(ret));
    }
}
