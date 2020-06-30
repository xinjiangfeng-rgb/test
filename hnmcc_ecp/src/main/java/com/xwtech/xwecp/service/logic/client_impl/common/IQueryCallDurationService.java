package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY040102Result;

public interface IQueryCallDurationService
{
	public QRY040102Result queryCallDuration(String phoneNum,String qryMonth) throws LIException;

}