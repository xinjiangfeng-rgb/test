package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY010099Result;

public interface IQueryActivityBalanceService
{
	public QRY010099Result queryActivityBalance(String phoneNum) throws LIException;

}