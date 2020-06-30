package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.INewBillQueryService;
import com.xwtech.xwecp.service.logic.pojo.QRY920731Result;

public class NewBillQueryServiceImpl implements INewBillQueryService {

	@Override
	public QRY920731Result newBillQuery(String SVCNUM, String BILLING_CYCLE) {
		 Map<String, Object> mapParam = new HashMap<String, Object>();
	        mapParam.put("SVCNUM", SVCNUM);
	        mapParam.put("BILLING_CYCLE", BILLING_CYCLE);
	        mapParam.put("__cmd", "QRY920731");
	        BaseServiceInvocationResult rs;
	        try {
	            rs = RemoteCaller.callRemote(mapParam);
	            return (QRY920731Result) rs;
	        } catch (LIException e) {
	            e.printStackTrace();
	        }
	        
	        return null;
	}

}
