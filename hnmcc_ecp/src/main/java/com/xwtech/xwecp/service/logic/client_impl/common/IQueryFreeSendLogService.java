package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.pojo.QRY170414Result;

public interface IQueryFreeSendLogService {

	public QRY170414Result queryFreeSendLog(String phone,String time,String staffId);
	
}
