package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryRightsInterestsService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryRightsInterestsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/*
 * 权益查询接口
 *
 * */
public class QRY300010Test {
    private static final Logger logger = LoggerFactory.getLogger(QRY300010Test.class);

    public static void main(String[] args) throws LIException {

/*
        QRY300010Result result = new QRY300010Result();
        String jsonStr="{\"respCode\":\"0000\",\"result\":{\"result\":{\"message\":\"当前用户没有权益\",\"transactionId\":\"123\",\"transactionTime\":\"201912212227\",\"code\":\"9999\"},\"respDesc\":\"调用成功!\"}}";
        JSONObject resultObj = JSONObject.parseObject(jsonStr).getJSONObject("result");
        JSONObject result1 = resultObj.getJSONObject("result");
        System.out.println("第一"+resultObj.toJSONString());
        ResultObject resultObject = new ResultObject();
        String transactionId = result1.getString("transactionId");
        String transactionTime = result1.getString("transactionTime");
        String code = result1.getString("code");
        String message = result1.getString("message");
        resultObject.setTransactionId(transactionId);
        resultObject.setTransactionTime(transactionTime);
        resultObject.setCode(code);
        resultObject.setMessage(message);
        result.setResult(resultObject);

        String s = JSON.toJSONString(result);

        System.out.println("第二"+s);*/
        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
//        props.put("platform.url", "http://127.0.0.1:8082/hnmcc_ecp/xwecp.do");
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
        IQueryRightsInterestsService queryRightsInterestsService = new QueryRightsInterestsServiceImpl();
        JSONObject publics = new JSONObject();
        publics.put("transactionId", "123");
        publics.put("transactionTime", "20190909140000");
        publics.put("system", "1000");
        publics.put("messageType", "1001");
        JSONObject busiInfo = new JSONObject();
        busiInfo.put("msisdn", "15093421454");
//      String [] bossCodes={"123"};
//      busiInfo.put("bossCodes",bossCodes);
//      System.out.println(publics.toJSONString());
//      System.out.println(busiInfo.toJSONString());
        String result = JSON.toJSONString(queryRightsInterestsService.queryRightsInterests(publics, busiInfo));
        System.out.println(result);
    }
}
