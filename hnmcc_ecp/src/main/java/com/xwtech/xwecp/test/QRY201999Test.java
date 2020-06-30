package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IPrintEInvoiceService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.PrintEInvoiceServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY201999Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/*
*电子发票:打印电子发票
* 成功
* */
public class QRY201999Test {
    private static final Logger logger = LoggerFactory.getLogger(QRY201999Test.class);
    public static void main(String[] args) {
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
        lic.setContextParameter(ic);
        IPrintEInvoiceService co = new PrintEInvoiceServiceImpl();
        QRY201999Result result = null;
        try {
            result = co.printEInvoice("20200209092059A459302853A",
                    "202002","吕兵阳","18803696960","202002",
                    "测试","个人","","","",
                    "03","","","","","b");
        } catch (LIException e) {
            e.printStackTrace();
        }
        logger.info(" ====== 开始返回参数 ======" + JSON.toJSONString(result));
    }

}
