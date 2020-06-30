package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IPhoneAppPaymentSignVerificationService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.PhoneAppPaymentSignVerificationServiceImpl;
import org.jboss.logging.Logger;

import java.text.ParseException;
import java.util.Properties;


public class QRY181118Test {
    /*
     *
     * 手机APP支付验签服务
     *
     * */
    private static final Logger logger = Logger.getLogger(QRY181118Test.class);

    public static void main(String[] args) throws LIException, ParseException {
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
        IPhoneAppPaymentSignVerificationService co = new PhoneAppPaymentSignVerificationServiceImpl();
        System.out.println(JSON.toJSONString(co.phoneAppPaymentSignVerification("1", "1", "1", "20", "WEIXIN-APP", "", "01", "影视畅享包", "影视畅享包三方支付(1个月)", "", "VIRTUAL", "", "192.168.10.12", "", "666666666", "","01", "", "", "", "37100530000", "10XX", "", "", "", "", "", "60m", "U101_101")));
        lic.setContextParameter(ic);
    }
}
