package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY040200Result;

/**
 * 截止昨天24点的已用流量(920317)
 * @author Administrator
 *
 */
public interface IGprsTwentyFourService {
	
	/**
	 * @param svcNum
	 * @return
	 * @throws LIException
	 */
	public QRY040200Result queryGprs(String svcNum) throws LIException;
}
