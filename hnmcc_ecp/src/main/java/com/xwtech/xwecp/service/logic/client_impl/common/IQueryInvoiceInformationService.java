package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY201997Result;

public interface IQueryInvoiceInformationService    {

    public QRY201997Result queryInvoiceInformation(String billId, String queryMonth, String invoiceStyle, String printFlag) throws LIException;
}
