package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY010097Result;

public interface IQueryCurrentDayFeeService
{
	public QRY010097Result queryCurrentDayFee(String phoneNum) throws LIException;

}