package com.xwtech.xwecp.dao;

import java.util.List;
import java.util.Map;

import com.xwtech.xwecp.pojo.ChannelInfo;
import com.xwtech.xwecp.pojo.ChannelLimitInfo;
import com.xwtech.xwecp.pojo.IterfaceCapacity;

public interface IServiceCallerPrivilegeDAO
{
	/**
	 * 获取渠道信息
	 * 
	 * @param channel
	 * @return
	 * @throws DAOException
	 */
	public ChannelInfo getChannelInfo(String channel) throws DAOException;

	public List<String> getCallerAcceptAddress(String channel);

	public List<String> getCallerAcceptLiLst(String channel);

	/**
	 * 获取渠道詳情信息
	 * 
	 * @param channel
	 *            渠道编码
	 * @param date
	 *            日期
	 * @return
	 * @throws DAOException
	 */
	public List<ChannelLimitInfo> getChannelLimitInfoList(String channel, String date);

	/**
	 * 获得接口连接数
	 * @param itfId
	 * @return
	 */
	public IterfaceCapacity getIterfaceCapacity(String itfId);
	
	/**
	 * 获得渠道额外参数配置
	 * @param channel
	 * @return
	 */
	public Map<String, String> getChannelExtParams(String channel);
	

}
