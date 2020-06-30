package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.DEL010023Result;
/**
 * 流量赠送接口
 * @author Administrator
 *
 */
public interface IPresentActivityBusiService
{
	public DEL010023Result presentActivityBusi(String phoneNumA,String phoneNum, String flag, String prodPrcId,String paypath) throws LIException;
}