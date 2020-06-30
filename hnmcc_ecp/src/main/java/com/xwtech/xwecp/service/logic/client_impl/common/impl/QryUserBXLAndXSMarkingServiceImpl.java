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
import com.xwtech.xwecp.service.logic.client_impl.common.IQryUserBXLAndXSMarkingService;
import com.xwtech.xwecp.service.logic.pojo.QRY199411Result;

public class QryUserBXLAndXSMarkingServiceImpl extends BaseClientServiceImpl implements IQryUserBXLAndXSMarkingService {

	@Override
	public QRY199411Result queryMarking(String phoneNum) throws LIException {
		long __beginTime = System.currentTimeMillis();
		String __cmd = "QRY199411";
		XWECPLIClient __client = XWECPLIClient.getInstance();
		ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
		RequestData __requestData = new RequestData();
		RequestParameter __param_phoneNum = new RequestParameter("svcNum", phoneNum);
		__requestData.getParams().add(__param_phoneNum);
		
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
		QRY199411Result __result = null;
		String __responseXML = "";
		Throwable __errorStack = null;
		ServiceMessage __response = null;
		try
		{
			__responseXML = __client.getPlatformConnection().send(__requestXML);
			__response = ServiceMessage.fromXML(__responseXML);
			BaseServiceInvocationResult __ret = ((ResponseData)(__response.getData())).getServiceResult();
			if(__ret instanceof QRY199411Result)
			{
				__result = (QRY199411Result)__ret;
			}
			else
			{
				__result = new QRY199411Result();
				__result.setResultCode(__ret.getResultCode());
				__result.setErrorCode(__ret.getErrorCode());
				__result.setErrorMessage(__ret.getErrorMessage());

/*

				MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) __responseXML);
				String str = bossJson.getStringResult();
				JSONArray array = JSONArray.parseArray(str);
				JSONObject jsonObj;
				for(int i=0;i<array.size();i++){
					jsonObj = (JSONObject) array.get(i);

					QRY199411Result qry199411Result = new QRY199411Result();
					//        						billDetail.setRetCode(jsonObj.getString("RET_CODE"));
						qry199411Result.setReturnCode(jsonObj.getString("RETURN_CODE"));
						qry199411Result.setBxlFlag(jsonObj.getString("BXL_FLAG"));
						qry199411Result.setLimitFlag(jsonObj.getString("LIMIT_FLAG"));
						qry199411Result.setLimitTime(jsonObj.getString("LIMIT_TIME"));
					__result=qry199411Result;
				}
*/
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
