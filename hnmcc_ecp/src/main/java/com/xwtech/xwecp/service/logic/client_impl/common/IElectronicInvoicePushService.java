package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY202001Result;

public interface IElectronicInvoicePushService  {

    public QRY202001Result electronicInvoicePush(String invoiceDsId, String billingCycleId, String billId, String pushBilldId, String  pushEmail) throws LIException;
}
