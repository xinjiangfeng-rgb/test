package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryAccountStatementService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryAccountStatementServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY420031Result;
import org.jboss.logging.Logger;

import java.util.Properties;

/*
 *
 * 账务账单查询接口
 * OSP_QUERY_ACCUNTS_BILL
 *
 * */
public class QRY420031Test {
    private static final Logger logger = Logger.getLogger(QRY420031Test.class);

    public static void main(String[] args) throws LIException {
        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp_nengkai/xwecp.do");
        props.put("platform.user", "hnmcc");
        props.put("platform.password", "hnmcc");
        XWECPLIClient client = XWECPLIClient.createInstance(props);
        //逻辑接口调用片段
        LIInvocationContext lic = LIInvocationContext.getContext();
        lic.setOpType("开通/关闭/查询/变更");
        lic.setBizCode("biz_code_170911");
        lic.setUserBrand("12");
        lic.setUserCity("12");
        InvocationContext ic = new InvocationContext();
        ic.addContextParameter("login_msisdn", "15890158325");
        ic.addContextParameter("route_type", "2");
        lic.setUserMobile("15837179491");
        ic.addContextParameter("route_value", "15890158325");
        lic.setContextParameter(ic);
        IQueryAccountStatementService co = new QueryAccountStatementServiceImpl();
        QRY420031Result result = co.queryAccountStatement("15093421454", "202004");
        System.out.println(JSON.toJSONString(result));
    }
}
