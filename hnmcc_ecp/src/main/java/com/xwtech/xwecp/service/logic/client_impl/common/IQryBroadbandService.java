package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.pojo.QRY201513Result;

/**
 * 光纤宽带到期时间查询接口
 * @author Administrator
 *
 */
public interface IQryBroadbandService {

	public QRY201513Result queryBroadband(String mobileNum)throws Exception;

}
