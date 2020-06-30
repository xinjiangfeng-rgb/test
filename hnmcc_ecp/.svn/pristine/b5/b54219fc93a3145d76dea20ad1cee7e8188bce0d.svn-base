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
import com.xwtech.xwecp.service.logic.client_impl.common.IQryAccountBooksService;
import com.xwtech.xwecp.service.logic.pojo.QRY920137Result;

/**
 账单账户入账明细
 */
public class QryAccountBooksServiceImpl extends BaseClientServiceImpl implements IQryAccountBooksService {

	/**
	 *
	 * @param svcNum		号码
	 * @param billingCycle	账期
	 * @param cycleBeginDate	账期开始时间
	 * @param cycleEndDate		账期结束时间
	 * @param optrid		操作员工号
	 * @return
	 * @throws LIException
	 */
	@Override
	public QRY920137Result qryAccountBooks(String svcNum, String billingCycle, String cycleBeginDate, String cycleEndDate, String optrid) throws LIException {
		long __beginTime = System.currentTimeMillis();
		String __cmd = "QRY920137";
		XWECPLIClient __client = XWECPLIClient.getInstance();
		ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
		RequestData __requestData = new RequestData();
		RequestParameter __param_svcNum = new RequestParameter("svcNum", svcNum);
		RequestParameter __param_billingCycle = new RequestParameter("billingCycle", billingCycle);
		RequestParameter __param_cycleBeginDate = new RequestParameter("cycleBeginDate", cycleBeginDate);
		RequestParameter __param_cycleEndDate = new RequestParameter("cycleEndDate", cycleEndDate);
		RequestParameter __param_optrid = new RequestParameter("optrid", optrid);
		__requestData.getParams().add(__param_svcNum);
		__requestData.getParams().add(__param_billingCycle);
		__requestData.getParams().add(__param_cycleBeginDate);
		__requestData.getParams().add(__param_cycleEndDate);
		__requestData.getParams().add(__param_optrid);


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
		QRY920137Result __result = null;
		String __responseXML = "";
		Throwable __errorStack = null;
		ServiceMessage __response = null;
		try
		{
			__responseXML = __client.getPlatformConnection().send(__requestXML);
			__response = ServiceMessage.fromXML(__responseXML);
			BaseServiceInvocationResult __ret = ((ResponseData)(__response.getData())).getServiceResult();
			if(__ret instanceof QRY920137Result)
			{
				__result = (QRY920137Result)__ret;
			}
			else
			{
				__result = new QRY920137Result();
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
