package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY040201Result;

public interface IQrypackageInfoNewService
{
	public QRY040201Result qrypackageInfo(String phoneNum) throws LIException;

}