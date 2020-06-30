package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY040100Result;

public interface IQueryFreeLoginUrlService
{
	public QRY040100Result getFreeLoginUrl(String phoneNum, String smsCode) throws LIException;

}