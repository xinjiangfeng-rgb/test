package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.PAY181115Result;
import com.xwtech.xwecp.service.logic.pojo.PayResponse;
import com.xwtech.xwecp.service.logic.pojo.paypojo.*;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class UnifiedPaymentRequestInvocation extends BaseInvocation implements ILogicalService {

    private String REQUEST_BOSSTELETEXT = "cc_181115";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        PAY181115Result result = new PAY181115Result();

        String rspXml = "";
        try {
            BaseJsonQueryUtils util = new BaseJsonQueryUtils();
            util.setConfig(REQUEST_BOSSTELETEXT);
            rspXml = util.getResponseXML(accessId, config, params);
            util.distroy();
            logger.info("******** Boss返回数据为*****　" + rspXml);

            MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) rspXml);
            String bossCode = bossJson.getBossCode();
            String bossDesc = bossJson.getBossDesc();

            logger.info("bossCode" + bossCode);
            logger.info("bossDesc" + bossDesc);


            JSONObject obj = JSONObject.parseObject(rspXml).getJSONObject("result");
            String respDesc = JSONObject.parseObject(rspXml).getString("respDesc");

            if (obj != null && !"".equals(obj)) {

                String bizDesc = obj.getString("BizDesc");
                String bizCode = obj.getString("BizCode");
                String checkSignFlag = obj.getString("CheckSignFlag");
                String retMsg = obj.getString("RET_MSG");
                JSONObject parametersMap = obj.getJSONObject("ParametersMap");
                String payEwm = obj.getString("PayEwm");
                String retCode = obj.getString("RET_CODE");
                JSONObject jsonObj = obj.getJSONObject("PayResponse");
                result.setBizDesc(bizDesc);
                result.setBizCode(bizCode);
                result.setCheckSignFlag(checkSignFlag);
                result.setRetMsg(retMsg);
                result.setPayEwm(payEwm);
                result.setRetCode(retCode);


                PayResponse payResponse = new PayResponse();
                String parameters = jsonObj.getString("Parameters");
                String reqTransID = jsonObj.getString("ReqTransID");
                String rcvDate = jsonObj.getString("RcvDate");
                String activityCode = jsonObj.getString("ActivityCode");
                String reqSys = jsonObj.getString("ReqSys");
                String actionCode = jsonObj.getString("ActionCode");
                String reqDate = jsonObj.getString("ReqDate");
                String rspDesc = jsonObj.getString("RspDesc");
                String rcvSys = jsonObj.getString("RcvSys");
                String paymentType = jsonObj.getString("PaymentType");
                String resultDesc = jsonObj.getString("ResultDesc");
                String rspCode = jsonObj.getString("RspCode");
                String rcvTransID = jsonObj.getString("RcvTransID");
                String orderNo = jsonObj.getString("OrderNo");
                String resultCode = jsonObj.getString("ResultCode");
                String rcvDateTime = jsonObj.getString("RcvDateTime");
                String payURL = jsonObj.getString("PayURL");
                String reqDateTime = jsonObj.getString("ReqDateTime");

                //判断支付类型
                //网银支付
                if ("ALIPAY-BANK".equals(paymentType)) {
                    String service = parametersMap.getString("service");
                    String partner = parametersMap.getString("partner");
                    String _input_charset = parametersMap.getString("_input_charset");
                    String sign_type = parametersMap.getString("sign_type");
                    String sign = parametersMap.getString("sign");
                    String notify_url = parametersMap.getString("notify_url");
                    String out_trade_no = parametersMap.getString("out_trade_no");
                    String total_fee = parametersMap.getString("total_fee");
                    String seller_id = parametersMap.getString("seller_id");
                    String payment_type = parametersMap.getString("payment_type");
                    String body = parametersMap.getString("body");
                    String it_b_pay = parametersMap.getString("it_b_pay");
                    String extra_common_param = parametersMap.getString("extra_common_param");
                    String Return_url = parametersMap.getString("Return_url");
                    String defaultbank = parametersMap.getString("defaultbank");
                    AlipayBank alipayBank = new AlipayBank();
                    alipayBank.setService(service);
                    alipayBank.setPartner(partner);
                    alipayBank.setInputCharset(_input_charset);
                    alipayBank.setSignType(sign_type);
                    alipayBank.setSign(sign);
                    alipayBank.setNotifyUrl(notify_url);
                    alipayBank.setOutTradeNo(out_trade_no);
                    alipayBank.setTotalFee(total_fee);
                    alipayBank.setSellerId(seller_id);
                    alipayBank.setPaymentType(payment_type);
                    alipayBank.setBody(body);
                    alipayBank.setItBPay(it_b_pay);
                    alipayBank.setExtraCommonParam(extra_common_param);
                    alipayBank.setReturnUrl(Return_url);
                    alipayBank.setDefaultbank(defaultbank);
                    result.setParametersMap(alipayBank);
                } else if ("WEIXIN-JSAPI".equals(paymentType)) {
                    String appId = parametersMap.getString("appId");
                    String timeStamp = parametersMap.getString("timeStamp");
                    String nonceStr = parametersMap.getString("nonceStr");
                    String packages = parametersMap.getString("package");
                    String signType = parametersMap.getString("signType");
                    String paySign = parametersMap.getString("paySign");
                    WeiXinJsApi weiXinJsApi = new WeiXinJsApi();
                    weiXinJsApi.setAppId(appId);
                    weiXinJsApi.setTimeStamp(timeStamp);
                    weiXinJsApi.setNonceStr(nonceStr);
                    weiXinJsApi.setPackages(packages);
                    weiXinJsApi.setSignType(signType);
                    weiXinJsApi.setPaySign(paySign);
                    result.setParametersMap(weiXinJsApi);
                } else if ("WEIXIN-APP".equals(paymentType)) {
                    String appId = parametersMap.getString("appId");
                    String partnerid = parametersMap.getString("partnerid");
                    String prepayid = parametersMap.getString("prepayid");
                    String packages = parametersMap.getString("package");
                    String noncestr = parametersMap.getString("noncestr");
                    String timestamp = parametersMap.getString("timestamp");
                    String sign = parametersMap.getString("sign");
                    WeiXinApp weiXinApp = new WeiXinApp();
                    weiXinApp.setAppid(appId);
                    weiXinApp.setPartnerid(partnerid);
                    weiXinApp.setPrepayid(prepayid);
                    weiXinApp.setPackages(packages);
                    weiXinApp.setNoncestr(noncestr);
                    weiXinApp.setTimestamp(timestamp);
                    weiXinApp.setSign(sign);
                    result.setParametersMap(weiXinApp);
                } else if ("WEIXIN-NATIVE".equals(paymentType)) {
                    String codeUrl = parametersMap.getString("code_url");
                    WeiXinNative weiXinNative = new WeiXinNative();
                    weiXinNative.setCodeUrl(codeUrl);
                    result.setParametersMap(weiXinNative);
                } else if ("UNIONPAY-GATEWAY".equals(paymentType)) {
                    String txnType = parametersMap.getString("txnType");
                    String txnSubType = parametersMap.getString("txnSubType");
                    String frontUrl = parametersMap.getString("frontUrl");
                    String channelType = parametersMap.getString("channelType");
                    String currencyCode = parametersMap.getString("currencyCode");
                    String merId = parametersMap.getString("merId");
                    String version = parametersMap.getString("Version");
                    String signMethod = parametersMap.getString("signMethod");
                    String signature = parametersMap.getString("signature");
                    String backUrl = parametersMap.getString("backUrl");
                    String certId = parametersMap.getString("certId");
                    String encoding = parametersMap.getString("encoding");
                    String bizType = parametersMap.getString("bizType");
                    String orderId = parametersMap.getString("orderId");
                    String txnTime = parametersMap.getString("txnTime");
                    String accessType = parametersMap.getString("accessType");
                    String reqReserved = parametersMap.getString("reqReserved");
                    String payTimeout = parametersMap.getString("payTimeout");
                    UnionpayGateway unionpayGateway = new UnionpayGateway();
                    unionpayGateway.setTxnType(txnType);
                    unionpayGateway.setTxnSubType(txnSubType);
                    unionpayGateway.setFrontUrl(frontUrl);
                    unionpayGateway.setChannelType(channelType);
                    unionpayGateway.setCurrencyCode(currencyCode);
                    unionpayGateway.setCurrencyCode(currencyCode);
                    unionpayGateway.setMerId(merId);
                    unionpayGateway.setVersion(version);
                    unionpayGateway.setSignMethod(signMethod);
                    unionpayGateway.setSignature(signature);
                    unionpayGateway.setBackUrl(backUrl);
                    unionpayGateway.setCertId(certId);
                    unionpayGateway.setEncoding(encoding);
                    unionpayGateway.setBizType(bizType);
                    unionpayGateway.setOrderId(orderId);
                    unionpayGateway.setTxnTime(txnTime);
                    unionpayGateway.setAccessType(accessType);
                    unionpayGateway.setReqReserved(reqReserved);
                    unionpayGateway.setPayTimeout(payTimeout);
               result.setParametersMap(unionpayGateway);
                } else if ("UNIONPAY-APP".equals(paymentType)) {
                    String tn = parametersMap.getString("tn");
                    UnionpayApp unionpayApp = new UnionpayApp();
                    unionpayApp.setTn(tn);
                    result.setParametersMap(unionpayApp);
                } else if ("UNIONPAY-APPLEPAY".equals(paymentType)) {
                    String tn = parametersMap.getString("tn");
                    String merchantID = parametersMap.getString("MerchantID");
                    UnionpayApplepay unionpayApplepay = new UnionpayApplepay();
                    unionpayApplepay.setTn(tn);
                    unionpayApplepay.setMerchantID(merchantID);
                           result.setParametersMap(unionpayApplepay);
                } else if ("ALIPAY-NATIVE".equals(paymentType)) {
                    String codeUrl = parametersMap.getString("code_url");
                    AlipayNative alipayNative = new AlipayNative();
                    alipayNative.setCodeUrl(codeUrl);
                    result.setParametersMap(alipayNative);
                } else if ("WEIXIN-WAP".equals(paymentType)) {
                    String mweburl = parametersMap.getString("mweb_url");
                    WeiXinWap weiXinWap = new WeiXinWap();
                    weiXinWap.setMwebUrl(mweburl);
                     result.setParametersMap(weiXinWap);
                } else if ("CMPAY-WEB".equals(paymentType)) {
                    String sessionId = parametersMap.getString("sessionId");
                    CmpayWeb cmpayWeb = new CmpayWeb();
                    cmpayWeb.setSessionId(sessionId);
                   result.setParametersMap(cmpayWeb);
                } else if ("ALIPAY-SIGNANDPAY".equals(paymentType)) {
                    String scheme = parametersMap.getString("scheme");
                    AlipaySignandpay alipaySignandpay = new AlipaySignandpay();
                    alipaySignandpay.setScheme(scheme);
                    result.setParametersMap(alipaySignandpay);
                } else if ("UNIONPAY-NATIVE".equals(paymentType)) {
                    String codeUrl = parametersMap.getString("code_url");
                    UnionpayNative unionpayNative = new UnionpayNative();
                    unionpayNative.setCodeUrl(codeUrl);
                     result.setParametersMap(unionpayNative);
                } else if ("CMPAY-NATIVE".equals(paymentType)) {
                    String codeUrl = parametersMap.getString("code_url");
                    CmpayNative cmpayNative = new CmpayNative();
                    cmpayNative.setCodeUrl(codeUrl);
                    result.setParametersMap(cmpayNative);
                }
                payResponse.setParameters(parameters);
                payResponse.setReqTransID(reqTransID);
                payResponse.setRcvDate(rcvDate);
                payResponse.setActionCode(actionCode);
                payResponse.setReqSys(reqSys);
                payResponse.setActivityCode(activityCode);
                payResponse.setReqDate(reqDate);
                payResponse.setRspDesc(rspDesc);
                payResponse.setRcvSys(rcvSys);
                payResponse.setPaymentType(paymentType);
                payResponse.setResultDesc(resultDesc);
                payResponse.setRspCode(rspCode);
                payResponse.setRcvTransID(rcvTransID);
                payResponse.setOrderNo(orderNo);
                payResponse.setResultCode(resultCode);
                payResponse.setRcvDateTime(rcvDateTime);
                payResponse.setPayURL(payURL);
                payResponse.setReqDateTime(reqDateTime);
                result.setPayResponse(payResponse);
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
            logger.error("统一支付付款请求服务", e);
        }
        return result;
    }
}
