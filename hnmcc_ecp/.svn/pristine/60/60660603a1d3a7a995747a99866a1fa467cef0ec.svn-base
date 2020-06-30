package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IFiberOpticBroadbandService2;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.FiberOpticBroadbandServiceImpl2;
import com.xwtech.xwecp.service.logic.pojo.QRY201514Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class QRY201514Test {

    private static final Logger logger = LoggerFactory.getLogger(QRY201514Test.class);

    public static void main(String[] args) throws Exception {
        // 初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
//        props.put("platform.url", "http://127.0.0.1:8080/obsh_ecp/xwecp.do");
//        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
//         props.put("platform.url", "http://10.32.229.82:10008/sms_ecp/xwecp.do");
        // props.put("platform.url", "http://10.32.122.166:10009/js_ecp/xwecp.do");
        // props.put("platform.url", "http://10.32.65.238:8081/sms_ecp/xwecp.do");
        // props.put("platform.url", "http://10.32.65.238/js_ecp/xwecp.do");

        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp/xwecp.do");
//        props.put("platform.url", "http://localhost:8082/hnmcc_ecp/xwecp.do");

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
        IFiberOpticBroadbandService2 co = new FiberOpticBroadbandServiceImpl2();
        QRY201514Result re = co.queryFiberOpticBroadband("15093421454");
        System.out.println(JSONObject.toJSONString(re));
        logger.info(" ====== 开始返回参数 ======");
        if (re != null) {
            logger.info(" ====== getResultCode ======" + re.getResultCode());
            logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());
            logger.info(" ====== AccessId ======" + re.getAccessId());
            logger.info(" ====== BossCode ======" + re.getBossCode());
            logger.info(" ======是否订购有宽带 flag ======" + re.getFlag());
            logger.info(" ======宽带名称 KdName ======" + re.getKdName());
            logger.info(" ======宽带速率 KdRate ======" + re.getKdRate());
            logger.info(" ======宽带专项月租到期时间 KdRentExpireDate ======" + re.getKdRentExpireDate());
            logger.info(" ======宽带入网时间 KdCreateDate ======" + re.getKdCreateDate());
            logger.info(" ======订购的套餐信息 TypeName ======" + re.getTypeName());
            logger.info(" ======宽带状态 KdState ======" + re.getKdState());
            logger.info(" ======宽带类型 KdType ======" + re.getKdType());
            logger.info(" ======是否订购魔百和 IsHlwdsOffer ======" + re.getIsHlwdsOffer());
            logger.info(" ======是否订购魔百和优惠包 IsHlwdsPlan ======" + re.getIsHlwdsPlan());
            logger.info(" ======基础套餐编号 KdCode ======" + re.getKdCode());
            logger.info(" ======宽带类专项月租预存金额 AdvFreeTotal ======" + re.getAdvFreeTotal());
            logger.info(" ======宽带营销活动及到期时间 marketings ======" + re.getMarketings());

        }
    }
}
