package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.pojo.DEL010030Result;

/**
 * 梦网产品业务处理Service
 * 
 * @author Administrator
 *
 */
public interface IMWCPBService {

	public DEL010030Result transactMWCP(String phone, String bizType, String spId, String spBizCode, int optType);
	public DEL010030Result transactMWCPchannelSource(String phone, String bizType, String spId, String spBizCode, int optType,String channelSource);
}
