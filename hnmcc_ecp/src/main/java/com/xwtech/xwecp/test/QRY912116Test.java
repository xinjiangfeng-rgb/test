package com.xwtech.xwecp.test;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryMgrMsgByBillIdService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryMgrMsgByBillIdServiceImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY912116Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class QRY912116Test {

    private static final Logger logger = LoggerFactory.getLogger(QRY912116Test.class);

    /**
     * 获取客户经理信息
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)
            throws Exception {
        // 初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://218.206.204.153/hnmcc_ecp_nengkai/xwecp.do");
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

        IQueryMgrMsgByBillIdService co = new QueryMgrMsgByBillIdServiceImpl();
        QRY912116Result re = co.queryMgrMsgByBillId("15093421454");
        System.out.println(JSON.toJSONString(re));
        logger.info(" ====== 开始返回参数 ======");
        if (re != null) {
            logger.info(" ====== getResultCode ======" + re.getResultCode());
            logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());
            logger.info(" ====== ProdprcName ======" + re.getEnStaffBill());
            logger.info(" ====== ProdprcId ======" + re.getEnStaffName());
        }
    }


}
