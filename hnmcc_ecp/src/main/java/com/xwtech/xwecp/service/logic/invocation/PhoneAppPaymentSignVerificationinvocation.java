package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY181118Result;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/*
 *手机APP支付验签服务
 * */
public class PhoneAppPaymentSignVerificationinvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_81118";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY181118Result result = new QRY181118Result();
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
            JSONObject jsonObj = JSONObject.parseObject(rspXml).getJSONObject("result");
            String respDesc = JSONObject.parseObject(rspXml).getString("respDesc");
            if (jsonObj != null && !"".equals(jsonObj)) {
                String signXMLData = jsonObj.getString("SignXMLData");
                String bizDesc = jsonObj.getString("BizDesc");
                String bizCode = jsonObj.getString("BizCode");
                String orderNo = jsonObj.getString("OrderNo");
                String retMsg = jsonObj.getString("RET_MSG");
                String reqDate = jsonObj.getString("ReqDate");
                String retCode = jsonObj.getString("RET_CODE");
                result.setSignXMLData(signXMLData);
                result.setBizDesc(bizDesc);
                result.setBizCode(bizCode);
                result.setRetMsg(retMsg);
                result.setRetCode(retCode);
                result.setOrderNo(orderNo);
                result.setReqDate(reqDate);
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
            logger.error("手机APP支付验签服务", e);
        }
        return result;
    }
}
