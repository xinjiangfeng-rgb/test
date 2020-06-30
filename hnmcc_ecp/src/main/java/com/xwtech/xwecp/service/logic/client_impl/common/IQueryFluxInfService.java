package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY040111Result;

public interface IQueryFluxInfService
{
	public QRY040111Result queryFluxInf(String phoneNum) throws LIException;

}