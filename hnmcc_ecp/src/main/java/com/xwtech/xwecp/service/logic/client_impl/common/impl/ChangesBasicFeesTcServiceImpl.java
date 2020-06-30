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
import com.xwtech.xwecp.service.logic.client_impl.common.IChangesBasicFeesTcService;
import com.xwtech.xwecp.service.logic.pojo.DEL930047Result;

public class ChangesBasicFeesTcServiceImpl extends BaseClientServiceImpl implements IChangesBasicFeesTcService {
    @Override
    public DEL930047Result changesBasicFeesTc(String svcNum, String instanceId, String mainProdPrcParaValue, String mainProdPrcCustParaType, String optrId, String telephonist) throws LIException {

        long __beginTime = System.currentTimeMillis();
        String __cmd = "DEL930047";
        XWECPLIClient __client = XWECPLIClient.getInstance();
        ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
        RequestData __requestData = new RequestData();
        RequestParameter __param_svcNum = new RequestParameter("SvcNum", svcNum);
        __requestData.getParams().add(__param_svcNum);
        RequestParameter __param_instanceId = new RequestParameter("InstanceId", instanceId);
        __requestData.getParams().add(__param_instanceId);
        RequestParameter __param_mainProdPrcParaValue = new RequestParameter("MainProdPrcParaValue", mainProdPrcParaValue);
        __requestData.getParams().add(__param_mainProdPrcParaValue);
        RequestParameter __param_mainProdPrcCustParaType = new RequestParameter("MainProdPrcCustParaType", mainProdPrcCustParaType);
        __requestData.getParams().add(__param_mainProdPrcCustParaType);
        RequestParameter __param_optrId= new RequestParameter("OptrId", optrId);
        __requestData.getParams().add(__param_optrId);
        RequestParameter __param_telephonist = new RequestParameter("Telephonist", telephonist);
        __requestData.getParams().add(__param_telephonist);

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
        DEL930047Result __result = null;
        String __responseXML = "";
        Throwable __errorStack = null;
        ServiceMessage __response = null;
        try {
            __responseXML = __client.getPlatformConnection().send(__requestXML);
            __response = ServiceMessage.fromXML(__responseXML);
            BaseServiceInvocationResult __ret = ((ResponseData) (__response.getData())).getServiceResult();
            if (__ret instanceof DEL930047Result) {
                __result = (DEL930047Result) __ret;
            } else {
                __result = new DEL930047Result();
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
