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
import com.xwtech.xwecp.service.logic.client_impl.common.IPaymentService;
import com.xwtech.xwecp.service.logic.pojo.PAY010001Result;

public class PaymentServiceImpl extends BaseClientServiceImpl implements IPaymentService {

	@Override
	public PAY010001Result payment(String idValue,String regionId,String operationType,String certificateType,String payPartLo,String payPartLt,
			String optCodeJF,String optCodeZS,String payPathJF,String payPathZS,String transIDHOJF,String transIDHOZS,String peerDate) throws LIException {
		long __beginTime = System.currentTimeMillis();
		String __cmd = "PAY010001";
		XWECPLIClient __client = XWECPLIClient.getInstance();
		ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
		RequestData __requestData = new RequestData();
		RequestParameter __param_idValue = new RequestParameter("idValue", idValue);
		__requestData.getParams().add(__param_idValue);
		RequestParameter __param_regionId = new RequestParameter("regionId", regionId);
		__requestData.getParams().add(__param_regionId);
		RequestParameter __param_operationType = new RequestParameter("operationType", operationType);
		__requestData.getParams().add(__param_operationType);
		RequestParameter __param_certificateType = new RequestParameter("certificateType", certificateType);
		__requestData.getParams().add(__param_certificateType);
		RequestParameter __param_payPartLo = new RequestParameter("payPartLo", payPartLo);
		__requestData.getParams().add(__param_payPartLo);
		RequestParameter __param_payPartLt = new RequestParameter("payPartLt", payPartLt);
		__requestData.getParams().add(__param_payPartLt);
		RequestParameter __param_optCodeJF = new RequestParameter("optCodeJF", optCodeJF);
		__requestData.getParams().add(__param_optCodeJF);
		RequestParameter __param_optCodeZS = new RequestParameter("optCodeZS", optCodeZS);
		__requestData.getParams().add(__param_optCodeZS);
		RequestParameter __param_payPathJF = new RequestParameter("payPathJF", payPathJF);
		__requestData.getParams().add(__param_payPathJF);
		RequestParameter __param_payPathZS = new RequestParameter("payPathZS", payPathZS);
		__requestData.getParams().add(__param_payPathZS);
		RequestParameter __param_transIDHOJF = new RequestParameter("transIDHOJF", transIDHOJF);
		__requestData.getParams().add(__param_transIDHOJF);
		RequestParameter __param_transIDHOZS = new RequestParameter("transIDHOZS", transIDHOZS);
		__requestData.getParams().add(__param_transIDHOZS);
		RequestParameter __param_peerDate = new RequestParameter("peerDate", peerDate);
		__requestData.getParams().add(__param_peerDate);
		
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
		PAY010001Result __result = null;
		String __responseXML = "";
		Throwable __errorStack = null;
		ServiceMessage __response = null;
		try
		{
			__responseXML = __client.getPlatformConnection().send(__requestXML);
			__response = ServiceMessage.fromXML(__responseXML);
			BaseServiceInvocationResult __ret = ((ResponseData)(__response.getData())).getServiceResult();
			if(__ret instanceof PAY010001Result)
			{
				__result = (PAY010001Result)__ret;
			}
			else
			{
				__result = new PAY010001Result();
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
