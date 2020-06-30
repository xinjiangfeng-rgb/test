package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY040099Result;

public interface IQueryMyBaseInfoService
{
	public QRY040099Result queryMyBaseInfo(String userMsisdn, String startcycle) throws LIException;

}