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
import com.xwtech.xwecp.service.logic.client_impl.common.IQryTargetCustomerService;
import com.xwtech.xwecp.service.logic.pojo.QRY210203Result;

public class QryTargetCustomerServiceImpl extends BaseClientServiceImpl implements IQryTargetCustomerService{

	@Override
	public QRY210203Result qryTargetCustomerInfo(String phoneNumber, String dstCustGroupId) throws LIException {
		long beginTime = System.currentTimeMillis();
		String cmd = "QRY210203";
			XWECPLIClient client = XWECPLIClient.getInstance();
			ServiceMessage msg = client.getMsgHelper().createRequestMessage(cmd);
			RequestData requestData = new RequestData();
			RequestParameter param_phoneNum = new RequestParameter("SvcNum", phoneNumber);
			requestData.getParams().add(param_phoneNum);
			RequestParameter param_id = new RequestParameter("dstCustGroupId", dstCustGroupId);
			requestData.getParams().add(param_id);
			msg.setData(requestData);
			msg.getHead().setUser(LIInvocationContext.USER);
			msg.getHead().setPwd(LIInvocationContext.PWD);
			LIInvocationContext ic = LIInvocationContext.getContext();
			if(ic != null)
			{
				msg.getHead().setOpType(ic.getOpType());
				msg.getHead().setUserMobile(ic.getUserMobile());
				msg.getHead().setUserCity(ic.getUserCity());
				msg.getHead().setBizCode(ic.getBizCode());
				msg.getHead().setUserBrand(ic.getUserBrand());
				msg.getHead().setOperId(ic.getOperId());
				msg.getHead().setTranceId(ic.getTranceId());
				msg.getHead().setClientPort(ic.getClientPort());
				((RequestData)(msg.getData())).setContext(ic.getContextParameter());
			}
			String requestXML = msg.toString();
			QRY210203Result result = null;
			String responseXML = "";
			Throwable errorStack = null;
			ServiceMessage response = null;
			try
			{
				responseXML = client.getPlatformConnection().send(requestXML);
				response = ServiceMessage.fromXML(responseXML);
				BaseServiceInvocationResult ret = ((ResponseData)(response.getData())).getServiceResult();
				if(ret instanceof QRY210203Result)
				{
					result = (QRY210203Result)ret;
				}
				else
				{
					result = new QRY210203Result();
					result.setResultCode(ret.getResultCode());
					result.setErrorCode(ret.getErrorCode());
					result.setErrorMessage(ret.getErrorMessage());
				}
			}
			catch(Exception e)
			{
				errorStack = e;
				throw new LIException(e);
			}
			finally
			{
				long endTime = System.currentTimeMillis();
				client.log(cmd, client.getPlatformConnection().getRemoteURL(), requestXML, responseXML, msg, response, beginTime, endTime, errorStack);
			}
			return result;
		}
}
