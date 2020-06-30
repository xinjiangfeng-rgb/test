package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQryClientAnswerSmsService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QryClientAnswerSmsServiceImpl;
import org.jboss.logging.Logger;

import java.util.Properties;

/**
 * 5.2.	客户回复短信查询
 */
public class QRY180822Test {

    private static final Logger logger = Logger.getLogger(QRY180822Test.class);
    public static void main(String[] args) throws LIException {
        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
//        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
//        props.put("platform.url", "http://localhost:8082/hnmcc_ecp/xwecp.do");
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

        IQryClientAnswerSmsService co = new QryClientAnswerSmsServiceImpl();
        //SVCNUM  手机号码  OPTCODE 渠道编码    PORT 发送端口号 MESSAGE 发送短信内容   CLIENTQMF   10086962125
        //时间格式yyyy-mm-dd HH24:MI 比如：2018-08-01 09:00至 2018-08-01 09:02可查询出8月1日大于等于9点0分0秒小于9点02分59秒的数据

        System.out.println(JSON.toJSONString(co.qryClientAnswerSms("2018-12-01 00:01","2018-12-01 03:12","10086961","JK1","15093383427")));
        lic.setContextParameter(ic);
    }

}
