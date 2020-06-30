package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY010098Result;

public interface IQueryCurrentBalanceService
{
	public QRY010098Result queryCurrentBalance(String phoneNum) throws LIException;

}