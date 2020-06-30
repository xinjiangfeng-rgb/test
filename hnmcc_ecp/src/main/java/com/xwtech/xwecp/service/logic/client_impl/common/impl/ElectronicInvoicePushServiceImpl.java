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
import com.xwtech.xwecp.service.logic.client_impl.common.IElectronicInvoicePushService;
import com.xwtech.xwecp.service.logic.pojo.QRY202001Result;

public class ElectronicInvoicePushServiceImpl extends BaseClientServiceImpl implements IElectronicInvoicePushService {
    @Override
    public QRY202001Result electronicInvoicePush(String invoiceDsId, String billingCycleId, String billId, String pushBilldId, String pushEmail) throws LIException {
        long __beginTime = System.currentTimeMillis();
        String __cmd = "QRY202001";
        XWECPLIClient __client = XWECPLIClient.getInstance();
        ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
        RequestData __requestData = new RequestData();
        RequestParameter __param_invoiceDsId = new RequestParameter("invoiceDsId", invoiceDsId);
        __requestData.getParams().add(__param_invoiceDsId);
        RequestParameter __param_billingCycleId= new RequestParameter("billingCycleId", billingCycleId);
        __requestData.getParams().add(__param_billingCycleId);
        RequestParameter __param_billId = new RequestParameter("billId", billId);
        __requestData.getParams().add(__param_billId);
        RequestParameter __param_pushBilldId = new RequestParameter("pushBilldId", pushBilldId);
        __requestData.getParams().add(__param_pushBilldId);
        RequestParameter __param_pushEmail = new RequestParameter("pushEmail", pushEmail);
        __requestData.getParams().add(__param_pushEmail);
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
        QRY202001Result __result = null;
        String __responseXML = "";
        Throwable __errorStack = null;
        ServiceMessage __response = null;
        try {
            __responseXML = __client.getPlatformConnection().send(__requestXML);
            __response = ServiceMessage.fromXML(__responseXML);
            BaseServiceInvocationResult __ret = ((ResponseData) (__response.getData())).getServiceResult();
            if (__ret instanceof QRY202001Result) {
                __result = (QRY202001Result) __ret;
            } else {
                __result = new QRY202001Result();
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
