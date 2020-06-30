package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY040050Result;

public interface IQueryMonthlyTrafficService {
	public QRY040050Result queryMonthlyTraffic(String phoneNum, String billingCycle, String cycleEndDay, String cycleBeginDate, String cycleEndDate)throws LIException;
}
