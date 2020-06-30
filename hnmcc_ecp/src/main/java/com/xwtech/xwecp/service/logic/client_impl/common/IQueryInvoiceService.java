package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.pojo.QRY200997Result;

public interface IQueryInvoiceService {

	public QRY200997Result queryInvoice(String billId,String queryMonth,String invoiceStyle,String printFlag);

}
