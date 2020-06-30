package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY931931Result;

public interface IQueryUserMailBoxPushService
{
	public QRY931931Result queryUserMailBoxPush(String keyNum, String qryType, String billingCycleId) throws LIException;

}