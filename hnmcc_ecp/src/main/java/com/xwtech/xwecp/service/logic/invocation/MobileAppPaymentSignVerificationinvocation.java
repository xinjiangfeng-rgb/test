package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.PAY181116Result;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/*
 *手机APP支付验签服务
 * */
public class MobileAppPaymentSignVerificationinvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_181116";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        PAY181116Result result = new PAY181116Result();
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
                String weiXinAppId = jsonObj.getString("WeiXinAppId");
                String bizDesc = jsonObj.getString("BizDesc");
                String theCodeOfTheBusinessHallWindow = jsonObj.getString("TheCodeOfTheBusinessHallWindow");
                String retMsg = jsonObj.getString("RET_MSG");
                String theAreaCodeForTheBusinessHall = jsonObj.getString("TheAreaCodeForTheBusinessHall");
                String unsignNotifyUrl = jsonObj.getString("UnsignNotifyUrl");
                String cashierOrderUrl = jsonObj.getString("cashierOrderUrl");
                String signValue = jsonObj.getString("SignValue");
                String theCodeOfClerk = jsonObj.getString("TheCodeOfClerk");
                String cerID = jsonObj.getString("CerID");
                String bizCode = jsonObj.getString("BizCode");
                String signNotifyURL = jsonObj.getString("SignNotifyURL");
                String orderNo = jsonObj.getString("OrderNo");
                String theCodeOfTerminal = jsonObj.getString("TheCodeOfTerminal");
                String notifyURL = jsonObj.getString("NotifyURL");
                String retCode = jsonObj.getString("RET_CODE");
                String clientIP = jsonObj.getString("ClientIP");
                String h5OrderUrl = jsonObj.getString("h5OrderUrl");
                String theCodeOfTheBusinessHall = jsonObj.getString("TheCodeOfTheBusinessHall");
                result.setWeiXinAppId(weiXinAppId);
                result.setBizDesc(bizDesc);
                result.setTheCodeOfTheBusinessHallWindow(theCodeOfTheBusinessHallWindow);
                result.setRetMsg(retMsg);
                result.setTheCodeOfTheBusinessHall(theCodeOfTheBusinessHall);
                result.setUnsignNotifyUrl(unsignNotifyUrl);
                result.setCashierOrderUrl(cashierOrderUrl);
                result.setSignValue(signValue);
                result.setTheCodeOfClerk(theCodeOfClerk);
                result.setCerId(cerID);
                result.setBizCode(bizCode);
                result.setSignNotifyURL(signNotifyURL);
                result.setOrderNo(orderNo);
                result.setTheCodeOfTerminal(theCodeOfTerminal);
                result.setNotifyUrl(notifyURL);
                result.setRetCode(retCode);
                result.setClientIP(clientIP);
                result.setH5OrderUrl(h5OrderUrl);
                result.setTheAreaCodeForTheBusinessHall(theAreaCodeForTheBusinessHall);
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
