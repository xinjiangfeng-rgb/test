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
import com.xwtech.xwecp.service.logic.client_impl.common.IPaymentMixService;
import com.xwtech.xwecp.service.logic.pojo.PAY010002Result;

public class PaymentMixServiceImpl extends BaseClientServiceImpl implements IPaymentMixService {

	@Override
	public PAY010002Result paymentMix(String SvcNum, String optSN, String payedType, String certificateType,
			String certificateCode, String optCode, String optSeq, String prodPricId, String expDate, String scrValue,
			String batchFlag, String AuthrNum, String remarks) throws LIException {
		long __beginTime = System.currentTimeMillis();
		String __cmd = "PAY010002";
		XWECPLIClient __client = XWECPLIClient.getInstance();
		ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
		RequestData __requestData = new RequestData();
		RequestParameter __param_SvcNum = new RequestParameter("SvcNum", SvcNum);
		__requestData.getParams().add(__param_SvcNum);
		RequestParameter __param_optSN = new RequestParameter("optSN", optSN);
		__requestData.getParams().add(__param_optSN);
		RequestParameter __param_payedType = new RequestParameter("payedType", payedType);
		__requestData.getParams().add(__param_payedType);
		RequestParameter __param_certificateType = new RequestParameter("certificateType", certificateType);
		__requestData.getParams().add(__param_certificateType);
		RequestParameter __param_certificateCode = new RequestParameter("certificateCode", certificateCode);
		__requestData.getParams().add(__param_certificateCode);
		RequestParameter __param_optCode = new RequestParameter("optCode", optCode);
		__requestData.getParams().add(__param_optCode);
		RequestParameter __param_optSeq = new RequestParameter("optSeq", optSeq);
		__requestData.getParams().add(__param_optSeq);
		RequestParameter __param_prodPricId = new RequestParameter("prodPricId", prodPricId);
		__requestData.getParams().add(__param_prodPricId);
		RequestParameter __param_expDate = new RequestParameter("expDate", expDate);
		__requestData.getParams().add(__param_expDate);
		RequestParameter __param_scrValue = new RequestParameter("scrValue", scrValue);
		__requestData.getParams().add(__param_scrValue);
		RequestParameter __param_batchFlag = new RequestParameter("batchFlag", batchFlag);
		__requestData.getParams().add(__param_batchFlag);
		RequestParameter __param_AuthrNum = new RequestParameter("AuthrNum", AuthrNum);
		__requestData.getParams().add(__param_AuthrNum);
		RequestParameter __param_remarks = new RequestParameter("remarks", remarks);
		__requestData.getParams().add(__param_remarks);
		
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
		PAY010002Result __result = null;
		String __responseXML = "";
		Throwable __errorStack = null;
		ServiceMessage __response = null;
		try
		{
			__responseXML = __client.getPlatformConnection().send(__requestXML);
			__response = ServiceMessage.fromXML(__responseXML);
			BaseServiceInvocationResult __ret = ((ResponseData)(__response.getData())).getServiceResult();
			if(__ret instanceof PAY010002Result)
			{
				__result = (PAY010002Result)__ret;
			}
			else
			{
				__result = new PAY010002Result();
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
