package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryVoiceResHisService;
import com.xwtech.xwecp.service.logic.pojo.QRY170610Result;

public class QueryVoiceResHisServiceImpl  implements IQueryVoiceResHisService{

	@Override
	public QRY170610Result queryVoiceResHis(String billId, String billingCycleId) {
		 Map<String, Object> mapParam = new HashMap<String, Object>();
	        mapParam.put("__cmd", "QRY170610");
	        mapParam.put("billId", billId);
	        mapParam.put("billingCycleId",billingCycleId);
	        BaseServiceInvocationResult rs;
	        try {
	            rs = RemoteCaller.callRemote(mapParam);
	            return (QRY170610Result) rs;
	        } catch (LIException e) {
	            e.printStackTrace();
	        }
	        return null;
	}

}
