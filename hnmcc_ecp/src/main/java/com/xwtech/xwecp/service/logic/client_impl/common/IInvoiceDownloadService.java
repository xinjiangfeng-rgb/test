package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.pojo.QRY200998Result;

public interface IInvoiceDownloadService {

	public QRY200998Result invoiceDownload(String invoiceDsId,String queryMonth,String billId,String pdf2Image);
	
}
