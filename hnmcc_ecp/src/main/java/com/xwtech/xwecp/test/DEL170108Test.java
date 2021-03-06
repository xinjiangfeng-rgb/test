package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IMonthlyInvoiceOrderService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.MonthlyInvoiceOrderServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.DEL170108Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
/*
 *电子发票:月结发票订购
 * */
public class DEL170108Test {
    private static final Logger logger = LoggerFactory.getLogger(DEL170108Test.class);
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
        IMonthlyInvoiceOrderService co = new MonthlyInvoiceOrderServiceImpl();
        DEL170108Result result = null;
//        {"PUSH_TIME":"","PUSH_WAY":"3","SvcNum":"18837190160","opType":"ADD","pushType":"1","pushMail":"18837190160@139.com"}
        try {
            result = co.monthlyInvoiceOrder("15093421454", "ADD", "1", "771613021@qq.com","","3");
        } catch (LIException e) {
            e.printStackTrace();
        }
        logger.info(" ====== 开始返回参数 ======" + JSON.toJSONString(result));
    }

}
