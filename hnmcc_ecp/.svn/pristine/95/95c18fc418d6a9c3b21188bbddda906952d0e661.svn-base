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
import com.xwtech.xwecp.service.logic.client_impl.common.IRedMemberService;
import com.xwtech.xwecp.service.logic.pojo.QRY040300Result;

/**
 * .
 */
public class RedMemberServiceImpl extends BaseClientServiceImpl implements IRedMemberService {
    @Override
    public QRY040300Result queryRedMember(String billID) throws LIException {
        long __beginTime = java.lang.System.currentTimeMillis();
        String __cmd = "QRY040300";

        XWECPLIClient __client = XWECPLIClient.getInstance();
        ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
        RequestData __requestData = new RequestData();
        RequestParameter __param_phoneNum = new RequestParameter("billID", billID);
        __requestData.getParams().add(__param_phoneNum);
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
        QRY040300Result __result = null;
        String __responseXML = "";
        Exception __errorStack = null;
        ServiceMessage __response = null;
        try{
            __responseXML = __client.getPlatformConnection().send(__requestXML);
            __response = ServiceMessage.fromXML(__responseXML);
            BaseServiceInvocationResult __e = ((ResponseData)(__response.getData())).getServiceResult();
            if(__e instanceof QRY040300Result){
                __result = (QRY040300Result)__e;
            }else {
                __result = new QRY040300Result();
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
