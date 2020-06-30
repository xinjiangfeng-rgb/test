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
import com.xwtech.xwecp.service.logic.client_impl.common.IMarketingActivityBusiService;
import com.xwtech.xwecp.service.logic.pojo.DEL010022Result;

public class MarketingActivityBusiServiceClientImpl extends BaseClientServiceImpl implements IMarketingActivityBusiService {
    public DEL010022Result marketingActivityBusi(String phoneNum, String flag, String prodPrcId) throws LIException {
        long __beginTime = System.currentTimeMillis();
        String __cmd = "DEL010022";
        XWECPLIClient __client = XWECPLIClient.getInstance();
        ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
        RequestData __requestData = new RequestData();
        RequestParameter __param_phoneNum = new RequestParameter("phoneNum", phoneNum);
        __requestData.getParams().add(__param_phoneNum);
        RequestParameter __param_flag = new RequestParameter("flag", flag);
        __requestData.getParams().add(__param_flag);
        RequestParameter __param_prodPrcId = new RequestParameter("prodPrcId", prodPrcId);

        __requestData.getParams().add(__param_prodPrcId);

        RequestParameter __param_ZDOptCode = new RequestParameter("ZDOptCode", " ");
        __requestData.getParams().add(__param_ZDOptCode);
        RequestParameter __param_regionflag = new RequestParameter("regionflag", " ");
        __requestData.getParams().add(__param_regionflag);
        RequestParameter __param_channelSource = new RequestParameter("channelSource", " ");
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
            __msg.getHead().setPageNum(__ic.getPageNum());
            __msg.getHead().setRecNum(__ic.getRecNum());
            __msg.getHead().setSerialNum(__ic.getSerialNum());
            __msg.getHead().setJfserialNum(__ic.getJfserialNum());
            __msg.getHead().setProdId(__ic.getProdId());
            ((RequestData) (__msg.getData())).setContext(__ic.getContextParameter());
        }
        String __requestXML = __msg.toString();
        DEL010022Result __result = null;
        String __responseXML = "";
        Throwable __errorStack = null;
        ServiceMessage __response = null;
        try {
            __responseXML = __client.getPlatformConnection().send(__requestXML);
            __response = ServiceMessage.fromXML(__responseXML);
            BaseServiceInvocationResult __ret = ((ResponseData) (__response.getData())).getServiceResult();
            if (__ret instanceof DEL010022Result) {
                __result = (DEL010022Result) __ret;
            } else {
                __result = new DEL010022Result();
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


    public DEL010022Result marketingActivityBusiChannelSource(String phoneNum, String flag, String prodPrcId, String channelSource) throws LIException {
        long __beginTime = System.currentTimeMillis();
        String __cmd = "DEL010022";
        XWECPLIClient __client = XWECPLIClient.getInstance();
        ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
        RequestData __requestData = new RequestData();
        RequestParameter __param_phoneNum = new RequestParameter("phoneNum", phoneNum);
        __requestData.getParams().add(__param_phoneNum);
        RequestParameter __param_flag = new RequestParameter("flag", flag);
        __requestData.getParams().add(__param_flag);
        RequestParameter __param_prodPrcId = new RequestParameter("prodPrcId", prodPrcId);
        __requestData.getParams().add(__param_prodPrcId);

        RequestParameter __param_ZDOptCode = new RequestParameter("ZDOptCode", " ");
        __requestData.getParams().add(__param_ZDOptCode);
        RequestParameter __param_regionflag = new RequestParameter("regionflag", " ");
        __requestData.getParams().add(__param_regionflag);
        RequestParameter __param_channelSource = new RequestParameter("channelSource", channelSource);
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
            __msg.getHead().setPageNum(__ic.getPageNum());
            __msg.getHead().setRecNum(__ic.getRecNum());
            __msg.getHead().setSerialNum(__ic.getSerialNum());
            __msg.getHead().setJfserialNum(__ic.getJfserialNum());
            __msg.getHead().setProdId(__ic.getProdId());
            ((RequestData) (__msg.getData())).setContext(__ic.getContextParameter());
        }
        String __requestXML = __msg.toString();
        DEL010022Result __result = null;
        String __responseXML = "";
        Throwable __errorStack = null;
        ServiceMessage __response = null;
        try {
            __responseXML = __client.getPlatformConnection().send(__requestXML);
            __response = ServiceMessage.fromXML(__responseXML);
            BaseServiceInvocationResult __ret = ((ResponseData) (__response.getData())).getServiceResult();
            if (__ret instanceof DEL010022Result) {
                __result = (DEL010022Result) __ret;
            } else {
                __result = new DEL010022Result();
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