package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY200272Result;

/**
 * 查询用户订购国际业务到期时间及状态
 */
public interface IQryIntelBusStateService
{
	public QRY200272Result queryIntelBusState(String phoneNum)throws LIException;


}