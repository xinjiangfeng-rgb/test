package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryPayLogService;
import com.xwtech.xwecp.service.logic.pojo.QRY170412Result;

public class QueryPayLogServiceImpl implements IQueryPayLogService {

	@Override
	public QRY170412Result queryPayLog(String phone, String time,String staffId) {
		 Map<String, Object> mapParam = new HashMap<String, Object>();
	        mapParam.put("svcNum", phone);
	        mapParam.put("billingCycleId", time);
	        mapParam.put("__cmd", "QRY170412");
	        mapParam.put("staffId", staffId);
	        BaseServiceInvocationResult rs;
	        try {
	            rs = RemoteCaller.callRemote(mapParam);
	            return (QRY170412Result) rs;
	        } catch (LIException e) {
	            e.printStackTrace();
	        }
	        
	        return null;
	}

}
