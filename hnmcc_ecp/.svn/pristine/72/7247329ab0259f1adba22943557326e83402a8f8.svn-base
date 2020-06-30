package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQryCurrentStreamService;
import com.xwtech.xwecp.service.logic.pojo.QRY170911Result;

public class QryCurrentStreamServiceImpl implements IQryCurrentStreamService{

	@Override
	public QRY170911Result queryCurrentSteam(String SvcNum) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("SvcNum", SvcNum);
		paramMap.put("__cmd", "QRY170911");
		BaseServiceInvocationResult rs = null;
		try {
			rs = RemoteCaller.callRemote(paramMap);
			return (QRY170911Result)rs;
		} catch (LIException e) {
			e.printStackTrace();
		}
		return null;
	}
}
