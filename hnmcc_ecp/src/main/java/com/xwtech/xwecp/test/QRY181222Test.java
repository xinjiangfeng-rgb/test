package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQryGotoneCustomersService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QryGotoneCustomersServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY181222Result;
import org.jboss.logging.Logger;

import java.util.Properties;

/**
 * 全球通客户查询接口
 */
public class QRY181222Test {

    private static final Logger logger = Logger.getLogger(QRY181222Test.class);
    public static void main(String[] args) {
        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
//        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
//        props.put("platform.url", "http://localhost:8080/hnmcc_ecp/xwecp.do");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
        props.put("platform.user", "hnmcc");
        props.put("platform.password", "hnmcc");

        XWECPLIClient client = XWECPLIClient.createInstance(props);

        //逻辑接口调用片段
        LIInvocationContext lic = LIInvocationContext.getContext();
        lic.setBizCode("biz_code_170911");
        lic.setOpType("开通/关闭/查询/变更");
        lic.setUserBrand("12");
        lic.setUserCity("12");
        lic.setUserMobile("15837179491");
        InvocationContext ic = new InvocationContext();
        ic.addContextParameter("login_msisdn", "15890158325");
        ic.addContextParameter("route_type", "2");
        ic.addContextParameter("route_value", "15890158325");

        lic.setContextParameter(ic);

        IQryGotoneCustomersService co = new QryGotoneCustomersServiceImpl();

        QRY181222Result result = co.qryGotoneCustomers("15093421454");

        System.out.println(JSON.toJSONString(result));

    }

}
