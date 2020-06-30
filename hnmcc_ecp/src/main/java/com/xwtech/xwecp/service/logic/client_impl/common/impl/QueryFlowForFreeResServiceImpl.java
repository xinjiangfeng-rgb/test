package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryFlowForFreeResService;
import com.xwtech.xwecp.service.logic.pojo.QRY170215Result;

public class QueryFlowForFreeResServiceImpl  implements IQueryFlowForFreeResService{

	@Override
	public QRY170215Result queryFlowForFreeRes(String phone) {
		 Map<String, Object> mapParam = new HashMap<String, Object>();
	        mapParam.put("svcNum", phone);
	        mapParam.put("__cmd", "QRY170215");
	        BaseServiceInvocationResult rs;
	        try {
	            rs = RemoteCaller.callRemote(mapParam);
	            return (QRY170215Result) rs;
	        } catch (LIException e) { 
	            e.printStackTrace();
	        }
	        
	        return null;
	}

}
