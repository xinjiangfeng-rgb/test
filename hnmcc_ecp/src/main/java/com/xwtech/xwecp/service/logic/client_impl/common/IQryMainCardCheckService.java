package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY180501Result;

/**
 * 主卡校验接口
 */
public interface IQryMainCardCheckService
{
	public QRY180501Result queryMarking(String phoneNum)throws LIException;


}