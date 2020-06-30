package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY161201Result;
import com.xwtech.xwecp.service.logic.pojo.QRY161202Result;

public interface IQueryUserFlowDayService {
	public QRY161202Result queryUserFlowDay(String phoneNum,String startDay,String endDay) throws LIException;
}