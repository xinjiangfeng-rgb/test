package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IValueAddedProductOrderService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.ValueAddedProductOrderServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.DEL930044Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/*
 *电子发票:月结发票订购
 * */
public class DEL930044Test {
    private static final Logger logger = LoggerFactory.getLogger(DEL930044Test.class);
    public static void main(String[] args) {
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
        lic.setContextParameter(ic);
        IValueAddedProductOrderService co=new ValueAddedProductOrderServiceImpl();
        DEL930044Result result = null;
        try {
            result = co.valueAddedProductOrder("15093421454", "ADD", "", "100168000750","00","ZZCP","","hnmcc_channel");
            System.out.println(JSON.toJSONString(result));
        } catch (LIException e) {
            e.printStackTrace();
        }
    }

}
