package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryFreeFlowForMonthService;
import com.xwtech.xwecp.service.logic.pojo.QRY170214Result;

public class QueryFreeFlowForMonthServiceImpl implements IQueryFreeFlowForMonthService  {

	@Override
	public QRY170214Result queryFreeFlowForMonth(String phone, String ItemId) {
		 Map<String, Object> mapParam = new HashMap<String, Object>();
	        mapParam.put("svcNum", phone);
	        mapParam.put("ItemId", ItemId);
	        mapParam.put("__cmd", "QRY170214");
	        BaseServiceInvocationResult rs;
	        try {
	            rs = RemoteCaller.callRemote(mapParam);
	            return (QRY170214Result) rs;
	        } catch (LIException e) { 
	            e.printStackTrace();
	        }
	        
	        return null;
	}

}
