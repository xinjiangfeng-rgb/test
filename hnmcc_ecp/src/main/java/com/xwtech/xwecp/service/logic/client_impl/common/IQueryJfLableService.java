package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY060002Result;

public interface IQueryJfLableService {
	public QRY060002Result qryLables(String phoneNum, String cityCode, String platForm) throws LIException;
}