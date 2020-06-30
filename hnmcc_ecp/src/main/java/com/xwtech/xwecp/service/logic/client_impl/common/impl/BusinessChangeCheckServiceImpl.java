package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IBusinessChangeCheckService;
import com.xwtech.xwecp.service.logic.pojo.QRY201612Result;

public class BusinessChangeCheckServiceImpl implements IBusinessChangeCheckService{

	@Override
	public QRY201612Result businessChangeCheck(String SvcNum, String InstanceId) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("SvcNum", SvcNum);
		mapParam.put("InstanceId", InstanceId);
		mapParam.put("__cmd", "QRY201612");
		BaseServiceInvocationResult rs;
		try {
			rs = RemoteCaller.callRemote(mapParam);
			return (QRY201612Result) rs;
		} catch (LIException e) {
			e.printStackTrace();
		}
		return null;
	}

}
