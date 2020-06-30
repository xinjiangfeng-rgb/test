package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.msg.RequestData;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.msg.ResponseData;
import com.xwtech.xwecp.msg.ServiceMessage;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.client.BaseClientServiceImpl;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.ICheckStandPaymentSignService;
import com.xwtech.xwecp.service.logic.pojo.PAY181119Result;

/*
* 收银台支付验签接口服务*/
public class CheckStandPaymentSignServiceImpl extends BaseClientServiceImpl implements ICheckStandPaymentSignService {


    @Override
    public PAY181119Result checkStandPaymentSign(String orderMoney, String payment, String gift, String merActivityID, String paymentLimit, String productID, String productName, String productDesc, String productURL, String productType, String returnURL, String customParam, String iDType, String iDValue, String reqChannel, String busiType, String timeoutExpress, String activityCode) throws LIException {
        long __beginTime = System.currentTimeMillis();
        String __cmd = "PAY181119";
        XWECPLIClient __client = XWECPLIClient.getInstance();
        ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
        RequestData __requestData = new RequestData();
        RequestParameter __param_phone= new RequestParameter("phone", "15093421454");
        RequestParameter __param_payment = new RequestParameter("payment", payment);
        RequestParameter __param_orderMoney = new RequestParameter("orderMoney", orderMoney);
        RequestParameter __param_gift = new RequestParameter("gift", gift);
        RequestParameter __param_merActivityID = new RequestParameter("merActivityID", merActivityID);
        RequestParameter __param_paymentLimit = new RequestParameter("paymentLimit", paymentLimit);
        RequestParameter __param_productID = new RequestParameter("productID", productID);
        RequestParameter __param_productName = new RequestParameter("productName", productName);
        RequestParameter __param_productDesc = new RequestParameter("productDesc", productDesc);
        RequestParameter __param_productURL = new RequestParameter("productURL", productURL);
        RequestParameter __param_productType = new RequestParameter("productType", productType);
        RequestParameter __param_returnURL = new RequestParameter("returnURL", returnURL);
        RequestParameter __param_customParam = new RequestParameter("customParam", customParam);
        RequestParameter __param_iDType = new RequestParameter("iDType", iDType);
        RequestParameter __param_iDValue = new RequestParameter("iDValue", iDValue);
        RequestParameter __param_reqChannel = new RequestParameter("reqChannel", reqChannel);
        RequestParameter __param_busiType = new RequestParameter("busiType", busiType);
        RequestParameter __param_timeoutExpress = new RequestParameter("timeoutExpress", timeoutExpress);
        RequestParameter __param_activityCode = new RequestParameter("activityCode", activityCode);
        __requestData.getParams().add(__param_phone);
        __requestData.getParams().add(__param_payment);
        __requestData.getParams().add(__param_orderMoney);
        __requestData.getParams().add(__param_gift);
        __requestData.getParams().add(__param_merActivityID);
        __requestData.getParams().add(__param_paymentLimit);
        __requestData.getParams().add(__param_productID);
        __requestData.getParams().add(__param_productName);
        __requestData.getParams().add(__param_productDesc);
        __requestData.getParams().add(__param_productURL);
        __requestData.getParams().add(__param_productType);
        __requestData.getParams().add(__param_returnURL);
        __requestData.getParams().add(__param_customParam);
        __requestData.getParams().add(__param_iDType);
        __requestData.getParams().add(__param_iDValue);
        __requestData.getParams().add(__param_reqChannel);
        __requestData.getParams().add(__param_busiType);
        __requestData.getParams().add(__param_timeoutExpress);
        __requestData.getParams().add(__param_activityCode);
        __msg.setData(__requestData);
        __msg.getHead().setUser(LIInvocationContext.USER);
        __msg.getHead().setPwd(LIInvocationContext.PWD);
        LIInvocationContext __ic = LIInvocationContext.getContext();
        if (__ic != null) {
            __msg.getHead().setOpType(__ic.getOpType());
            __msg.getHead().setUserMobile(__ic.getUserMobile());
            __msg.getHead().setUserCity(__ic.getUserCity());
            __msg.getHead().setBizCode(__ic.getBizCode());
            __msg.getHead().setUserBrand(__ic.getUserBrand());
            __msg.getHead().setOperId(__ic.getOperId());
            __msg.getHead().setTranceId(__ic.getTranceId());
            __msg.getHead().setClientPort(__ic.getClientPort());
            ((RequestData) (__msg.getData())).setContext(__ic.getContextParameter());
        }
        String __requestXML = __msg.toString();
        PAY181119Result __result = null;
        String __responseXML = "";
        Throwable __errorStack = null;
        ServiceMessage __response = null;
        try {
            __responseXML = __client.getPlatformConnection().send(__requestXML);
            __response = ServiceMessage.fromXML(__responseXML);
            BaseServiceInvocationResult __ret = ((ResponseData) (__response.getData())).getServiceResult();
            if (__ret instanceof PAY181119Result) {
                __result = (PAY181119Result) __ret;
            } else {
                __result = new PAY181119Result();
                __result.setResultCode(__ret.getResultCode());
                __result.setErrorCode(__ret.getErrorCode());
                __result.setErrorMessage(__ret.getErrorMessage());
            }
        } catch (Exception __e) {
            __errorStack = __e;
            throw new LIException(__e);
        } finally {
            long __endTime = System.currentTimeMillis();
            __client.log(__cmd, __client.getPlatformConnection().getRemoteURL(), __requestXML, __responseXML, __msg, __response, __beginTime, __endTime, __errorStack);
        }
        return __result;
    }
}
