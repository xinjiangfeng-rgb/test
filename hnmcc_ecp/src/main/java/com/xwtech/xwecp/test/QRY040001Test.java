package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryUserInfoService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryUserInfoServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY040001Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class QRY040001Test
{
    private static final Logger logger = LoggerFactory.getLogger(QRY040001Test.class);
    
    /**
     * 查询用户信息
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)
        throws Exception
    {
        // 初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
//        props.put("platform.url", "http://127.0.0.1:8080/obsh_ecp/xwecp.do");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
         //props.put("platform.url", "http://127.0.0.1:8090/hnmcc_ecp/xwecp.do");
        // props.put("platform.url", "http://10.32.229.82:10008/sms_ecp/xwecp.do");
        // props.put("platform.url", "http://10.32.122.166:10009/js_ecp/xwecp.do");
        // props.put("platform.url", "http://10.32.65.238:8081/sms_ecp/xwecp.do");
        // props.put("platform.url", "http://10.32.65.238/js_ecp/xwecp.do");
        props.put("platform.user", "hnmcc");
        props.put("platform.password", "hnmcc");
        XWECPLIClient client = XWECPLIClient.createInstance(props);
        // 逻辑接口调用片段
        LIInvocationContext lic = LIInvocationContext.getContext();
        lic.setBizCode("biz_code_19234");
        lic.setOpType("开通/关闭/查询/变更");
        lic.setUserBrand("1");
        lic.setUserCity("12");
        lic.setUserMobile("13915170950");
        InvocationContext ic = new InvocationContext();
        ic.addContextParameter("login_msisdn", "13838268859");
        ic.addContextParameter("route_type", "1");
        ic.addContextParameter("route_value", "12");
        
        lic.setContextParameter(ic);
        //13937128180
        IQueryUserInfoService co = new QueryUserInfoServiceClientImpl();
        QRY040001Result re = co.queryUserInfo("15093421454");//18737108266
        System.out.println(JSONObject.toJSONString(re));
        logger.info(" ====== 开始返回参数 ======");
        if (re != null)
        {
            logger.info(" ====== getResultCode ======" + re.getResultCode());
            logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());
            logger.info(" ====== AreaCode ======" + re.getProdprcname());
            logger.info(" ====== AreaCode ======" + re.getAreacode());
            logger.info(" ====== AreaCode ======" + re.getUser_name());
            logger.info(" ====== AreaCode ======" + re.getBrandname());
            logger.info(" ====== AreaCode ======" + re.getStoptype_name());
            //logger.info(" ====== AreaCode ======" + re.get);
        }
    }
}
