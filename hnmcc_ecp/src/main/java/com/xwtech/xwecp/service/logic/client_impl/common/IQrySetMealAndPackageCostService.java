package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY181025Result;

/**
 * 4.1.1.	查询用户套餐和流量包合计费用
 * 查询用户套餐和流量包合计费用，支持指定产品不参与计算。
 */
public interface IQrySetMealAndPackageCostService
{
	/**
	 *
	 * @param svcNum
	 * @param filterOfferIds   过滤产品
	 * @return
	 * @throws LIException
	 */
	public QRY181025Result qrySetMealAndPackageCost(String svcNum, String filterOfferIds) throws LIException;


}