package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IOrdermarketingActivityNewService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.OrdermarketingActivityNewServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.DEL929206Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 营销活动订购接口 调能力开放平台
 */
public class DEL929206Test {
    private static final Logger logger = LoggerFactory.getLogger(DEL929206Test.class);

    public static void main(String[] args) throws Exception {
        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
//		props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
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
        IOrdermarketingActivityNewService co = new OrdermarketingActivityNewServiceImpl();
        DEL929206Result re = co.ordermarketingActivityNew("U", "xxx", "", "", "18236464534", "", "X11202001132002", "YZZZZTC", "", "");
        System.out.println(JSONObject.toJSONString(re));
        if (re != null) {
            logger.info(" ====== getResultCode ======" + re.getResultCode());
            logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());
            logger.info(" ====== getPhoneNum ======" + re.toString());
        }
    }
}

