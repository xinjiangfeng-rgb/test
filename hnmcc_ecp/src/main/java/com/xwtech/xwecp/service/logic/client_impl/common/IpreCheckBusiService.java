package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.DEL010021Result;

public interface IpreCheckBusiService
{
	public DEL010021Result preCheckBusi(String phoneNum, String id, String filtMarket,String filtResGoods) throws LIException;
}