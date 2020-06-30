package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY181120Result;
import com.xwtech.xwecp.service.logic.pojo.RetDataBean;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ChannelReconciliationInvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_181120";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY181120Result result = new QRY181120Result();
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
            String respDesc = JSONObject.parseObject(rspXml).getString("respDesc");
            if (obj != null && !"".equals(obj)) {
                    String retCode = obj.getString("RET_CODE");
                    String bizCode = obj.getString("BizCode");
                    String bizDesc = obj.getString("BizDesc");
                    String retMsg = obj.getString("RET_MSG");
                    JSONArray retData = obj.getJSONArray("RET_DATA");
                    List<RetDataBean> retDataBeanList = new ArrayList<>();
                    for (int j = 0; j < retData.size(); j++) {
                        JSONObject retDataObj = (JSONObject) retData.get(j);
                        RetDataBean retDataBean = new RetDataBean();
                        String orderNo = retDataObj.getString("OrderNo");
                        String buyerId = retDataObj.getString("BuyerId");
                        String orderMoney = retDataObj.getString("OrderMoney");
                        String payment = retDataObj.getString("Payment");
                        String gift = retDataObj.getString("Gift");
                        String reqChannel = retDataObj.getString("ReqChannel");
                        String paymentType = retDataObj.getString("PaymentType");
                        String paymentLimit = retDataObj.getString("PaymentLimit");
                        String idType = retDataObj.getString("IdType");
                        String idValue = retDataObj.getString("IdValue");
                        String productId = retDataObj.getString("ProductId");
                        String productName = retDataObj.getString("ProductName");
                        String customParam = retDataObj.getString("CustomParam");
                        String diffType = retDataObj.getString("DiffType");
                        String settleDate = retDataObj.getString("SettleDate");
                        String isRefund = retDataObj.getString("IsRefund");
                        String oriSettleDate = retDataObj.getString("OriSettleDate");
                        String areacodeForBusinessHall = retDataObj.getString("AreacodeForBusinessHall");
                        String codeBusinessHall = retDataObj.getString("CodeBusinessHall");
                        String codeBusinessHallWindow = retDataObj.getString("CodeBusinessHallWindow");
                        String codeTerminal = retDataObj.getString("CodeTerminal");
                        String codeClerk = retDataObj.getString("CodeClerk");
                        String busiType = retDataObj.getString("BusiType");
                        String serviceFee = retDataObj.getString("ServiceFee");
                        String cardType = retDataObj.getString("CardType");
                        String paymentAgent = retDataObj.getString("PaymentAgent");
                        String paymentMode = retDataObj.getString("PaymentMode");
                        String reserve1 = retDataObj.getString("Reserve1");
                        String reserve2 = retDataObj.getString("Reserve2");
                        retDataBean.setOrderNo(orderNo);
                        retDataBean.setBuyerId(buyerId);
                        retDataBean.setOrderMoney(orderMoney);
                        retDataBean.setPayment(payment);
                        retDataBean.setGift(gift);
                        retDataBean.setReqChannel(reqChannel);
                        retDataBean.setPaymentType(paymentType);
                        retDataBean.setPaymentLimit(paymentLimit);
                        retDataBean.setIdType(idType);
                        retDataBean.setIdValue(idValue);
                        retDataBean.setProductId(productId);
                        retDataBean.setProductName(productName);
                        retDataBean.setCustomParam(customParam);
                        retDataBean.setDiffType(diffType);
                        retDataBean.setSettleDate(settleDate);
                        retDataBean.setIsRefund(isRefund);
                        retDataBean.setOriSettleDate(oriSettleDate);
                        retDataBean.setAreacodeForBusinessHall(areacodeForBusinessHall);
                        retDataBean.setCodeBusinessHall(codeBusinessHall);
                        retDataBean.setCodeBusinessHallWindow(codeBusinessHallWindow);
                        retDataBean.setCodeTerminal(codeTerminal);
                        retDataBean.setCodeClerk(codeClerk);
                        retDataBean.setBusiType(busiType);
                        retDataBean.setServiceFee(serviceFee);
                        retDataBean.setCardType(cardType);
                        retDataBean.setPaymentAgent(paymentAgent);
                        retDataBean.setPaymentMode(paymentMode);
                        retDataBean.setReserve1(reserve1);
                        retDataBean.setReserve2(reserve2);
                        retDataBeanList.add(retDataBean);
                    }

                    result.setBizDesc(bizDesc);
                    result.setBizCode(bizCode);
                    result.setRetCode(retCode);
                    result.setRetMsg(retMsg);
                    result.setRetData(retDataBeanList);
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
            logger.error("渠道对账服务", e);
        }
        return result;
    }
}
