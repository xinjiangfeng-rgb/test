package com.xwtech.xwecp.test;

import java.util.List;
import java.util.Properties;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryFreeResourceInfoService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryFreeResourceInfoServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.FreeResource;
import com.xwtech.xwecp.service.logic.pojo.QRY020021Result;

public class QRY020021Test
{
    private static final Logger logger = LoggerFactory.getLogger(QRY020021Test.class);
    
    public static void main(String[] args)
        throws Exception
    {
        // 初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
//        props.put("platform.url", "http://localhost:8080/hnmcc_ecp/xwecp.do");
        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
        props.put("platform.user", "hnmcc");
        props.put("platform.password", "hnmcc");
        
        XWECPLIClient client = XWECPLIClient.createInstance(props);
        // 逻辑接口调用片段
        LIInvocationContext lic = LIInvocationContext.getContext();
        lic.setBizCode("biz_code_19234");
        lic.setOpType("开通/关闭/查询/变更");
        lic.setUserBrand("动感地带");
        lic.setUserCity("14");
        lic.setUserMobile("13901583754");
        InvocationContext ic = new InvocationContext();
        ic.addContextParameter("login_msisdn", "13806152071");
        ic.addContextParameter("route_type", "1");
        ic.addContextParameter("route_value", "19");
        ic.addContextParameter("ddr_city", "19");
        
        ic.addContextParameter("user_id", "1949200000475035");
        
        lic.setContextParameter(ic);
        
        IQueryFreeResourceInfoService co = new QueryFreeResourceInfoServiceClientImpl();
        
        QRY020021Result re = co.queryFreeResourceInfo("18737108266");
        System.out.println(JSONObject.toJSONString(re));
        logger.info(" ====== 开始返回参数 ======");
        if (re != null)
        {
            logger.info("响应码：" + re.getResultCode());
            logger.info("Boss响应码：" + re.getBossCode());
            logger.info("消息：" + re.getErrorMessage());
            
            List<FreeResource> freeResources = re.getFreeResources();
            for (FreeResource free : freeResources)
            {
                logger.info("getKey_num：" + free.getKey_num());
                logger.info("getItem_id：" + free.getItem_id());
                logger.info("getItem_name：" + free.getItem_name());
                logger.info("getProd_id：" + free.getProd_id());
                logger.info("getDone_code：" + free.getDone_code());
                logger.info("getFree_res：" + free.getFree_res());
                logger.info("getFree_res_remain：" + free.getFree_res_remain());
                logger.info("getFree_res_used：" + free.getFree_res_used());
                logger.info("getValid_date：" + free.getValid_date());
                logger.info("getExpire_date：" + free.getExpire_date());
                logger.info("getRemark：" + free.getRemark());
            }
        }

    }
}
