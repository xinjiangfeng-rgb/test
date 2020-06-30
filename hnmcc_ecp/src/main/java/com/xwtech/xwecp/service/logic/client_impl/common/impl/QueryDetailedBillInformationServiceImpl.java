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
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryDetailedBillInformationService;
import com.xwtech.xwecp.service.logic.pojo.QRY241038Result;

public class QueryDetailedBillInformationServiceImpl extends BaseClientServiceImpl implements IQueryDetailedBillInformationService {

    @Override
    public QRY241038Result queryDetailedBillInformation(String busiCode,String type,String acount,String channelId,String provinceId,String regionId) throws LIException {
        long __beginTime = System.currentTimeMillis();
        String __cmd = "QRY241038";
        XWECPLIClient __client = XWECPLIClient.getInstance();
        ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
        RequestData __requestData = new RequestData();
        RequestParameter __param_busiCode = new RequestParameter("busiCode", busiCode);
        __requestData.getParams().add(__param_busiCode);
        RequestParameter __param_type = new RequestParameter("type", type);
        __requestData.getParams().add(__param_type);
        RequestParameter __param_acount = new RequestParameter("acount", acount);
        __requestData.getParams().add(__param_acount);
        RequestParameter __param_channelId = new RequestParameter("channelId", channelId);
        __requestData.getParams().add(__param_channelId);
        RequestParameter __param_provinceId = new RequestParameter("provinceId", provinceId);
        __requestData.getParams().add(__param_provinceId);
        RequestParameter __param_regionId = new RequestParameter("regionId", regionId);
        __requestData.getParams().add(__param_regionId);
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
        QRY241038Result __result = null;
        String __responseXML = "";
        Throwable __errorStack = null;
        ServiceMessage __response = null;
        try {
            __responseXML = __client.getPlatformConnection().send(__requestXML);
            __response = ServiceMessage.fromXML(__responseXML);
            BaseServiceInvocationResult __ret = ((ResponseData) (__response.getData())).getServiceResult();
            if (__ret instanceof QRY241038Result) {
                __result = (QRY241038Result) __ret;
            } else {
                __result = new QRY241038Result();
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
