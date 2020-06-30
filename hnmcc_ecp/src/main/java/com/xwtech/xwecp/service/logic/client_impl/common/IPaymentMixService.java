package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.PAY010002Result;

/**
 * 混合支付接口(400068)
 * @author Administrator
 *
 */
public interface IPaymentMixService {

	/**
	 * @param SvcNum 号码	(扣积分的号码)
	 * @param optSN	外部流水号  (25位，并且以日期开头)
	 * @param payedType	缴费类型	(0)
	 * @param certificateType	凭证类型	(0)
	 * @param certificateCode	凭证编号 (空字符串)
	 * @param optCode	业务编码	(GJFES)
	 * @param optSeq	业务标识 (先固定传2，账务是根据此字段和外部流水号做联合主键)
	 * @param prodPricId	营销活动编码	(传固定值：420002184985)
	 * @param expDate	账本有效期	(传空字符串)
	 * @param scrValue	积分		(扣减的积分)
	 * @param batchFlag	入缴费工单操作判断	(false：入缴费工单当前表)
	 * @param AuthrNum	被赠送预存的号码	
	 * @param remarks	日志
	 * @return
	 * @throws LIException
	 */
	public PAY010002Result paymentMix(String SvcNum,String optSN,String payedType,String certificateType,
			String certificateCode,String optCode,String optSeq,String prodPricId,String expDate,String scrValue,
			String batchFlag,String AuthrNum,String remarks)throws LIException;
}
