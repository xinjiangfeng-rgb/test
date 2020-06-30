package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY040203Result;

/**
 * 判断是不是4G套餐接口(200814)
 * @author Administrator
 *
 */
public interface IFourgComboService {
	
	/**
	 * 
	 * @param svcNum
	 * @return
	 * @throws LIException
	 */
	public QRY040203Result check4G(String svcNum) throws LIException;
}
