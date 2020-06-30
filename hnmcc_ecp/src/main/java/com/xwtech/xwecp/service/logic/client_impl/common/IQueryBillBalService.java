package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY010021Result;

public interface IQueryBillBalService
{
	public QRY010021Result queryBillBal(String msisdn, String extQueryDateYm) throws LIException;

}