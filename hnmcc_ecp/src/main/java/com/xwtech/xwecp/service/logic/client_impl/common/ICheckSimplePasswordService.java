package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY040105Result;

public interface ICheckSimplePasswordService
{
	public QRY040105Result checkSimplePassword(String phoneNum, String password) throws LIException;

}