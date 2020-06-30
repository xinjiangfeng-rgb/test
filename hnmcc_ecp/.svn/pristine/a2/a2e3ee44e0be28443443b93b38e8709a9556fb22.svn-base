package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.ITransactBusiness2;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.TransactBusinessImpl2;
import com.xwtech.xwecp.service.logic.pojo.QRY930020Result;

import java.util.Properties;

/**
 * Created by 54344 on 2018/2/5.
 */
public class QRY930020Test {

    public static void main(String[] args) throws LIException {
        // 初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
//		props.put("platform.url", "http://127.0.0.1:8080/hnmcc_ecp/xwecp.do");
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
//        ITransactBusinessService co = new TransactBusinessServiceClientImpl();
        // 动感地带 13401312424 brand_id：11 city_id：17 user_id：1738200005062065
        // 全球通 13913032424 user_id：1419200008195160
        // 1、开通 2、关闭 3、变更
        // 1、立即 2、次日 3、次月
        //(mobile, "100165000504", 1, 0, "", "", "","CREATEZZCP");
        ITransactBusiness2 tr = new TransactBusinessImpl2();
        QRY930020Result ret = tr.transactBusiness2("18236464534", "100160000355", "ADD", "");
        System.out.println(JSONObject.toJSONString(ret));
    }
}
