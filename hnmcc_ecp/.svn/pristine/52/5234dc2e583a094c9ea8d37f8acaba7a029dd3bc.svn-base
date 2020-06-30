package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.msg.RequestData;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.msg.ResponseData;
import com.xwtech.xwecp.msg.ServiceMessage;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IGroupMemberCRUDService;
import com.xwtech.xwecp.service.logic.pojo.DEL181228Result;


/**
 * 转套餐并参加活动
 */
public class GroupMemberCRUDImpl implements IGroupMemberCRUDService {


    /**
     *
     * @param gBillId       集团计费号
     * @param flag          操作标识
     * @param billId        成员号码
     * @param validMonth    赠送流量有效期
     * @param validType     生效方式
     * @param memSrvpkg     成员资费包
     * @param custOrderId   订单流水
     * @param smsTemplate   短息模板
     * @return
     * @throws LIException
     */
    @Override
    public DEL181228Result delGroupMemberCRUD(String gBillId,
                                              String flag,
                                              String billId,
                                              String validMonth,
                                              String validType,
                                              String memSrvpkg,
                                              String custOrderId,
                                              String smsTemplate) throws LIException {


        long __beginTime = System.currentTimeMillis();
        String __cmd = "DEL181228";
        XWECPLIClient __client = XWECPLIClient.getInstance();
        ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
        RequestData __requestData = new RequestData();

        RequestParameter __param_gBillId = new RequestParameter("GBILL_ID", gBillId);
        RequestParameter __param_flag = new RequestParameter("FLAG", flag);
        RequestParameter __param_billId = new RequestParameter("BILL_ID", billId);
        RequestParameter __param_validMonth = new RequestParameter("VALID_MONTH", validMonth);
        RequestParameter __param_validType = new RequestParameter("VALID_TYPE", validType);
        RequestParameter __param_memSrvpkg = new RequestParameter("MEM_SRVPKG", memSrvpkg);
        RequestParameter __param_custOrderId = new RequestParameter("CUST_ORDER_ID", custOrderId);
        RequestParameter __param_smsTemplate = new RequestParameter("SMS_TEMPLATE", smsTemplate);


        __requestData.getParams().add(__param_gBillId);
        __requestData.getParams().add(__param_flag);
        __requestData.getParams().add(__param_billId);
        __requestData.getParams().add(__param_validMonth);
        __requestData.getParams().add(__param_validType);
        __requestData.getParams().add(__param_memSrvpkg);
        __requestData.getParams().add(__param_custOrderId);
        __requestData.getParams().add(__param_smsTemplate);


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
        DEL181228Result __result = null;
        String __responseXML = "";
        Throwable __errorStack = null;
        ServiceMessage __response = null;
        try
        {
            __responseXML = __client.getPlatformConnection().send(__requestXML);
            __response = ServiceMessage.fromXML(__responseXML);
            BaseServiceInvocationResult __ret = ((ResponseData)(__response.getData())).getServiceResult();
            if(__ret instanceof DEL181228Result)
            {
                __result = (DEL181228Result)__ret;
            }
            else
            {
                __result = new DEL181228Result();
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
