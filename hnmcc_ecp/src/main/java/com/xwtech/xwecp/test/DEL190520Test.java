package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IAddedChangeBasicProductsService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.AddedChangeBasicProductsServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.DEL190520Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class DEL190520Test {

    private static final Logger logger = LoggerFactory.getLogger(DEL190520Test.class);

    public static void main(String[] args) throws Exception {
        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
//        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
//		props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
        props.put("platform.user", "hnmcc");
        props.put("platform.password", "hnmcc");
        XWECPLIClient client = XWECPLIClient.createInstance(props);
        //逻辑接口调用片段
        LIInvocationContext lic = LIInvocationContext.getContext();
        lic.setBizCode("biz_code_19234");
        lic.setOpType("开通/关闭/查询/变更");
        lic.setUserBrand("12");
        lic.setUserCity("12");
        lic.setUserMobile("15252318843");
        InvocationContext ic = new InvocationContext();
        ic.addContextParameter("login_msisdn", "15252318843");
        ic.addContextParameter("route_type", "2");
        ic.addContextParameter("route_value", "15996190955");
        lic.setContextParameter(ic);
        IAddedChangeBasicProductsService co = new AddedChangeBasicProductsServiceImpl();

        DEL190520Result re = co.addedChangeBasicProducts( "18236464534", "100160000355","","");
        System.out.println(JSONObject.toJSONString(re));
        logger.info(" ====== 开始返回参数 ======"+re);

        if (re != null)
        {
            logger.info(" ====== getResultCode ======" + re.getResultCode());
            logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());
            logger.info(" ====== getPhoneNum ======" + re.toString());
        }
    }
}
