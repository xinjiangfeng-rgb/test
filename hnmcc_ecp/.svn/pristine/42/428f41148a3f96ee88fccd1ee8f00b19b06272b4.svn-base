package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.client.BaseClientServiceImpl;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.msg.RequestData;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.msg.ResponseData;
import com.xwtech.xwecp.msg.ServiceMessage;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.ITransactBusinessService;
import com.xwtech.xwecp.service.logic.pojo.DEL010001Result;


public class TransactBusinessServiceClientImpl extends BaseClientServiceImpl implements ITransactBusinessService
{
	public DEL010001Result transactBusiness(String phoneNum, String id, int oprType, int chooseFlag, String beginDate, String endDate, String reserve1, String reserve2) throws LIException
	{
		long __beginTime = System.currentTimeMillis();
		String __cmd = "DEL010001";
		XWECPLIClient __client = XWECPLIClient.getInstance();
		ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
		RequestData __requestData = new RequestData();
		RequestParameter __param_phoneNum = new RequestParameter("phoneNum", phoneNum);
		__requestData.getParams().add(__param_phoneNum);
		RequestParameter __param_id = new RequestParameter("id", id);
		__requestData.getParams().add(__param_id);
		RequestParameter __param_oprType = new RequestParameter("oprType", oprType);
		__requestData.getParams().add(__param_oprType);
		RequestParameter __param_chooseFlag = new RequestParameter("chooseFlag", chooseFlag);
		__requestData.getParams().add(__param_chooseFlag);
		RequestParameter __param_beginDate = new RequestParameter("beginDate", beginDate);
		__requestData.getParams().add(__param_beginDate);
		RequestParameter __param_endDate = new RequestParameter("endDate", endDate);
		__requestData.getParams().add(__param_endDate);
		RequestParameter __param_reserve1 = new RequestParameter("reserve1", reserve1);
		__requestData.getParams().add(__param_reserve1);
		RequestParameter __param_reserve2 = new RequestParameter("reserve2", reserve2);
		__requestData.getParams().add(__param_reserve2);

		RequestParameter __param_OptrId = new RequestParameter("OptrId", " ");
		__requestData.getParams().add(__param_OptrId);
		RequestParameter __param_Telephonist = new RequestParameter("Telephonist", " ");
		__requestData.getParams().add(__param_Telephonist);

		RequestParameter __param_channelSource = new RequestParameter("channelSource", " ");
		__requestData.getParams().add(__param_channelSource);

		RequestParameter __param_Biztype = new RequestParameter("Biztype", " ");
		__requestData.getParams().add(__param_Biztype);

		RequestParameter __param_inUserPwd = new RequestParameter("inUserPwd", " ");
		__requestData.getParams().add(__param_inUserPwd);

		RequestParameter __param_inCertType = new RequestParameter("inCertType", " ");
		__requestData.getParams().add(__param_inCertType);

		RequestParameter __param_inCertNum = new RequestParameter("inCertNum", " ");
		__requestData.getParams().add(__param_inCertNum);
		//OptrId
		//Telephonist
		//Biztype
		//inUserPwd
		//inCertType
		//inCertNum
		//
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
			__msg.getHead().setPageNum(__ic.getPageNum());
			__msg.getHead().setRecNum(__ic.getRecNum());
			__msg.getHead().setSerialNum(__ic.getSerialNum());
			__msg.getHead().setJfserialNum(__ic.getJfserialNum());
			__msg.getHead().setProdId(__ic.getProdId());
			((RequestData)(__msg.getData())).setContext(__ic.getContextParameter());
		}
		String __requestXML = __msg.toString();
		DEL010001Result __result = null;
		String __responseXML = "";
		Throwable __errorStack = null;
		ServiceMessage __response = null;
		try
		{
			__responseXML = __client.getPlatformConnection().send(__requestXML);
			__response = ServiceMessage.fromXML(__responseXML);
			BaseServiceInvocationResult __ret = ((ResponseData)(__response.getData())).getServiceResult();
			if(__ret instanceof DEL010001Result)
			{
				__result = (DEL010001Result)__ret;
			}
			else
			{
				__result = new DEL010001Result();
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


	public DEL010001Result transactBusinessChannelSource(String phoneNum, String id, int oprType, int chooseFlag, String beginDate, String endDate, String reserve1, String reserve2,String channelSource) throws LIException
	{
		long __beginTime = System.currentTimeMillis();
		String __cmd = "DEL010001";
		XWECPLIClient __client = XWECPLIClient.getInstance();
		ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
		RequestData __requestData = new RequestData();
		RequestParameter __param_phoneNum = new RequestParameter("phoneNum", phoneNum);
		__requestData.getParams().add(__param_phoneNum);
		RequestParameter __param_id = new RequestParameter("id", id);
		__requestData.getParams().add(__param_id);
		RequestParameter __param_oprType = new RequestParameter("oprType", oprType);
		__requestData.getParams().add(__param_oprType);
		RequestParameter __param_chooseFlag = new RequestParameter("chooseFlag", chooseFlag);
		__requestData.getParams().add(__param_chooseFlag);
		RequestParameter __param_beginDate = new RequestParameter("beginDate", beginDate);
		__requestData.getParams().add(__param_beginDate);
		RequestParameter __param_endDate = new RequestParameter("endDate", endDate);
		__requestData.getParams().add(__param_endDate);
		RequestParameter __param_reserve1 = new RequestParameter("reserve1", reserve1);
		__requestData.getParams().add(__param_reserve1);
		RequestParameter __param_reserve2 = new RequestParameter("reserve2", reserve2);
		__requestData.getParams().add(__param_reserve2);

		RequestParameter __param_OptrId = new RequestParameter("OptrId", " ");
		__requestData.getParams().add(__param_OptrId);
		RequestParameter __param_Telephonist = new RequestParameter("Telephonist", " ");
		__requestData.getParams().add(__param_Telephonist);

		RequestParameter __param_channelSource = new RequestParameter("channelSource", channelSource);
		__requestData.getParams().add(__param_channelSource);
		//OptrId
		//Telephonist
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
			__msg.getHead().setPageNum(__ic.getPageNum());
			__msg.getHead().setRecNum(__ic.getRecNum());
			__msg.getHead().setSerialNum(__ic.getSerialNum());
			__msg.getHead().setJfserialNum(__ic.getJfserialNum());
			__msg.getHead().setProdId(__ic.getProdId());
			((RequestData)(__msg.getData())).setContext(__ic.getContextParameter());
		}
		String __requestXML = __msg.toString();
		DEL010001Result __result = null;
		String __responseXML = "";
		Throwable __errorStack = null;
		ServiceMessage __response = null;
		try
		{
			__responseXML = __client.getPlatformConnection().send(__requestXML);
			__response = ServiceMessage.fromXML(__responseXML);
			BaseServiceInvocationResult __ret = ((ResponseData)(__response.getData())).getServiceResult();
			if(__ret instanceof DEL010001Result)
			{
				__result = (DEL010001Result)__ret;
			}
			else
			{
				__result = new DEL010001Result();
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



	public DEL010001Result transactBusinessChannelSource(String phoneNum, String id, int oprType, int chooseFlag, String beginDate, String endDate, String reserve1, String reserve2,String channelSource,String optrId) throws LIException
	{
		long __beginTime = System.currentTimeMillis();
		String __cmd = "DEL010001";
		XWECPLIClient __client = XWECPLIClient.getInstance();
		ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
		RequestData __requestData = new RequestData();
		RequestParameter __param_phoneNum = new RequestParameter("phoneNum", phoneNum);
		__requestData.getParams().add(__param_phoneNum);
		RequestParameter __param_id = new RequestParameter("id", id);
		__requestData.getParams().add(__param_id);
		RequestParameter __param_oprType = new RequestParameter("oprType", oprType);
		__requestData.getParams().add(__param_oprType);
		RequestParameter __param_chooseFlag = new RequestParameter("chooseFlag", chooseFlag);
		__requestData.getParams().add(__param_chooseFlag);
		RequestParameter __param_beginDate = new RequestParameter("beginDate", beginDate);
		__requestData.getParams().add(__param_beginDate);
		RequestParameter __param_endDate = new RequestParameter("endDate", endDate);
		__requestData.getParams().add(__param_endDate);
		RequestParameter __param_reserve1 = new RequestParameter("reserve1", reserve1);
		__requestData.getParams().add(__param_reserve1);
		RequestParameter __param_reserve2 = new RequestParameter("reserve2", reserve2);
		__requestData.getParams().add(__param_reserve2);

		RequestParameter __param_OptrId = new RequestParameter("OptrId", optrId);
		__requestData.getParams().add(__param_OptrId);
		RequestParameter __param_Telephonist = new RequestParameter("Telephonist", " ");
		__requestData.getParams().add(__param_Telephonist);

		RequestParameter __param_channelSource = new RequestParameter("channelSource", channelSource);
		__requestData.getParams().add(__param_channelSource);
		//OptrId
		//Telephonist
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
			__msg.getHead().setPageNum(__ic.getPageNum());
			__msg.getHead().setRecNum(__ic.getRecNum());
			__msg.getHead().setSerialNum(__ic.getSerialNum());
			__msg.getHead().setJfserialNum(__ic.getJfserialNum());
			__msg.getHead().setProdId(__ic.getProdId());
			((RequestData)(__msg.getData())).setContext(__ic.getContextParameter());
		}
		String __requestXML = __msg.toString();
		DEL010001Result __result = null;
		String __responseXML = "";
		Throwable __errorStack = null;
		ServiceMessage __response = null;
		try
		{
			__responseXML = __client.getPlatformConnection().send(__requestXML);
			__response = ServiceMessage.fromXML(__responseXML);
			BaseServiceInvocationResult __ret = ((ResponseData)(__response.getData())).getServiceResult();
			if(__ret instanceof DEL010001Result)
			{
				__result = (DEL010001Result)__ret;
			}
			else
			{
				__result = new DEL010001Result();
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