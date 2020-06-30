package com.xwtech.xwecp.service;

import java.util.Map;
import java.util.Map.Entry;

import com.xwtech.xwecp.msg.RequestData;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.msg.ResponseData;
import com.xwtech.xwecp.msg.ServiceMessage;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import org.apache.commons.lang3.StringUtils;

public class RemoteCaller {

	public static BaseServiceInvocationResult callRemote(Map<String, Object> paramMap) throws LIException {
		long __beginTime = System.currentTimeMillis();
		
		if(paramMap.get("__cmd")==null){
			return null;
		}
		String __cmd = paramMap.get("__cmd").toString();
		XWECPLIClient __client = XWECPLIClient.getInstance();
		ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
		RequestData __requestData = new RequestData();

		for (Entry<String, Object> en : paramMap.entrySet()) {
			
			if (StringUtils.isBlank(en.getKey())||en.getValue()==null) {
				continue;
			}
			
			RequestParameter __param = new RequestParameter(en.getKey(), en.getValue());
			__requestData.getParams().add(__param);
		}

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
			__msg.getHead().setPageNum(__ic.getPageNum());
			__msg.getHead().setRecNum(__ic.getRecNum());
			__msg.getHead().setSerialNum(__ic.getSerialNum());
			__msg.getHead().setJfserialNum(__ic.getJfserialNum());
			__msg.getHead().setProdId(__ic.getProdId());
			((RequestData) (__msg.getData())).setContext(__ic.getContextParameter());
		}
		String __requestXML = __msg.toString();
		String __responseXML = "";
		Throwable __errorStack = null;
		ServiceMessage __response = null;
		try {
			__responseXML = __client.getPlatformConnection().send(__requestXML);
			__response = ServiceMessage.fromXML(__responseXML);
			BaseServiceInvocationResult __ret = ((ResponseData) (__response.getData())).getServiceResult();
			return __ret;
		} catch (Exception __e) {
			__errorStack = __e;
			throw new LIException(__e);
		} finally {
			long __endTime = System.currentTimeMillis();
			__client.log(__cmd, __client.getPlatformConnection().getRemoteURL(), __requestXML, __responseXML, __msg,
					__response, __beginTime, __endTime, __errorStack);
		}
	}
}
