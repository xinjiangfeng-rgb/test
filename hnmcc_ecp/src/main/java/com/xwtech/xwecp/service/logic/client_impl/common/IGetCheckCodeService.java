package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY040094Result;

public interface IGetCheckCodeService
{
	public QRY040094Result getCheckCode(String phoneNum,String content) throws LIException;

}