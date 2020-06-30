package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.PAY010001Result;

/**
 * 缴费接口(200033)
 * @author Administrator
 *
 */
public interface IPaymentService {
	
	/**
	 * 
	 * @param idValue 充值号码
	 * @param regionId 地市
	 * @param operationType 操作类型
	 * @param certificateType 缴费类型
	 * @param payPartLo 缴费金额
	 * @param payPartLt 赠送金额
	 * @param optCodeJF 缴费code
	 * @param optCodeZS 赠送code
	 * @param payPathJF 缴费途径
	 * @param payPathZS 赠送途径
	 * @param transIDHOJF 缴费流水
	 * @param transIDHOZS 赠送流水(这两个流水一样)
	 * @param peerDate 缴费时间(以支付宝扣费返回时间为准)
	 * @return
	 * @throws LIException
	 */
	public PAY010001Result payment(String idValue,String regionId,String operationType,String certificateType,String payPartLo,String payPartLt,
			String optCodeJF,String optCodeZS,String payPathJF,String payPathZS,String transIDHOJF,String transIDHOZS,String peerDate) throws LIException;
}
