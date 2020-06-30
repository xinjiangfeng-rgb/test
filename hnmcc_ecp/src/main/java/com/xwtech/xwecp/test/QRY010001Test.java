package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryBillDetailService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryBillDetailServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.GsmBillDetail;
import com.xwtech.xwecp.service.logic.pojo.QRY010001Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

/*
 * ESB_CLOUD_BUSINESS_940032
 * ESB_CLOUD_BUSINESS_940034
 * 不确定
 * */
public class QRY010001Test {
    private static final Logger logger = LoggerFactory.getLogger(QRY010001Test.class);

    public static void main(String[] args)
            throws Exception {
        // 初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
//        props.put("platform.url", "http://127.0.0.1:8081/hnmcc_ecp/xwecp.do");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp_nengkai/xwecp.do");
        props.put("platform.user", "hnmcc");
        props.put("platform.password", "hnmcc");

        XWECPLIClient client = XWECPLIClient.createInstance(props);
        // 逻辑接口调用片段
        LIInvocationContext lic = LIInvocationContext.getContext();
        lic.setBizCode("biz_code_19234");
        lic.setOpType("开通/关闭/查询/变更");
        lic.setUserBrand("动感地带");
        lic.setUserCity("14");
        lic.setUserMobile("18236464534");
        InvocationContext ic = new InvocationContext();
        // ic.addContextParameter("login_msisdn", "18236464538");
        ic.addContextParameter("route_type", "1");
        ic.addContextParameter("route_value", "14");
        ic.addContextParameter("ddr_city", "14");
        ic.addContextParameter("user_passwd", "AE6A5396C8A7E892");
        ic.addContextParameter("user_id", "1315200003768705");

        lic.setContextParameter(ic);

        //1 语音详单
        //2 套餐及固定费详单
        //3 自有增值业务扣费详单
        //4 代扣费业务详单
        //5 短彩信清单
        //8 上网类详单
        //22    其他费用扣费
        IQueryBillDetailService co = new QueryBillDetailServiceClientImpl();
        QRY010001Result re = co.queryBillDetail(
                "15093421454", "20190101", "20200129", 1, 4150, "");
        System.out.println(JSONObject.toJSONString(re));
        logger.info("响应码：" + re.getResultCode());
        logger.info("Boss响应码：" + re.getBossCode());
        logger.info("消息：" + re.getErrorMessage());
        List<GsmBillDetail> retList1 = re.getGsmBillDetail();
        logger.info(" ====== 开始返回参数_查询语音话单 ======" + retList1.size());
        int i = 0;
        for (GsmBillDetail obj : retList1) {
            System.out.println((i++) + "语音话单－－－－－－" + obj.getCall_type() + "--" + obj.getOpp_number() + "--" + obj.getTotal_info() + "--" + obj.getOffer_name());
        }
//
//
//        List<IpcarBillDetail> retList2 = re.getIpcarBillDetail();
//        logger.info(" ====== 开始返回参数_查询套餐详单 ======" + retList2.size());
//        for(IpcarBillDetail obj:retList2){
//            logger.info("套餐话单－－－－－－"+obj.getTotal_info());
//        }
//
//
//        List<IspBillDetail> retMMSList = re.getIspBillDetail();
//        logger.info(" ====== 开始返回参数_自有增值业务扣费详单 ======" + retMMSList.size());
//        for(IspBillDetail obj:retMMSList){
//        	logger.info("增值业务扣费详单－－－－－"+obj.getBusi_name()+"----"+obj.getStart_time()+"----"+obj.getTotal_info());
//        }
//
//
//        List<GprsBillDetail> retMMSList8 = re.getGprsBillDetail();
//        logger.info(" ====== 开始返回参数_查询上网详单 ======" + retMMSList8.size());
//        for(GprsBillDetail obj:retMMSList8){
//            logger.info("上网详单－－－－－－"+obj.getTotal_info());
//        }
//
//
//        List<MontnetBillDetail> retMMSList4 = re.getMontnetBillDetail();
//        logger.info(" ====== 开始返回参数_代扣费业务详单 ======" + retMMSList4.size());
//        for(MontnetBillDetail obj:retMMSList4){
//            logger.info("代扣费业务详单－－－－－－"+obj.getTotal_info());
//        }
//
//
//        List<CdrsBillDetail> retMMSList22 = re.getCdrsBillDetail();
//        logger.info(" ====== 开始返回参数_其他费用扣费 ======" + retMMSList22.size());
//        for(CdrsBillDetail obj:retMMSList22){
//            logger.info("其他费用扣费－－－－－－"+obj.getTotal_info());
//        }
//
//
//        List<MmsBillDetail> retMMSList5 = re.getMmsBillDetail();
//        logger.info(" ====== 开始返回参数_查询短彩信详单 ======" + retMMSList5.size());
//        for(MmsBillDetail obj:retMMSList5){
//            logger.info("查询短彩信详单－－－－－－"+obj.getTotal_info());
//        }


    }
}
