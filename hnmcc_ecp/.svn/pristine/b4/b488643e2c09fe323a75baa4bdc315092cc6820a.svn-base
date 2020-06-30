package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IUnifiedPaymentRequestService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.UnifiedPaymentRequestServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 线上统一支付验签接口通用服务
 */
public class PAY181115Test {
    private static final Logger logger = LoggerFactory.getLogger(QRY500003Test.class);

    public static void main(String[] args) throws LIException {
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
        IUnifiedPaymentRequestService co = new UnifiedPaymentRequestServiceImpl();
        System.out.println(JSON.toJSONString(co.unifiedPaymentRequest("1", "1", "0", "", "WEIXIN-APP", "", "392900", "测试", "测试", "http://www.baidu.com", "VIRTUAL", "", "10.2.23.2", "", "", "","01","15093421454","","","37100310000","1001","","","","","","")));
        lic.setContextParameter(ic);
    }
}
