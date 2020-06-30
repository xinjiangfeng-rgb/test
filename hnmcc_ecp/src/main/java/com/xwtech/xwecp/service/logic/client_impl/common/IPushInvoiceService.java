package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.pojo.QRY201001Result;

public interface IPushInvoiceService {

	public QRY201001Result pushInvoice(String INVOICE_DS_ID,String billingCycleId,String BILL_ID,String PUSH_BILL_ID,String PUSH_EMAIL);

}
