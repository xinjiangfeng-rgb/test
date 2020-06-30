package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestData;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.msg.ResponseData;
import com.xwtech.xwecp.msg.ServiceMessage;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.client.BaseClientServiceImpl;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IFailureWorkOrderToDispatchCenterService;
import com.xwtech.xwecp.service.logic.pojo.DEL400030Result;

public class FailureWorkOrderToDispatchCenterServiceImpl extends BaseClientServiceImpl implements IFailureWorkOrderToDispatchCenterService {


    @Override
    public DEL400030Result failureWorkOrderToDispatchCenter(String systemId,
                                                            String serialNumber,
                                                            String customerPhone,
                                                            String businessType,
                                                            String taskOrderType,
                                                            String prodectCode,
                                                            String prodectDescribe,
                                                            String taskContet,
                                                            String creatFailTime,
                                                            String soPhone,
                                                            String subBusinessType) throws LIException {
        long __beginTime = System.currentTimeMillis();
        String __cmd = "DEL400030";
        XWECPLIClient __client = XWECPLIClient.getInstance();
        ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);



        RequestData __requestData = new RequestData();


        RequestParameter __param_phone = new RequestParameter("phone", "15093421454");
        __requestData.getParams().add(__param_phone);

        JSONObject parameter = new JSONObject();
        parameter.put("systemId", systemId);
        parameter.put("serialNumber", serialNumber);
        parameter.put("customerPhone", customerPhone);
        parameter.put("businessType", businessType);
        parameter.put("taskOrderType", taskOrderType);
        parameter.put("taskContet", taskContet);
        JSONObject customerInfo = new JSONObject();
        customerInfo.put("prodectCode",prodectCode);
        customerInfo.put("prodectDescribe",prodectDescribe);
        JSONObject extensionInfo = new JSONObject();
        extensionInfo.put("creatFailTime",creatFailTime);
        extensionInfo.put("soPhone",soPhone);
        extensionInfo.put("subBusinessType",subBusinessType);
        parameter.put("customerInfo", customerInfo);
        parameter.put("extensionInfo", extensionInfo);
        RequestParameter __param_parameter = new RequestParameter("parameter", parameter);
        __requestData.getParams().add(__param_parameter);

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
        DEL400030Result __result = null;
        String __responseXML = "";
        Throwable __errorStack = null;
        ServiceMessage __response = null;
        try {
            __responseXML = __client.getPlatformConnection().send(__requestXML);
            __response = ServiceMessage.fromXML(__responseXML);
            BaseServiceInvocationResult __ret = ((ResponseData) (__response.getData())).getServiceResult();
            if (__ret instanceof DEL400030Result) {
                __result = (DEL400030Result) __ret;
            } else {
                __result = new DEL400030Result();
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

