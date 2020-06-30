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
import com.xwtech.xwecp.service.logic.client_impl.common.IValueAddedProductOrderService;
import com.xwtech.xwecp.service.logic.pojo.DEL930044Result;

public class ValueAddedProductOrderServiceImpl extends BaseClientServiceImpl implements IValueAddedProductOrderService {


    @Override
    public DEL930044Result valueAddedProductOrder(String SvcNum, String OperMode, String ProdCustomParaType, String productId, String ProdPrcPara, String OptrId, String Telephonist, String channelSource) throws LIException {
        long __beginTime = System.currentTimeMillis();
        String __cmd = "DEL930044";
        XWECPLIClient __client = XWECPLIClient.getInstance();
        ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
        RequestData __requestData = new RequestData();
        RequestParameter __param_SvcNum = new RequestParameter("SvcNum", SvcNum);
        __requestData.getParams().add(__param_SvcNum);
        RequestParameter __param_OperMode = new RequestParameter("OperMode", OperMode);
        __requestData.getParams().add(__param_OperMode);
        RequestParameter __param_ProdCustomParaType = new RequestParameter("ProdCustomParaType", ProdCustomParaType);
        __requestData.getParams().add(__param_ProdCustomParaType);
        RequestParameter __param_AddProdPrcId= new RequestParameter("productId", productId);
        __requestData.getParams().add(__param_AddProdPrcId);
        RequestParameter __param_ProdPrcPara= new RequestParameter("ProdPrcPara", ProdPrcPara);
        __requestData.getParams().add(__param_ProdPrcPara);
        RequestParameter __param_OptrId= new RequestParameter("OptrId", OptrId);
        __requestData.getParams().add(__param_OptrId);
        RequestParameter __param_Telephonist= new RequestParameter("Telephonist", Telephonist);
        __requestData.getParams().add(__param_Telephonist);
        RequestParameter __param_channelSource= new RequestParameter("channelSource", channelSource);
        __requestData.getParams().add(__param_channelSource);
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
        DEL930044Result __result = null;
        String __responseXML = "";
        Throwable __errorStack = null;
        ServiceMessage __response = null;
        try {
            __responseXML = __client.getPlatformConnection().send(__requestXML);
            __response = ServiceMessage.fromXML(__responseXML);
            BaseServiceInvocationResult __ret = ((ResponseData) (__response.getData())).getServiceResult();
            if (__ret instanceof DEL930044Result) {
                __result = (DEL930044Result) __ret;
            } else {
                __result = new DEL930044Result();
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
