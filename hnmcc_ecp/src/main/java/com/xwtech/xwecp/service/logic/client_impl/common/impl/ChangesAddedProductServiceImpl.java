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
import com.xwtech.xwecp.service.logic.client_impl.common.IChangesAddedProductService;
import com.xwtech.xwecp.service.logic.pojo.DEL980011Result;

public class ChangesAddedProductServiceImpl extends BaseClientServiceImpl implements IChangesAddedProductService {
    @Override
    public DEL980011Result changesAddedProduct(String svcNum, String operMode, String prodCustomParaType, String addProdPrcId, String prodPrcPara, String optrId, String telephonist) throws LIException {

        long __beginTime = System.currentTimeMillis();
        String __cmd = "DEL980011";
        XWECPLIClient __client = XWECPLIClient.getInstance();
        ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
        RequestData __requestData = new RequestData();
        RequestParameter __param_svcNum = new RequestParameter("SvcNum", svcNum);
        __requestData.getParams().add(__param_svcNum);
        RequestParameter __param_operMode = new RequestParameter("OperMode", operMode);
        __requestData.getParams().add(__param_operMode);
        RequestParameter __param_prodCustomParaType = new RequestParameter("ProdCustomParaType", prodCustomParaType);
        __requestData.getParams().add(__param_prodCustomParaType);
        RequestParameter __param_addProdPrcId = new RequestParameter("AddProdPrcId", addProdPrcId);
        __requestData.getParams().add(__param_addProdPrcId);
        RequestParameter __param_prodPrcPara= new RequestParameter("ProdPrcPara", prodPrcPara);
        __requestData.getParams().add(__param_prodPrcPara);
        RequestParameter __param_OptrId = new RequestParameter("OptrId", optrId);
        __requestData.getParams().add(__param_OptrId);
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
        DEL980011Result __result = null;
        String __responseXML = "";
        Throwable __errorStack = null;
        ServiceMessage __response = null;
        try {
            __responseXML = __client.getPlatformConnection().send(__requestXML);
            __response = ServiceMessage.fromXML(__responseXML);
            BaseServiceInvocationResult __ret = ((ResponseData) (__response.getData())).getServiceResult();
            if (__ret instanceof DEL980011Result) {
                __result = (DEL980011Result) __ret;
            } else {
                __result = new DEL980011Result();
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
