package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.ImarketingCommunicationService;
import com.xwtech.xwecp.service.logic.pojo.MCSResult;

public class MarketingCommunicationServiceImpl implements ImarketingCommunicationService {

	Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

	@Override
	public List<MCSResult> marketingCommunication(String mobile, String cityCode,
			Map<String, Object> eventPropertyParam) {
		String squn_num = System.currentTimeMillis() + mobile;
		String reqTime = ConfigCenter.nowTime();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("Version", ConfigCenter.VERSION);
		param.put("Platform", ConfigCenter.PLATFORM_CODE);
		param.put("Event", ConfigCenter.EVENT_CODE);
		param.put("OperateTime", reqTime);
		param.put("Sign", ConfigCenter.calcSign(squn_num, mobile, reqTime));
		param.put("userId", ConfigCenter.USER_ID);
		param.put("userAreaCode", ConfigCenter.USER_AREA_CODE);
		param.put("SequenceNo", squn_num);
		param.put("ServNumber", mobile);
		param.put("AreaCode", cityCode);
		try {
			BaseServiceInvocationResult rs = RemoteCaller.callRemote(param);
			logger.info(JSONObject.toJSONString(rs));
		} catch (LIException e) {
			e.printStackTrace();
		}
		return null;
	}

}
