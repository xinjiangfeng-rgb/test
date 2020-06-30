package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryAccountService;
import com.xwtech.xwecp.service.logic.pojo.QRY170421Result;

public class QueryAccountServiceImpl  implements IQueryAccountService{

	@Override
	public QRY170421Result queryAccount(String SVCNUM) {
		 Map<String, Object> mapParam = new HashMap<String, Object>();
	        mapParam.put("SVCNUM", SVCNUM);
	        mapParam.put("__cmd", "QRY170421");
	        BaseServiceInvocationResult rs;
	        try {
	            rs = RemoteCaller.callRemote(mapParam);
	            return (QRY170421Result) rs;
	        } catch (LIException e) {
	            e.printStackTrace();
	        }
	        
	        return null;
	}

}
