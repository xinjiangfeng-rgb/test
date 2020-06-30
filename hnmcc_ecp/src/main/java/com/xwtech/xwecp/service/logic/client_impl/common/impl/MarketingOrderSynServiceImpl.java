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
import com.xwtech.xwecp.service.logic.client_impl.common.IMarketingOrderSynService;
import com.xwtech.xwecp.service.logic.pojo.DEL200624Result;

public class MarketingOrderSynServiceImpl extends BaseClientServiceImpl implements IMarketingOrderSynService {


    @Override
    public DEL200624Result marketingOrderSyn(String svcNum, String addProdPrcId, String batchId, String optId, String remark) throws LIException {
        long __beginTime = System.currentTimeMillis();
        String __cmd = "DEL200624";
        XWECPLIClient __client = XWECPLIClient.getInstance();
        ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
        RequestData __requestData = new RequestData();
        RequestParameter __param_svcNum = new RequestParameter("svcNum", svcNum);
        __requestData.getParams().add(__param_svcNum);
        RequestParameter __param_addProdPrcId = new RequestParameter("addProdPrcId", addProdPrcId);
        __requestData.getParams().add(__param_addProdPrcId);
        RequestParameter __param_batchId = new RequestParameter("batchId", batchId);
        __requestData.getParams().add(__param_batchId);
        RequestParameter __param_optId = new RequestParameter("optId", optId);
        __requestData.getParams().add(__param_optId);
        RequestParameter __param_remark= new RequestParameter("remark", remark);
        __requestData.getParams().add(__param_remark);
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
            __msg.getHead().setPageNum(__ic.getPageNum());
            __msg.getHead().setRecNum(__ic.getRecNum());
            __msg.getHead().setSerialNum(__ic.getSerialNum());
            __msg.getHead().setJfserialNum(__ic.getJfserialNum());
            __msg.getHead().setProdId(__ic.getProdId());
            ((RequestData) (__msg.getData())).setContext(__ic.getContextParameter());
        }
        String __requestXML = __msg.toString();
        DEL200624Result __result = null;
        String __responseXML = "";
        Throwable __errorStack = null;
        ServiceMessage __response = null;
        try {
            __responseXML = __client.getPlatformConnection().send(__requestXML);
            __response = ServiceMessage.fromXML(__responseXML);
            BaseServiceInvocationResult __ret = ((ResponseData) (__response.getData())).getServiceResult();
            if (__ret instanceof DEL200624Result) {
                __result = (DEL200624Result) __ret;
            } else {
                __result = new DEL200624Result();
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