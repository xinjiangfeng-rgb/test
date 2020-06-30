package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.PAY181119Result;

/*
 *收银台支付验签接口服务
 * */
public interface ICheckStandPaymentSignService {
       PAY181119Result checkStandPaymentSign(
               String orderMoney,
               String payment,
               String gift,
               String merActivityID,
               String paymentLimit,
               String productID,
               String productName,
               String productDesc,
               String productURL,
               String productType,
               String returnURL,
               String customParam,
               String iDType,
               String iDValue,
               String reqChannel,
               String busiType,
               String timeoutExpress,
               String activityCode
       ) throws LIException;
}
