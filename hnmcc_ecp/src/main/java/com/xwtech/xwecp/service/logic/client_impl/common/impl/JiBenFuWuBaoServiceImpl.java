package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.client.BaseClientServiceImpl;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IJBFWBService;
import com.xwtech.xwecp.service.logic.pojo.DEL010028Result;
import com.xwtech.xwecp.service.RemoteCaller;

/**
 * 基本服务包Service <br/>
 * 1 SvcNum 电话号码 C 20 Y 电话号码<br/>
 * 2 InstanceId 话务员工号 C 200 Y 话务员工号<br/>
 * 3 OptrId 产品ID C 20 Y 产品ID<br/>
 * 4 Telephonist 电话员工号 C 20 Y 电话员工号<br/>
 * 
 * MHF${request_seq}930021HNYD00XZT01 ${request_time}000 ----- FFFF
 * FFFFFFFF${phoneNum}${t}${bossCode}${t}${t}MNF
 */
public class JiBenFuWuBaoServiceImpl extends BaseClientServiceImpl implements IJBFWBService {

	@Override
	public DEL010028Result changeJBFWB(String phone, String bossCode) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("phoneNum", phone);
		mapParam.put("bossCode", bossCode);
		mapParam.put("channelSource", " ");
		mapParam.put("OptrId", " ");
		mapParam.put("Telephonist", " ");

		mapParam.put("__cmd", "DEL010028");
		BaseServiceInvocationResult rs;
		try {
			rs = RemoteCaller.callRemote(mapParam);
			return (DEL010028Result) rs;
		} catch (LIException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public DEL010028Result changeJBFWBChannelSource(String phone, String bossCode,String channelSource) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("phoneNum", phone);
		mapParam.put("bossCode", bossCode);
		mapParam.put("channelSource", channelSource);
		mapParam.put("OptrId", " ");
		mapParam.put("Telephonist", " ");

		mapParam.put("__cmd", "DEL010028");
		BaseServiceInvocationResult rs;
		try {
			rs = RemoteCaller.callRemote(mapParam);
			return (DEL010028Result) rs;
		} catch (LIException e) {
			e.printStackTrace();
		}
		return null;
	}
}
