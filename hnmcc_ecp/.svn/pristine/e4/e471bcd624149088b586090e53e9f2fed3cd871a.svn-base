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
import com.xwtech.xwecp.service.logic.client_impl.common.IMonthlyInvoiceOrderService;
import com.xwtech.xwecp.service.logic.pojo.DEL170108Result;

public class MonthlyInvoiceOrderServiceImpl extends BaseClientServiceImpl implements IMonthlyInvoiceOrderService {

        @Override
        public DEL170108Result monthlyInvoiceOrder(String SvcNum, String opType, String pushType, String pushMail, String pushTime, String pushWay) throws LIException {
            long __beginTime = System.currentTimeMillis();
            String __cmd = "DEL170108";
            XWECPLIClient __client = XWECPLIClient.getInstance();
            ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
            RequestData __requestData = new RequestData();
            RequestParameter __param_SvcNum = new RequestParameter("SvcNum", SvcNum);
            __requestData.getParams().add(__param_SvcNum);
            RequestParameter __param_opType = new RequestParameter("opType", opType);
            __requestData.getParams().add(__param_opType);
            RequestParameter __param_pushType = new RequestParameter("pushType", pushType);
            __requestData.getParams().add(__param_pushType);
            RequestParameter __param_pushMail= new RequestParameter("pushMail", pushMail);
            __requestData.getParams().add(__param_pushMail);
            RequestParameter __param_pushTime = new RequestParameter("pushTime", pushTime);
            __requestData.getParams().add(__param_pushTime);
            RequestParameter __param_pushWay= new RequestParameter("pushWay", pushWay);
            __requestData.getParams().add(__param_pushWay);
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
            DEL170108Result __result = null;
            String __responseXML = "";
            Throwable __errorStack = null;
            ServiceMessage __response = null;
            try {
                __responseXML = __client.getPlatformConnection().send(__requestXML);
                __response = ServiceMessage.fromXML(__responseXML);
                BaseServiceInvocationResult __ret = ((ResponseData) (__response.getData())).getServiceResult();
                if (__ret instanceof DEL170108Result) {
                    __result = (DEL170108Result) __ret;
                } else {
                    __result = new DEL170108Result();
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
