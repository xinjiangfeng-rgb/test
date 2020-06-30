package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY191119Result;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class QueryBroadbandRenewalInvocation extends BaseInvocation implements ILogicalService {

    private String REQUEST_BOSSTELETEXT = "cc_191119";
    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY191119Result result = new QRY191119Result();
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
            JSONObject obj = JSONObject.parseObject(rspXml).getJSONObject("result");
            String ordBroadband = obj.getString("ORD_BROADBAND");
            String broadbandType = obj.getString("BROADBAND_TYPE");
            String planType = obj.getString("PLAN_TYPE");
            String expCustomer = obj.getString("EXP_CUSTOMER");
            String expDate = obj.getString("EXP_DATE");
            String specrentFlag = obj.getString("SPECRENT_FLAG");
            result.setOrdBroadband(ordBroadband);
            result.setBroadbandType(broadbandType);
            result.setPlanType(planType);
            result.setExpCustomer(expCustomer);
            result.setExpDate(expDate);
            result.setSpecrentFlag(specrentFlag);
            if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            result.setBossCode(bossCode);
            result.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());
        } catch (Exception e) {
            logger.error("宽带续费活动到期查询接口", e);
        }
        return result;
    }
}
