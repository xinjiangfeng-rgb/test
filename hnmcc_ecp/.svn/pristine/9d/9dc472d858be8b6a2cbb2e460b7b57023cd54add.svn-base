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
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryJfLableService;
import com.xwtech.xwecp.service.logic.pojo.QRY060002Result;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QueryJfLableServiceImpl extends BaseClientServiceImpl implements IQueryJfLableService {
	public static final String REMOTE_URL = "http://211.138.17.200:9060/pms_open_intfc_proxy/recommendMarket";
	//public static final String PLATFORM_CODE = "MOBILE2_HALL";
	public static final String EVENT_CODE = "EVENT_MOBILE_HALL";
	public static final String PASSWORD = "2F4D)PLI7b";
	public static final String VERSION = "0100";
	public static final String USER_ID = "YzzzzXBZT";
	public static final String USER_AREA_CODE = "371";

	public QRY060002Result qryLables(String phoneNum,String cityCode,String platFrom) throws LIException {
		String squn_num = System.currentTimeMillis() + phoneNum;
		String reqTime = nowTime();

		long __beginTime = System.currentTimeMillis();
		String __cmd = "QRY060002";
		XWECPLIClient __client = XWECPLIClient.getInstance();
		ServiceMessage __msg = __client.getMsgHelper().createRequestMessage(__cmd);
		RequestData __requestData = new RequestData();
		RequestParameter __param_version = new RequestParameter("Version", VERSION);
		__requestData.getParams().add(__param_version);
		RequestParameter __param_platform = new RequestParameter("Platform", platFrom);
		__requestData.getParams().add(__param_platform);
		RequestParameter __param_event = new RequestParameter("Event", EVENT_CODE);
		__requestData.getParams().add(__param_event);
		RequestParameter __param_operatetime = new RequestParameter("OperateTime", reqTime);
		__requestData.getParams().add(__param_operatetime);
		RequestParameter __param_sign = new RequestParameter("Sign", calcSign(platFrom,squn_num, phoneNum, reqTime));
		__requestData.getParams().add(__param_sign);
		RequestParameter __param_userid = new RequestParameter("userId", USER_ID);
		__requestData.getParams().add(__param_userid);
		RequestParameter __param_userareacode = new RequestParameter("userAreaCode", USER_AREA_CODE);
		__requestData.getParams().add(__param_userareacode);
		RequestParameter __param_sequenceno = new RequestParameter("SequenceNo", squn_num);
		__requestData.getParams().add(__param_sequenceno);
		RequestParameter __param_servnumber = new RequestParameter("phoneNum", phoneNum);
		__requestData.getParams().add(__param_servnumber);
		RequestParameter __param_areacode = new RequestParameter("areaCode", cityCode);
		__requestData.getParams().add(__param_areacode);
		RequestParameter __param_requesttime = new RequestParameter("request_time", reqTime);
		__requestData.getParams().add(__param_requesttime);

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
		QRY060002Result __result = null;
		String __responseXML = "";
		Throwable __errorStack = null;
		ServiceMessage __response = null;
		try
		{
			__responseXML = __client.getPlatformConnection().send(__requestXML);
			__response = ServiceMessage.fromXML(__responseXML);
			BaseServiceInvocationResult __ret = ((ResponseData)(__response.getData())).getServiceResult();
			if(__ret instanceof QRY060002Result)
			{
				__result = (QRY060002Result)__ret;
			}
			else
			{
				__result = new QRY060002Result();
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

	public String nowTime() {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 计算签名md5（渠道标识+事件标识+事件惟一序列号+服务号码+请求时间+密钥）
	 * @return
	 */
	public String calcSign(String platForm,String squn_num, String phone, String reqTime) {
		String rs = platForm + EVENT_CODE + squn_num + phone + reqTime + PASSWORD;
		System.out.println("加密字符串：" + rs);
		rs = DigestUtils.md5Hex(rs);
		System.out.println("计算签名结果：" + rs);
		return rs;
	}
}