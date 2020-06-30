package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryViceCardOutService;
import com.xwtech.xwecp.service.logic.pojo.QRY170413Result;

public class QueryViceCardOutServiceImpl implements IQueryViceCardOutService {

	@Override
	public QRY170413Result queryViceCardOutLog(String phone, String time,String staffId) {
		 Map<String, Object> mapParam = new HashMap<String, Object>();
	        mapParam.put("svcNum", phone);
	        mapParam.put("billingCycleId", time);
	        mapParam.put("__cmd", "QRY170413");
	        mapParam.put("staffId", staffId);
	        BaseServiceInvocationResult rs;
	        try {
	            rs = RemoteCaller.callRemote(mapParam);
	            return (QRY170413Result) rs;
	        } catch (LIException e) {
	            e.printStackTrace();
	        }
	        return null;
	}

}
