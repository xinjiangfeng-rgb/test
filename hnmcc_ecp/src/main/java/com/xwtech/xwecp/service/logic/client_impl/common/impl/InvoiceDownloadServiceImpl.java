package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IInvoiceDownloadService;
import com.xwtech.xwecp.service.logic.pojo.QRY200998Result;

public class InvoiceDownloadServiceImpl implements IInvoiceDownloadService {

	@Override
	public QRY200998Result invoiceDownload(String invoiceDsId, String queryMonth, String billId, String pdf2Image) {
		 Map<String, Object> mapParam = new HashMap<String, Object>();
	        mapParam.put("invoiceDsId", invoiceDsId);
	        mapParam.put("queryMonth", queryMonth);
	        mapParam.put("billId", billId);
	        mapParam.put("pdf2Image", pdf2Image);
	        mapParam.put("__cmd", "QRY200998");
	        BaseServiceInvocationResult rs;
	        try {
	            rs = RemoteCaller.callRemote(mapParam);
	            return (QRY200998Result) rs;
	        } catch (LIException e) {
	            e.printStackTrace();
	        }
	        
	        return null;
	}

}
