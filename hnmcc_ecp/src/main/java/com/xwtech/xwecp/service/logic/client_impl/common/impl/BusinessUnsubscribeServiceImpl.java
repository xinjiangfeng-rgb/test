package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IBusinessUnsubscribeService;
import com.xwtech.xwecp.service.logic.pojo.DEL201704Result;

public class BusinessUnsubscribeServiceImpl  implements IBusinessUnsubscribeService {

	@Override
	public DEL201704Result businessUnsubscribe(String phone, String id) {
		
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("SvcNum", phone);
		mapParam.put("SO_ID", id);
		mapParam.put("__cmd", "DEL201704");
		BaseServiceInvocationResult rs;
		try {
			rs = RemoteCaller.callRemote(mapParam);
			return (DEL201704Result) rs;
		} catch (LIException e) {
			e.printStackTrace();
		}
		return null;
	}

}
