package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryBusinessStoreService;
import com.xwtech.xwecp.service.logic.pojo.QRY20170621Result;

public class QueryBusinessStoreServiceImpl implements IQueryBusinessStoreService {

	@Override
	public QRY20170621Result queryBusinessStore(String BILL_ID) {
		 Map<String, Object> mapParam = new HashMap<String, Object>();
	        mapParam.put("BILL_ID", BILL_ID);
	        mapParam.put("__cmd", "QRY20170621");
	        BaseServiceInvocationResult rs;
	        try {
	            rs = RemoteCaller.callRemote(mapParam);
	            return (QRY20170621Result) rs;
	        } catch (LIException e) {
	            e.printStackTrace();
	        }
	        
	        return null;
	}

}
