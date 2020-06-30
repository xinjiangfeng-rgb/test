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
import com.xwtech.xwecp.service.logic.client_impl.common.IAddRedMemberService;
import com.xwtech.xwecp.service.logic.pojo.QRY040301Result;

/**
 * Created by 启东 on 2017/3/8.
 */
public class AddRedMemberServiceImpl extends BaseClientServiceImpl implements IAddRedMemberService {

    @Override
    public QRY040301Result queryAddMember(String phoneNum, String gbillId, String memSrvpkg, String memeberId) throws LIException {
        long __beginTime = java.lang.System.currentTimeMillis();
        String __cmd = "QRY040301";

        XWECPLIClient __client = XWECPLIClient.getInstance();
        ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
        RequestData __requestData = new RequestData();
        RequestParameter __param_phoneNum = new RequestParameter("phoneNum", phoneNum);
        __requestData.getParams().add(__param_phoneNum);
        RequestParameter __param_gbillId = new RequestParameter("gbillId", gbillId);
        __requestData.getParams().add(__param_gbillId);
        RequestParameter __param_memSrvpkg = new RequestParameter("memSrvpkg",memSrvpkg);
        __requestData.getParams().add(__param_memSrvpkg);
        RequestParameter __param_memeberId = new RequestParameter("memeberId",memeberId);
        __requestData.getParams().add(__param_memeberId);
        __msg.setData(__requestData);
        __msg.getHead().setUser(LIInvocationContext.USER);
        __msg.getHead().setPwd(LIInvocationContext.PWD);
        LIInvocationContext __ic = LIInvocationContext.getContext();
        if(__ic != null){
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
            QRY040301Result __result = null;
            String __responseXML = "";
            Throwable __errorStack = null;
            ServiceMessage __response = null;
        try{
            __responseXML = __client.getPlatformConnection().send(__requestXML);
            __response = ServiceMessage.fromXML(__responseXML);
            BaseServiceInvocationResult __e = ((ResponseData)(__response.getData())).getServiceResult();
            if(__e instanceof QRY040301Result){
                __result = (QRY040301Result) __e;
            }else {
                __result = new QRY040301Result();
                __result.setResultCode(__e.getResultCode());
                __result.setErrorCode(__e.getErrorCode());
                __result.setErrorMessage(__e.getErrorMessage());
            }
        }catch (Exception var23){
            __errorStack = var23;
            throw new LIException(var23);

        }finally {
            long __endTime = java.lang.System.currentTimeMillis();
            __client.log(__cmd,__client.getPlatformConnection().getRemoteURL(),__requestXML,__responseXML,__msg,__response,__beginTime,__endTime,__errorStack);
        }
        return __result;
    }
}

