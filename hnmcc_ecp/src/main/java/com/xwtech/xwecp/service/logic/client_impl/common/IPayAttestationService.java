package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.PAY181114Result;

/**
 * 线上统一支付验签接口通用服务
 */
public interface IPayAttestationService {

	/**
	 *
	 * @param ActivityCode	交易代码，请填写U105_002
	 * @param BusiType		业务类型
	 * @param ClientIP		客户端IP
	 * @param CustomParam	商户自定义参数
	 * @param Gift			商户赠送金额(单位分)
	 * @param IDType		中国移动用户标识类型
	 * @param IDValue		中国移动用户号码
	 * @param OrderMoney	订单总金额(单位分)
	 * @param Payment		用户支付金额(单位分)
	 * @param PaymentType	支付方式
	 * @param ProductDesc	商品描述
	 * @param ProductID		商品编号
	 * @param ProductName	商品名称
	 * @param ProductType	商品类型
	 * @param ProductURL	商品展示URL
	 * @param ReturnURL		页面返回的URL
	 * @return
	 * @throws LIException
	 */
	public PAY181114Result payAttestation(String ActivityCode,
                                          String BusiType,
                                          String ClientIP,
                                          String CustomParam,
                                          String Gift,
                                          String IDType,
                                          String IDValue,
                                          String OrderMoney,
                                          String Payment,
                                          String PaymentType,
                                          String ProductDesc,
                                          String ProductID,
                                          String ProductName,
                                          String ProductType,
                                          String ProductURL,
                                          String ReturnURL)throws LIException;


}