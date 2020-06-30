package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.pojo.DEL010028Result;

/**
 * 基本服务包业务处理类
 * 
 * @author Administrator
 *
 */
public interface IJBFWBService {

	public DEL010028Result changeJBFWB(String phone, String bossCode);

	public DEL010028Result changeJBFWBChannelSource(String phone, String bossCode,String channelSource) ;

}
