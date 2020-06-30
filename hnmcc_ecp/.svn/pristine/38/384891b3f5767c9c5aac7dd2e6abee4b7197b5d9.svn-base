package com.xwtech.xwecp.test;

import java.util.Properties;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.ICheckUserInfoService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.CheckUserInfoServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY040005Result;

public class QRY040005Test
{
    private static final Logger logger = LoggerFactory.getLogger(QRY040005Test.class);
    
    public static void main(String[] args)
        throws Exception
    {
        // 初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
        props.put("platform.user", "hnmcc");
        props.put("platform.password", "hnmcc");
        
        XWECPLIClient client = XWECPLIClient.createInstance(props);
        // 逻辑接口调用片段
        LIInvocationContext lic = LIInvocationContext.getContext();
        lic.setBizCode("biz_code_19234");
        lic.setOpType("开通/关闭/查询/变更");
        lic.setUserBrand("动感地带");
        lic.setUserCity("用户县市");
        lic.setUserMobile("13601400067");
        InvocationContext ic = new InvocationContext();
        ic.addContextParameter("login_msisdn", "13675149526");
        ic.addContextParameter("route_type", "2");
        ic.addContextParameter("route_value", "13675149526");
        ic.addContextParameter("ddr_city", "14");
        
        ic.addContextParameter("user_id", "1738200005062065"); // 2056200011182291
        
        lic.setContextParameter(ic);
        
        ICheckUserInfoService co = new CheckUserInfoServiceClientImpl();
        // 动感地带 13401312424 brand_id：11 city_id：17 user_id：1738200005062065
        // 全球通 13913032424 user_id：1419200008195160
        QRY040005Result re = co.checkUserInfo("15837179491", "1", "372928199605188512", 0);
        System.out.println(JSON.toJSONString(re));
        logger.info(" ====== 开始返回参数 ======");
        if (re != null)
        {
            logger.info(" ====== getResultCode ======" + re.getResultCode());
            logger.info(" ====== getErrorCode ======" + re.getErrorCode());
            logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());
        }
    }
}
