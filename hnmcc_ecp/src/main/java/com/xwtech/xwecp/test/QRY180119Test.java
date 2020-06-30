package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.ILineUpService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.LineUpServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY180119Result;
import org.jboss.logging.Logger;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class QRY180119Test {


    private static final Logger logger = Logger.getLogger(QRY170911Test.class);

    public static void main(String[] args) throws FileNotFoundException {

        Date now = new Date("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式


        String hehe = dateFormat.format( now );
        System.out.println(now);
logger.info("hehe"+now);
        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
//        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
        props.put("platform.url", "http://127.0.0.1:8082/hnmcc_ecp/xwecp.do");
        props.put("platform.user", "hnmcc");
        props.put("platform.password", "hnmcc");

        XWECPLIClient client = XWECPLIClient.createInstance(props);

        //逻辑接口调用片段
        LIInvocationContext lic = LIInvocationContext.getContext();
        lic.setBizCode("biz_code_170911");
        lic.setOpType("开通/关闭/查询/变更");
        lic.setUserBrand("12");
        lic.setUserCity("12");
        lic.setUserMobile("15890158325");
        InvocationContext ic = new InvocationContext();
        ic.addContextParameter("login_msisdn", "15890158325");
        ic.addContextParameter("route_type", "2");
        ic.addContextParameter("route_value", "15890158325");

        lic.setContextParameter(ic);

        ILineUpService co = new LineUpServiceImpl();

        QRY180119Result ret = co.LineUp("13693710741", "201374", "0", "0",
                "2018-05-30 10:01", "2018-05-30 10:10");


        System.out.println(JSON.toJSONString(ret));


    }


}
