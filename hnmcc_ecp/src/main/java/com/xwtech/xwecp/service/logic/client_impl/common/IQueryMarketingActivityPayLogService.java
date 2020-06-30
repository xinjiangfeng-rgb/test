package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.pojo.QRY170415Result;

public interface IQueryMarketingActivityPayLogService {

	public QRY170415Result queryMarketingActivityPayLog(String phone,String time,String staffId);
}
