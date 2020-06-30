package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.msg.RequestData;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.msg.ResponseData;
import com.xwtech.xwecp.msg.ServiceMessage;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQryCustomerPortraitService;
import com.xwtech.xwecp.service.logic.pojo.QRY181107Result;

/**
 * Created by 54344 on 2018/4/16.
 */
public class QryCustomerPortraitServiceImpl implements IQryCustomerPortraitService {

    /**
     *
     * @param servNo  主键，可以指定任一个属性为 主键。例如：客户手机号码。
     * @param profileId   客户画像ID，依赖于已经配置 的客户画像ID。由营销管理平台分配。
     * @param username  用户名
     * @param password  密码
     * @return
     * @throws LIException
     */
    @Override
    public QRY181107Result qryCustomerPortrait(String servNo, String profileId, String username, String password) throws LIException {
        long __beginTime = System.currentTimeMillis();
        String __cmd = "QRY181107";
        XWECPLIClient __client = XWECPLIClient.getInstance();
        ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
        RequestData __requestData = new RequestData();
        RequestParameter __param_servNo = new RequestParameter("servNo", servNo);
        RequestParameter __param_profileId = new RequestParameter("profileId", profileId);
        RequestParameter __param_username = new RequestParameter("username", username);
        RequestParameter __param_password = new RequestParameter("password", password);

        __requestData.getParams().add(__param_servNo);
        __requestData.getParams().add(__param_profileId);
        __requestData.getParams().add(__param_username);
        __requestData.getParams().add(__param_password);


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
        QRY181107Result __result = null;
        String __responseXML = "";
        Throwable __errorStack = null;
        ServiceMessage __response = null;
        try
        {
            __responseXML = __client.getPlatformConnection().send(__requestXML);
            __response = ServiceMessage.fromXML(__responseXML);
            BaseServiceInvocationResult __ret = ((ResponseData)(__response.getData())).getServiceResult();
            if(__ret instanceof QRY181107Result)
            {
                __result = (QRY181107Result)__ret;
            }
            else
            {
                __result = new QRY181107Result();
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
