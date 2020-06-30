package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IFailureWorkOrderToDispatchCenterService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.FailureWorkOrderToDispatchCenterServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.DEL400030Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
/*
* 手厅同步业务办理失败工单到派单中心
*
* */
public class DEL400030Test {
    private static final Logger logger = LoggerFactory.getLogger(DEL400030Test.class);
    public static void main(String[] args) throws Exception {
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
        lic.setUserMobile("15252318843");
        InvocationContext ic = new InvocationContext();
        ic.addContextParameter("login_msisdn", "15252318843");
        ic.addContextParameter("route_type", "2");
        ic.addContextParameter("route_value", "15996190955");
        lic.setContextParameter(ic);
        IFailureWorkOrderToDispatchCenterService co = new FailureWorkOrderToDispatchCenterServiceImpl();
        DEL400030Result re = co.failureWorkOrderToDispatchCenter("SJYYT", "10005620", "15093421454", "0101","2003","100168002341"," 移动流量王-49元A套餐（180分钟+5G）（公开版）","测测","2020年3月9日10:50:01","15093421454","0003000900000170");
        System.out.println(JSONObject.toJSONString(re));
    }
}
