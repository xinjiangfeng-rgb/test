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
import com.xwtech.xwecp.service.logic.client_impl.common.IPayAttestationService;
import com.xwtech.xwecp.service.logic.pojo.PAY181114Result;


/**
 * 线上统一支付验签接口通用服务
 */
public class PayAttestationServiceImpl extends BaseClientServiceImpl implements IPayAttestationService {

	/**
	 *
	 * @param activityCode		交易代码，请填写U105_002
	 * @param busiType		业务类型
	 * @param clientIP		客户端IP
	 * @param customParam		商户自定义参数
	 * @param gift		商户赠送金额(单位分)
	 * @param iDType		中国移动用户标识类型
	 * @param iDValue		中国移动用户号码
	 * @param orderMoney		订单总金额(单位分)
	 * @param payment		用户支付金额(单位分)
	 * @param paymentType		支付方式
	 * @param productDesc		商品描述
	 * @param productID		商品编号
	 * @param productName		商品名称
	 * @param productType		商品类型
	 * @param productURL		商品展示URL
	 * @param returnURL		页面返回的URL
	 * @return
	 * @throws LIException
	 */
	@Override
	public PAY181114Result payAttestation(String activityCode,
										  String busiType,
										  String clientIP,
										  String customParam,
										  String gift,
										  String iDType,
										  String iDValue,
										  String orderMoney,
										  String payment,
										  String paymentType,
										  String productDesc,
										  String productID,
										  String productName,
										  String productType,
										  String productURL,
										  String returnURL) throws LIException {
		long __beginTime = System.currentTimeMillis();
		String __cmd = "PAY181114";
		XWECPLIClient __client = XWECPLIClient.getInstance();
		ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
		RequestData __requestData = new RequestData();
		RequestParameter __param_activityCode = new RequestParameter("activityCode",activityCode);
		RequestParameter __param_busiType = new RequestParameter("busiType",busiType);
		RequestParameter __param_clientIP = new RequestParameter("clientIP",clientIP);
		RequestParameter __param_customParam = new RequestParameter("customParam",customParam);
		RequestParameter __param_gift = new RequestParameter("gift",gift);
		RequestParameter __param_iDType = new RequestParameter("iDType",iDType);
		RequestParameter __param_iDValue = new RequestParameter("iDValue",iDValue);
		RequestParameter __param_orderMoney = new RequestParameter("orderMoney",orderMoney);
		RequestParameter __param_payment = new RequestParameter("payment",payment);
		RequestParameter __param_paymentType = new RequestParameter("paymentType",paymentType);
		RequestParameter __param_productDesc = new RequestParameter("productDesc",productDesc);
		RequestParameter __param_productID = new RequestParameter("productID",productID);
		RequestParameter __param_productName = new RequestParameter("productName",productName);
		RequestParameter __param_productType = new RequestParameter("productType",productType);
		RequestParameter __param_productURL = new RequestParameter("productURL",productURL);
		RequestParameter __param_returnURL = new RequestParameter("returnURL",returnURL);

		__requestData.getParams().add(__param_activityCode);
		__requestData.getParams().add(__param_busiType);
		__requestData.getParams().add(__param_clientIP);
		__requestData.getParams().add(__param_customParam);
		__requestData.getParams().add(__param_gift);
		__requestData.getParams().add(__param_iDType);
		__requestData.getParams().add(__param_iDValue);
		__requestData.getParams().add(__param_orderMoney);
		__requestData.getParams().add(__param_payment);
		__requestData.getParams().add(__param_paymentType);
		__requestData.getParams().add(__param_productDesc);
		__requestData.getParams().add(__param_productID);
		__requestData.getParams().add(__param_productName);
		__requestData.getParams().add(__param_productType);
		__requestData.getParams().add(__param_productURL);
		__requestData.getParams().add(__param_returnURL);


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
		PAY181114Result __result = null;
		String __responseXML = "";
		Throwable __errorStack = null;
		ServiceMessage __response = null;
		try
		{
			__responseXML = __client.getPlatformConnection().send(__requestXML);
			__response = ServiceMessage.fromXML(__responseXML);
			BaseServiceInvocationResult __ret = ((ResponseData)(__response.getData())).getServiceResult();
			if(__ret instanceof PAY181114Result)
			{
				__result = (PAY181114Result)__ret;
			}
			else
			{
				__result = new PAY181114Result();
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
