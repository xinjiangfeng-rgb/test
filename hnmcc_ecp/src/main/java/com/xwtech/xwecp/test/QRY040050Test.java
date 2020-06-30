package com.xwtech.xwecp.test;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryMonthlyTrafficService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryMonthlyTrafficServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY040050Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
/*
*
* 新流量月报
* */
public class QRY040050Test {

    private static final Logger logger = LoggerFactory.getLogger(QRY040050Test.class);

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
        lic.setUserBrand("动感地带");
        lic.setUserCity("14");
        lic.setUserMobile("13913814503");
        InvocationContext ic = new InvocationContext();
        ic.addContextParameter("login_msisdn", "13913814503");
        ic.addContextParameter("route_type", "1");
        ic.addContextParameter("route_value", "14");
        ic.addContextParameter("ddr_city", "14");
        ic.addContextParameter("user_id", "1423200000471569");
        lic.setContextParameter(ic);
        IQueryMonthlyTrafficService co = new QueryMonthlyTrafficServiceImpl();
        QRY040050Result re = co.queryMonthlyTraffic("18737108266", "202002", "", "", "");
        System.out.println(com.alibaba.fastjson.JSON.toJSONString(re));
    }
}
