package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryMainAndSubCardsInfoNewService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryMainAndSubCardsInfoNewServiceImpl;
import org.jboss.logging.Logger;

import java.util.Properties;


public class QRY180820Test {

    private static final Logger logger = Logger.getLogger(QRY180820Test.class);

    public static void main(String[] args) throws LIException {
        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
//        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
//        props.put("platform.url", "http://10.218.105.2:14017/hnmcc_ecp/xwecp.do");
//        props.put("platform.url", "http://localhost:8082/hnmcc_ecp/xwecp.do");
//        props.put("platform.url", "http://127.0.0.1:8082/hnmcc_ecp/xwecp.do");
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
        IQueryMainAndSubCardsInfoNewService co = new QueryMainAndSubCardsInfoNewServiceImpl();
        //SvcNum    手机号码
        //IsAblity 是否通过能力开放平台调用   直接输入 Y15238341442
        System.out.println(JSON.toJSONString(co.queryMainAndSubCardsInfoNew("13592286158", "Y")));

    }
}
