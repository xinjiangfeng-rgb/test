package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY920013Result;

public interface IQueryCurrentMonthFeeService {

	public QRY920013Result currentMonthFee(String phoneNum) throws LIException;
}
