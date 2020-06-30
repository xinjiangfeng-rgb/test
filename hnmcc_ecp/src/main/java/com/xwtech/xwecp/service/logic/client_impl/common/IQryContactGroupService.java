package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY040109Result;
/**
 * 集团联系人查询
 * @author Administrator
 *
 */
public interface IQryContactGroupService
{
	public QRY040109Result qryContactGroup(String phoneNum) throws LIException;
}