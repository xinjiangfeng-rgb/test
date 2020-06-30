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
import com.xwtech.xwecp.service.logic.client_impl.common.IAddedProductSubscriptionService;
import com.xwtech.xwecp.service.logic.pojo.DEL930019Result;

public class AddedProductSubscriptionServiceImpl extends BaseClientServiceImpl implements IAddedProductSubscriptionService {
    @Override
    public DEL930019Result addedProductSubscription(String svcNum, String addProdPrcId, String operMode, String optrld, String telephonist, String effDate, String expDate) throws LIException{
        long __beginTime = System.currentTimeMillis();
        String __cmd = "DEL930019";
        XWECPLIClient __client = XWECPLIClient.getInstance();
        ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
        RequestData __requestData = new RequestData();
        RequestParameter __param_svcNum = new RequestParameter("svcNum", svcNum);
        __requestData.getParams().add(__param_svcNum);
        RequestParameter __param_addProdPrcId = new RequestParameter("addProdPrcId", addProdPrcId);
        __requestData.getParams().add(__param_addProdPrcId);
        RequestParameter __param_operMode = new RequestParameter("operMode", operMode);
        __requestData.getParams().add(__param_operMode);
        RequestParameter __param_optrld = new RequestParameter("optrld", optrld);
        __requestData.getParams().add(__param_optrld);
        RequestParameter __param_telephonist = new RequestParameter("telephonist", telephonist);
        __requestData.getParams().add(__param_telephonist);
        RequestParameter __param_effDate = new RequestParameter("effDate", effDate);
        __requestData.getParams().add(__param_effDate);
        RequestParameter __param_expDate = new RequestParameter("expDate", expDate);
        __requestData.getParams().add(__param_expDate);
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
        DEL930019Result __result = null;
        String __responseXML = "";
        Throwable __errorStack = null;
        ServiceMessage __response = null;
        try {
            __responseXML = __client.getPlatformConnection().send(__requestXML);
            __response = ServiceMessage.fromXML(__responseXML);
            BaseServiceInvocationResult __ret = ((ResponseData) (__response.getData())).getServiceResult();
            if (__ret instanceof DEL930019Result) {
                __result = (DEL930019Result) __ret;
            } else {
                __result = new DEL930019Result();
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
