package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IActivityCancelService;
import com.xwtech.xwecp.service.logic.client_impl.common.IFlowGjService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.ActivityCancelServiceIml;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.FlowGjServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY161108Result;
import com.xwtech.xwecp.service.logic.pojo.QRY170716Result;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by Administrator on 2017/7/17.
 */
public class QRY170716Test {
    private static final Logger logger = LoggerFactory.getLogger(QRY170716Test.class);

    public static void main(String[] args) {
        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
        //props.put("platform.url", "http://localhost:8090/hnmcc_ecp/xwecp.do");
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


        QRY170716Result ret = null;
        IActivityCancelService cancel = new ActivityCancelServiceIml();
        ret =   cancel.activityCancel("18236464534", "600000165637");
        System.out.println(JSONObject.toJSONString(ret));

    }
}
