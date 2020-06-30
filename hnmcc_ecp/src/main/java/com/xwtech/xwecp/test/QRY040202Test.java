package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQryVisibleToHandleService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QryVisibleToHandleServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY040202Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class QRY040202Test {

    private static final Logger logger = LoggerFactory.getLogger(QRY040202Test.class);

    public static void main(String[] args) throws Exception {
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
        lic.setUserBrand("动感地带");
        lic.setUserCity("14");
        lic.setUserMobile("13815890413");
        InvocationContext ic = new InvocationContext();
        ic.addContextParameter("route_type", "1");
        ic.addContextParameter("route_value", "14");
        ic.addContextParameter("ddr_city", "14");
        ic.addContextParameter("user_id", "1419300019772126");
        lic.setContextParameter(ic);
        IQryVisibleToHandleService co = new QryVisibleToHandleServiceClientImpl();
        //13837865600	20160501	20160512
        QRY040202Result re = co.qryVisibleToHandle("15978812137", "X11202003198001,X11202003198002,X11202003198003", "Y", "");
        //co.scbQueryNew("15151539700","1","201312","201405");
        System.out.println(JSONObject.toJSONString(re));
//		for(VisibleYxhdList obj:re.getVisibleYxhdList()){
//		logger.info(obj.getOfferId()+"------"+obj.getCanSel()+"---------"+obj.getErrMsg()+"------"+obj.getSelFee()+"------"+obj.getOfferCode());
//		}
    }
}