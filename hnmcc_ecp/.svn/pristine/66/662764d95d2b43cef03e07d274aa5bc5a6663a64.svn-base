package com.xwtech.xwecp.test;

import java.util.Properties;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.ICheckCRMLoginService;
import com.xwtech.xwecp.service.logic.client_impl.common.ITransactBusinessService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.CheckCRMLoginClientImpl;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.TransactBusinessServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY040103Result;

public class QRY040103Test
{
    private static final Logger logger = LoggerFactory.getLogger(QRY040103Test.class);
    
    public static void main(String[] args)
        throws Exception
    {
        // 初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
		props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
//		props.put("platform.url", "http://127.0.0.1:8090/hnmcc_ecp/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");
        
        XWECPLIClient client = XWECPLIClient.createInstance(props);
        // 逻辑接口调用片段
        LIInvocationContext lic = LIInvocationContext.getContext();
        lic.setBizCode("biz_code_19234");
        lic.setOpType("开通/关闭/查询/变更");
        lic.setUserBrand("动感地带");
        lic.setUserCity("14");
        lic.setUserMobile("13813382424");
        InvocationContext ic = new InvocationContext();
        ic.addContextParameter("login_msisdn", "13813382424");
        ic.addContextParameter("route_type", "1");
        ic.addContextParameter("route_value", "14");
        ic.addContextParameter("ddr_city", "14");
        ic.addContextParameter("user_id", "1419200008195116"); // 13813382424-1419200008195116,13913032424-1419200008195160
        lic.setContextParameter(ic);
        ICheckCRMLoginService co = new CheckCRMLoginClientImpl();
        // 1、开通 2、关闭 3、变更 \
        // 1、立即 2、次日 3、次月
        QRY040103Result re = co.sendLoginRequest();
        System.out.println(JSON.toJSONString(re));
        logger.info(" ====== 开始返回参数 ======");
        if (re != null)
        {
            logger.info(" === re.getResultCode() === " + re.getResultCode());
            logger.info(" === re.getErrorCode() === " + re.getErrorCode());
            
            logger.info(" === re.getErrorMessage() === " + re.getErrorMessage());
        }
    }
}
