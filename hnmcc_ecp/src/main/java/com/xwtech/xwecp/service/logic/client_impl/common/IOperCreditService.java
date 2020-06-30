package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.DEL010025Result;
import com.xwtech.xwecp.service.logic.pojo.QRY040094Result;

public interface IOperCreditService
{
	public DEL010025Result operCredit(String phoneNum,String operType) throws LIException;

}