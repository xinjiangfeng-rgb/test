package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.*;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class MarketingOrderSynInvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_200624";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        DEL200624Result result = new DEL200624Result();
        String rspXml = "";
        try {
            BaseJsonQueryUtils util = new BaseJsonQueryUtils();
            util.setConfig(REQUEST_BOSSTELETEXT);
            rspXml = util.getResponseXML(accessId, config, params);
            util.distroy();
            logger.info("******** Boss返回数据为*****　" + rspXml);
            if (StringUtils.isEmpty(rspXml)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) rspXml);
            String bossCode = bossJson.getBossCode();
            String bossDesc = bossJson.getBossDesc();
            logger.info("bossCode" + bossCode);
            logger.info("bossDesc" + bossDesc);
            JSONObject resultObj = JSONObject.parseObject(rspXml).getJSONObject("result");
            String respDesc = JSONObject.parseObject(rspXml).getString("respDesc");
            String respCode = JSONObject.parseObject(rspXml).getString("respCode");
            if (resultObj != null && !"".equals(resultObj)) {
                JSONArray array = resultObj.getJSONArray("SO_MEMBER_DEAL");
                List<OrderSyn> orderSynList = new ArrayList<>();
                for (int i = 0; i < array.size(); i++) {
                    if (array != null) {
                        JSONObject orderSynObj = (JSONObject) array.get(i);
                        OrderSyn orderSyn = new OrderSyn();
                        if (orderSynObj != null) {
                            String bizCode = orderSynObj.getString("BIZCODE");
                            String bizDesc = orderSynObj.getString("BIZDESC");
                            String oprTime = orderSynObj.getString("OPRTIME");
                            orderSyn.setBizCode(bizCode);
                            orderSyn.setBizDesc(bizDesc);
                            orderSyn.setOprTime(oprTime);
                            orderSynList.add(orderSyn);
                        }
                    }
                }
                result.setOrderSynList(orderSynList);
                result.setRespCode(respCode);
                result.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());
            } else {
                result.setErrorMessage(respDesc);
            }
            if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            result.setBossCode(bossCode);
        } catch (Exception e) {
            logger.error("营销活动订单同步", e);
        }
        return result;
    }
}
