package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY040202Result;

public interface IQryVisibleToHandleService {
	public QRY040202Result qryVisibleToHandle(String svcNum,String offerId,String filtMarket,String filtResGoods) throws LIException;
}
