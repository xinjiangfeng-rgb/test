package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY420033Result;

public interface ISubscribedBusinesService {

/*
*  billId   手机号码
*  billingCycleId   账期
*  {"billId":"13938502195","billingCycleId":"202003"}
* */
    public QRY420033Result SubscribedBusines(String billId, String billingCycleId) throws LIException;
}
