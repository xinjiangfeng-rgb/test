package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY040097Result;

public interface IQueryMGroupInfoService
{
	public QRY040097Result queryMGroupInfo(String phoneNum) throws LIException;

}