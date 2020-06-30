package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IEsbOrderInvoiceService;
import com.xwtech.xwecp.service.logic.pojo.DEL170107Result;

public class EsbOrderInvoiceServiceImpl implements IEsbOrderInvoiceService {

	@Override
	public DEL170107Result esbOrderInvoice(String SvcNum, String opType, String pushType, String pushMail, String PUSH_TIME,
			String PUSH_WAY) {
		 Map<String, Object> mapParam = new HashMap<String, Object>();
	        mapParam.put("SvcNum", SvcNum);
	        mapParam.put("opType", opType);
	        mapParam.put("__cmd", "DEL170107");
	        mapParam.put("pushType", pushType);
	        mapParam.put("pushMail", pushMail);
	        mapParam.put("PUSH_TIME", PUSH_TIME);
	        mapParam.put("PUSH_WAY", PUSH_WAY);
	        BaseServiceInvocationResult rs;
	        try {
	            rs = RemoteCaller.callRemote(mapParam);
	            return (DEL170107Result) rs;
	        } catch (LIException e) { 
	            e.printStackTrace();
	        }
	        
	        return null;
	}

}
