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


public class ConsumptionTrendAnalysisInvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_420032";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY420032Result result = new QRY420032Result();
        List<PayResultList> list = new ArrayList<PayResultList>();
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
            if (resultObj != null && !"".equals(resultObj)) {
                JSONArray consumptionList = resultObj.getJSONArray("SO_MEMBER_DEAL");
                List<ConsumptionResult> consumptionResultList = new ArrayList<>();
                for (int i = 0; i < consumptionList.size(); i++) {
                    if (consumptionList != null) {
                        JSONObject consumptionListObj = (JSONObject) consumptionList.get(i);
                        ConsumptionResult consumptionResult = new ConsumptionResult();
                        if (consumptionListObj != null) {
                            String feeTypeName = consumptionListObj.getString("FEETYPENAME");
                            String receAmount = consumptionListObj.getString("RECE_AMOUNT");
                            String billingCycle = consumptionListObj.getString("BILLING_CYCLE");
                            String resultDesc = consumptionListObj.getString("RESULTDESC");
                            String feeTypeId = consumptionListObj.getString("FEETYPEID");
                            String feeTypeAmount = consumptionListObj.getString("FEETYPEAMOUNT");
                            String resultCode = consumptionListObj.getString("RESULTCODE");
                            consumptionResult.setFeeTypeName(feeTypeName);
                            consumptionResult.setReceAmount(receAmount);
                            consumptionResult.setBillingCycle(billingCycle);
                            consumptionResult.setResultDesc(resultDesc);
                            consumptionResult.setResultCode(resultCode);
                            consumptionResult.setFeeTypeId(feeTypeId);
                            consumptionResult.setFeeTypeAmount(feeTypeAmount);
                            consumptionResultList.add(consumptionResult);
                        }
                    }
                }
                result.setConsumptionResults(consumptionResultList);
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
            logger.error("4.2.消费趋势分析接口", e);
        }
        return result;
    }
}
