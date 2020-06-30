package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.PAY181116Result;

/*
 *H5支付验签服务
 * */
public interface IMobileAppPaymentSignVerificationService  {
       PAY181116Result mobileAppPaymentSignVerification(
                                                        String orderMoney,
                                                        String payment,
                                                        String gift,
                                                        String merActivityID,
                                                        String paymentType,
                                                        String paymentLimit,
                                                        String productID,
                                                        String productName,
                                                        String productDesc,
                                                        String productURL,
                                                        String productType,
                                                        String returnURL,
                                                        String clientIP,
                                                        String customParam,
                                                        String buyerID,
                                                        String iDType,
                                                        String iDValue,
                                                        String defaultBank,
                                                        String authCode,
                                                        String reqChannel,
                                                        String busiType,
                                                        String requestFromUrl,
                                                        String logonID,
                                                        String contractCode,
                                                        String signValidityPeriod,
                                                        String oriReqDate,
                                                        String timeoutExpress,
                                                        String activityCode
                                                        ) throws LIException;
}
