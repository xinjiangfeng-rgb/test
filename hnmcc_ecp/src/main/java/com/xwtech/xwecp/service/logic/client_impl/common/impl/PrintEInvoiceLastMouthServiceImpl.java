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
import com.xwtech.xwecp.service.logic.client_impl.common.IPrintEInvoiceLastMouthService;
import com.xwtech.xwecp.service.logic.pojo.QRY202000Result;

public class PrintEInvoiceLastMouthServiceImpl  extends BaseClientServiceImpl implements IPrintEInvoiceLastMouthService {
    @Override
    public QRY202000Result printEInvoiceLastMouth(String paymentId,String acctId, String acctName, String printNum, String billingCycleId, String invoiceContent, String ghfNsrmc, String ghfNsrsbh, String ghfDz, String ghfDhhm, String ghfYhzh, String ghfQylx, String pushBillId, String pushEmail, String remark, String optCode) throws LIException {
        long __beginTime = System.currentTimeMillis();
        String __cmd = "QRY202000";
        XWECPLIClient __client = XWECPLIClient.getInstance();
        ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
        RequestData __requestData = new RequestData();
        RequestParameter __param_paymentId= new RequestParameter("paymentId", paymentId);
        __requestData.getParams().add(__param_paymentId);
        RequestParameter __param_acctId= new RequestParameter("acctId", acctId);
        __requestData.getParams().add(__param_acctId);
        RequestParameter __param_acctName = new RequestParameter("acctName", acctName);
        __requestData.getParams().add(__param_acctName);
        RequestParameter __param_printNum = new RequestParameter("printNum", printNum);
        __requestData.getParams().add(__param_printNum);
        RequestParameter __param_billingCycleId = new RequestParameter("billingCycleId", billingCycleId);
        __requestData.getParams().add(__param_billingCycleId);
        RequestParameter __param_invoiceContent = new RequestParameter("invoiceContent", invoiceContent);
        __requestData.getParams().add(__param_invoiceContent);
        RequestParameter __param_ghfNsrmc = new RequestParameter("ghfNsrmc", ghfNsrmc);
        __requestData.getParams().add(__param_ghfNsrmc);
        RequestParameter __param_ghfNsrsbh = new RequestParameter("ghfNsrsbh", ghfNsrsbh);
        __requestData.getParams().add(__param_ghfNsrsbh);
        RequestParameter __param_ghfDz = new RequestParameter("ghfDz", ghfDz);
        __requestData.getParams().add(__param_ghfDz);
        RequestParameter __param_ghfDhhm = new RequestParameter("ghfDhhm", ghfDhhm);
        __requestData.getParams().add(__param_ghfDhhm);
        RequestParameter __param_ghfYhzh = new RequestParameter("ghfYhzh", ghfYhzh);
        __requestData.getParams().add(__param_ghfYhzh);
        RequestParameter __param_ghfQylx = new RequestParameter("ghfQylx", ghfQylx);
        __requestData.getParams().add(__param_ghfQylx);
        RequestParameter __param_pushBillId = new RequestParameter("pushBillId", pushBillId);
        __requestData.getParams().add(__param_pushBillId);
        RequestParameter __param_pushEmail = new RequestParameter("pushEmail", pushEmail);
        __requestData.getParams().add(__param_pushEmail);
        RequestParameter __param_remark= new RequestParameter("remark", remark);
        __requestData.getParams().add(__param_remark);
        RequestParameter __param_optCode = new RequestParameter("optCode", optCode);
        __requestData.getParams().add(__param_optCode);
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
        QRY202000Result __result = null;
        String __responseXML = "";
        Throwable __errorStack = null;
        ServiceMessage __response = null;
        try {
            __responseXML = __client.getPlatformConnection().send(__requestXML);
            __response = ServiceMessage.fromXML(__responseXML);
            BaseServiceInvocationResult __ret = ((ResponseData) (__response.getData())).getServiceResult();
            if (__ret instanceof QRY202000Result) {
                __result = (QRY202000Result) __ret;
            } else {
                __result = new QRY202000Result();
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
