package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY010018Result;

public interface IQueryNewRealTimeBillingService
{
	public QRY010018Result queryNewRealTimeBilling(String phoneNum, String startcycle) throws LIException;

}