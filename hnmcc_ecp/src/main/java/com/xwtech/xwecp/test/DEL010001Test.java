package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.ITransactBusinessService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.TransactBusinessServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.DEL010001Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class DEL010001Test {



    private static final Logger logger = LoggerFactory.getLogger(DEL010001Test.class);

    public static void main(String[] args)
            throws Exception {
        // 初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
//		props.put("platform.url", "http://127.0.0.1:8081/hnmcc_ecp/xwecp.do");
//        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");

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
        ITransactBusinessService co = new TransactBusinessServiceClientImpl();
        // 动感地带 13401312424 brand_id：11 city_id：17 user_id：1738200005062065
        // 全球通 13913032424 user_id：1419200008195160
        // 1、开通 2、关闭 3、变更 \
        // 1、立即 2、次日 3、次月
        //(mobile, "100165000504", 1, 0, "", "", "","CREATEZZCP");
        DEL010001Result ret = null;
//        ret = co.transactBusinessChannelSource("18236464534", "100168000750", 1, 0, "", "", "", "", "1", "YZZZZDSYYT");
//       ret = co.transactBusinessChannelSource("18236464534", "100168000750", 1, 0, "", "", "", "","1");
//        ret = co.transactBusinessChannelSource("18236464534", "100168000750", 1, 0, "", "", "", "MWCP", "", "YZZZZDSYYT");
////WXYYJLB_MGYYCQBJHY
//        //MGYYNEWCL
//      ret = co.transactBusiness("18236464534", "100168000750", 1, 0, "", "", "", "ZZCP");
        ret =  co.transactBusiness("18236464534", "100168000750", 1, 1, "", "", "", "ZZCP");
        ret =  co.transactBusinessChannelSource("18236464534","100168000750",1,0,"","","","CREATEZZCP","hnmcc_channel");
        System.out.println(JSONObject.toJSONString(ret));
        logger.info(" ====== 开始返回参数 ======");
    }
}
