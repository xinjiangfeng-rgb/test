package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryBillChangeLogService;
import com.xwtech.xwecp.service.logic.pojo.QRY170416Result;

public class QueryBillChangeLogServiceImpl implements IQueryBillChangeLogService {

	@Override
	public QRY170416Result queryBillChangeLog(String phone, String time,String staffId) {
		 Map<String, Object> mapParam = new HashMap<String, Object>();
	        mapParam.put("svcNum", phone);
	        mapParam.put("billingCycleId", time);
	        mapParam.put("__cmd", "QRY170416");
	        mapParam.put("staffId", staffId);
	        BaseServiceInvocationResult rs;
	        try {
	            rs = RemoteCaller.callRemote(mapParam);
	            return (QRY170416Result) rs;
	        } catch (LIException e) { 
	            e.printStackTrace();
	        }
	        
	        return null;
	}

}
