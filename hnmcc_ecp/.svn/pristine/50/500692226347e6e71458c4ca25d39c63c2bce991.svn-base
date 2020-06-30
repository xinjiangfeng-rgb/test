package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryPkgConsumeService;
import com.xwtech.xwecp.service.logic.pojo.QRY170411Result;

public class QueryPkgConsumeServiceImpl  implements IQueryPkgConsumeService{

	@Override
	public QRY170411Result queryPkgConsume(String phone,String staffId,String billingCycleId) {
		 Map<String, Object> mapParam = new HashMap<String, Object>();
	        mapParam.put("svcNum", phone);
	        mapParam.put("__cmd", "QRY170411");
	        mapParam.put("staffId", staffId);
	        mapParam.put("billingCycleId", billingCycleId);
	        BaseServiceInvocationResult rs;
	        try {
	            rs = RemoteCaller.callRemote(mapParam);
	            return (QRY170411Result) rs;
	        } catch (LIException e) {
	            e.printStackTrace();
	        }
	        
	        return null;
	}

}
