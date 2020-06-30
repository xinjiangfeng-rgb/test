package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IPackageActivitiesService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.PackageActivitiesServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.DEL218918Result;

import java.util.Properties;

public class DEL218918Test {

    public static void main(String[] args) throws Exception
    {

        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
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


        IPackageActivitiesService co= new PackageActivitiesServiceImpl();

        DEL218918Result result = co.qryPackageActivities("18236464534", "100168001551", "X16201812010004", "YZZZZTS");

        System.out.println(JSON.toJSONString(result));



    }
}
