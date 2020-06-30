package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.PAY181119Result;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/*
 *收银台支付验签接口服务
 * */
public class CheckStandPaymentSignInvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_181119";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        PAY181119Result result = new PAY181119Result();
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
                String retCode = jsonObj.getString("RET_CODE");
                String retMsg = jsonObj.getString("RET_MSG");
                String cerID = jsonObj.getString("CerID");
                String activityCode = jsonObj.getString("ActivityCode");
                String signValue = jsonObj.getString("SignValue");
                String orderNo = jsonObj.getString("OrderNo");
                String weiXinAppId = jsonObj.getString("WeiXinAppId");
                String notifyURL = jsonObj.getString("NotifyURL");
                String signNotifyURL = jsonObj.getString("SignNotifyURL");
                String unsignNotifyUrl = jsonObj.getString("UnsignNotifyUrl");
                String clientIP = jsonObj.getString("ClientIP");
                String theAreaCodeForTheBusinessHall = jsonObj.getString("TheAreaCodeForTheBusinessHall");
                String theCodeOfTheBusinessHall = jsonObj.getString("TheCodeOfTheBusinessHall");
                String theCodeOfTheBusinessHallWindow = jsonObj.getString("TheCodeOfTheBusinessHallWindow");
                String theCodeOfTerminal = jsonObj.getString("TheCodeOfTerminal");
                String theCodeOfClerk = jsonObj.getString("TheCodeOfClerk");
                String contractCode = jsonObj.getString("ContractCode");

                result.setContractCode(contractCode);
                result.setActivityCode(activityCode);
                result.setWeiXinAppId(weiXinAppId);
                result.setTheCodeOfTheBusinessHallWindow(theCodeOfTheBusinessHallWindow);
                result.setRetMsg(retMsg);
                result.setTheCodeOfTheBusinessHall(theCodeOfTheBusinessHall);
                result.setUnsignNotifyUrl(unsignNotifyUrl);
                result.setSignValue(signValue);
                result.setTheCodeOfClerk(theCodeOfClerk);
                result.setCerId(cerID);
                result.setSignNotifyURL(signNotifyURL);
                result.setOrderNo(orderNo);
                result.setTheCodeOfTerminal(theCodeOfTerminal);
                result.setNotifyUrl(notifyURL);
                result.setRetCode(retCode);
                result.setClientIP(clientIP);
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
            logger.error("收银台支付验签接口服务", e);
        }
        return result;
    }
}
