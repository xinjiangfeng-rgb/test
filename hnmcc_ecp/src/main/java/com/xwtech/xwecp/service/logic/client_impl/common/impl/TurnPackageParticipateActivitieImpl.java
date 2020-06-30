package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.msg.RequestData;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.msg.ResponseData;
import com.xwtech.xwecp.msg.ServiceMessage;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.ITurnPackageParticipateActivitieService;
import com.xwtech.xwecp.service.logic.pojo.DEL218919Result;


/**
 * 转套餐并参加活动
 */
public class TurnPackageParticipateActivitieImpl implements ITurnPackageParticipateActivitieService {


    @Override
    public DEL218919Result delTurnPackageParticipateActivitie(String billId, String basicofferid, String ployid) throws LIException {
        long __beginTime = System.currentTimeMillis();
        String __cmd = "DEL218919";
        XWECPLIClient __client = XWECPLIClient.getInstance();
        ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
        RequestData __requestData = new RequestData();
        RequestParameter __param_billId = new RequestParameter("billId", billId);
        RequestParameter __param_basicofferid = new RequestParameter("basicofferid", basicofferid);
        RequestParameter __param_ployid = new RequestParameter("ployid", ployid);

        __requestData.getParams().add(__param_billId);
        __requestData.getParams().add(__param_basicofferid);
        __requestData.getParams().add(__param_ployid);

        __msg.setData(__requestData);
        __msg.getHead().setUser(LIInvocationContext.USER);
        __msg.getHead().setPwd(LIInvocationContext.PWD);
        LIInvocationContext __ic = LIInvocationContext.getContext();
        if(__ic != null)
        {
            __msg.getHead().setOpType(__ic.getOpType());
            __msg.getHead().setUserMobile(__ic.getUserMobile());
            __msg.getHead().setUserCity(__ic.getUserCity());
            __msg.getHead().setBizCode(__ic.getBizCode());
            __msg.getHead().setUserBrand(__ic.getUserBrand());
            __msg.getHead().setOperId(__ic.getOperId());
            __msg.getHead().setTranceId(__ic.getTranceId());
            __msg.getHead().setClientPort(__ic.getClientPort());
            ((RequestData)(__msg.getData())).setContext(__ic.getContextParameter());
        }
        String __requestXML = __msg.toString();
        DEL218919Result __result = null;
        String __responseXML = "";
        Throwable __errorStack = null;
        ServiceMessage __response = null;
        try
        {
            __responseXML = __client.getPlatformConnection().send(__requestXML);
            __response = ServiceMessage.fromXML(__responseXML);
            BaseServiceInvocationResult __ret = ((ResponseData)(__response.getData())).getServiceResult();
            if(__ret instanceof DEL218919Result)
            {
                __result = (DEL218919Result)__ret;
            }
            else
            {
                __result = new DEL218919Result();
                __result.setResultCode(__ret.getResultCode());
                __result.setErrorCode(__ret.getErrorCode());
                __result.setErrorMessage(__ret.getErrorMessage());
            }
        }
        catch(Exception __e)
        {
            __errorStack = __e;
            throw new LIException(__e);
        }
        finally
        {
            long __endTime = System.currentTimeMillis();
            __client.log(__cmd, __client.getPlatformConnection().getRemoteURL(), __requestXML, __responseXML, __msg, __response, __beginTime, __endTime, __errorStack);
        }
        return __result;
    }
}
