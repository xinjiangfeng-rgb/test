package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQrygprsdayfluxService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QrygprsdayfluxServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.GprsDayFlux;
import com.xwtech.xwecp.service.logic.pojo.QRY040064Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class QRY040064Test {
    private static final Logger logger = LoggerFactory.getLogger(QRY040064Test.class);

    public static void main(String[] args) throws Exception {
        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
//		props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
        //props.put("platform.url", "http://10.32.229.82:10008/sms_ecp/xwecp.do");
        //props.put("platform.url", "http://10.32.122.166:10009/js_ecp/xwecp.do");
        //props.put("platform.url", "http://10.32.65.238:8081/sms_ecp/xwecp.do");
        //props.put("platform.url", "http://10.32.65.238/js_ecp/xwecp.do");
        props.put("platform.user", "hnmcc");
        props.put("platform.password", "hnmcc");

        XWECPLIClient client = XWECPLIClient.createInstance(props);
        //逻辑接口调用片段
        LIInvocationContext lic = LIInvocationContext.getContext();
        lic.setBizCode("biz_code_19234");
        lic.setOpType("开通/关闭/查询/变更");
        lic.setUserBrand("动感地带");
        lic.setUserCity("14");
        lic.setUserMobile("13815890413");
        InvocationContext ic = new InvocationContext();
        ic.addContextParameter("route_type", "1");
        ic.addContextParameter("route_value", "14");
        ic.addContextParameter("ddr_city", "14");
        ic.addContextParameter("user_id", "1419300019772126");


        lic.setContextParameter(ic);

        //for(int i=0;i<1000;i++){
        //Thread.sleep(1000);
        IQrygprsdayfluxService co = new QrygprsdayfluxServiceClientImpl();
        //13837865600	20160501	20160512
        QRY040064Result re = co.qryGprsDayFlux("15093421454", "20191015", "20191107");
        System.out.println(JSON.toJSONString(re));
        //co.scbQueryNew("15151539700","1","201312","201405");
        logger.info(" ====== 开始返回参数 ======");
        logger.info(" ====== getResultCode ======" + re.getResultCode());
        for (GprsDayFlux obj : re.getGprsDayFluxList()) {
            logger.info(obj.getDayNum() + "------" + obj.getDayAllRes() + "---------" + Integer.parseInt(obj.getDayAllRes()) / 1024 + "M");
            //logger.info(obj.getDayNum()+"-------------"+Integer.parseInt(obj.getGprs4GFlux())/1024+"M");
        }
        //}


    }
}