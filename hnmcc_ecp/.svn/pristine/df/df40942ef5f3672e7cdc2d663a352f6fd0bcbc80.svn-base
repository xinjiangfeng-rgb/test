package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.client.BaseClientServiceImpl;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IFWBService;
import com.xwtech.xwecp.service.logic.pojo.DEL010031Result;
import com.xwtech.xwecp.service.RemoteCaller;

/**
 * MHF${request_seq}930017HNYD00XZT01 ${request_time}000 ----- FFFF
 * FFFFFFFF${phoneNum}${t}${bossCode}${t}ADD${t}${t}${t}${t}${t}MNF
 *
 */
public class FuWuBaoServiceImpl extends BaseClientServiceImpl implements IFWBService {

	@Override
	public DEL010031Result transactFWB(String phone, String bossCode, int oprType) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("phoneNum", phone);
		mapParam.put("bossCode", bossCode);
		mapParam.put("oprType", oprType);
		mapParam.put("__cmd", "DEL010031");
		BaseServiceInvocationResult rs;
		try {
			rs = RemoteCaller.callRemote(mapParam);
			return (DEL010031Result) rs;
		} catch (LIException e) {
			e.printStackTrace();
		}
		return null;
	}
}
