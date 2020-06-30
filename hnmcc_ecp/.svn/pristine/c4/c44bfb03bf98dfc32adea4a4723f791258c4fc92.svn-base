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
import com.xwtech.xwecp.service.logic.client_impl.common.IInternationalBasicService;
import com.xwtech.xwecp.service.logic.pojo.DEL200240Result;


public class InternationalBasicServiceImpl extends BaseClientServiceImpl implements IInternationalBasicService {


	public DEL200240Result internationalBasicBusinessTransaction(String phoneNum,
                                                                 String operMode,
                                                                 String prodCustomParaType,
                                                                 String addProdPrcId,
                                                                 String prodPrcPara,
                                                                 String optrId,
                                                                 String telephonist,
                                                                 String multPricePlanId,
                                                                 String begdate,
                                                                 String enddate,
                                                                 String remindGPRS) throws LIException
	{
		long __beginTime = System.currentTimeMillis();
		String __cmd = "DEL200240";
		XWECPLIClient __client = XWECPLIClient.getInstance();
		ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
		RequestData __requestData = new RequestData();
		RequestParameter __param_SvcNum = new RequestParameter("SvcNum", phoneNum);
		__requestData.getParams().add(__param_SvcNum);
		RequestParameter __param_operMode = new RequestParameter("operMode", operMode);
		__requestData.getParams().add(__param_operMode);
		RequestParameter __param_prodCustomParaType = new RequestParameter("prodCustomParaType", prodCustomParaType);
		__requestData.getParams().add(__param_prodCustomParaType);
		RequestParameter __param_addProdPrcId = new RequestParameter("addProdPrcId", addProdPrcId);
		__requestData.getParams().add(__param_addProdPrcId);
		RequestParameter __param_prodPrcPara  = new RequestParameter("prodPrcPara", prodPrcPara);
		__requestData.getParams().add(__param_prodPrcPara);
		RequestParameter __param_optrId = new RequestParameter("optrId",   optrId);
		__requestData.getParams().add(__param_optrId);
		RequestParameter __param_telephonist = new RequestParameter("telephonist",   telephonist);
		__requestData.getParams().add(__param_telephonist);
		RequestParameter __param_multPricePlanId = new RequestParameter("multPricePlanId",   multPricePlanId);
		__requestData.getParams().add(__param_multPricePlanId);
		RequestParameter __param_begdate = new RequestParameter("begdate",   begdate);
		__requestData.getParams().add(__param_begdate);
		RequestParameter __param_enddate = new RequestParameter("enddate",   enddate);
		__requestData.getParams().add(__param_enddate);
		RequestParameter __param_remindGPRS = new RequestParameter("remindGPRS",   remindGPRS);
		__requestData.getParams().add(__param_remindGPRS);


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
		DEL200240Result __result = null;
		String __responseXML = "";
		Throwable __errorStack = null;
		ServiceMessage __response = null;
		try
		{
			__responseXML = __client.getPlatformConnection().send(__requestXML);
			__response = ServiceMessage.fromXML(__responseXML);
			BaseServiceInvocationResult __ret = ((ResponseData)(__response.getData())).getServiceResult();
			if(__ret instanceof DEL200240Result)
			{
				__result = (DEL200240Result)__ret;
			}
			else
			{
				__result = new DEL200240Result();
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