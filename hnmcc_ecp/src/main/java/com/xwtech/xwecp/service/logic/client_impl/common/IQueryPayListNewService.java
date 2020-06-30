package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY040205Result;

public interface IQueryPayListNewService
{
	public QRY040205Result queryPayHistory(String phoneNum, String sDate, String eDate) throws LIException;

}