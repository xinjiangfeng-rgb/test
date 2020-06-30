package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY040049Result;

public interface IQueryGPRSFluxsService {
	public QRY040049Result queryGPRSFluxs(String phoneNum,String queryMonth)throws LIException;
}
