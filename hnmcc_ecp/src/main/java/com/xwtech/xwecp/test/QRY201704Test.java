package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryBusinessInfoService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryBusinessInfoServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY201704Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class QRY201704Test {
    private static final Logger logger = LoggerFactory.getLogger(QRY201704Test.class);

    public static void main(String[] args) throws Exception {
        // 初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
//        props.put("platform.url", "http://127.0.0.1:8080/obsh_ecp/xwecp.do");
//         props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp_nengkai/xwecp.do");
//         props.put("platform.url", "http://10.32.229.82:10008/sms_ecp/xwecp.do");
        // props.put("platform.url", "http://10.32.122.166:10009/js_ecp/xwecp.do");
        // props.put("platform.url", "http://10.32.65.238:8081/sms_ecp/xwecp.do");
        // props.put("platform.url", "http://10.32.65.238/js_ecp/xwecp.do");
        props.put("platform.user", "hnmcc");
        props.put("platform.password", "hnmcc");
        XWECPLIClient client = XWECPLIClient.createInstance(props);
        // 逻辑接口调用片段
        LIInvocationContext lic = LIInvocationContext.getContext();
        lic.setBizCode("biz_code_19234");
        lic.setOpType("开通/关闭/查询/变更");
        lic.setUserBrand("1");
        lic.setUserCity("12");
        lic.setUserMobile("13915170950");
        InvocationContext ic = new InvocationContext();
        ic.addContextParameter("login_msisdn", "13838268859");
        ic.addContextParameter("route_type", "1");
        ic.addContextParameter("route_value", "12");

        lic.setContextParameter(ic);

        IQueryBusinessInfoService queryBusinessInfoService = new QueryBusinessInfoServiceImpl();
        QRY201704Result ret = queryBusinessInfoService.queryBusinessInfo("15093421454", "0000", "GROUP_PROD");
        System.out.println(JSONObject.toJSONString(ret));

        Set set = new HashSet();
        set.add(new QRY201704Result());
    }
}
