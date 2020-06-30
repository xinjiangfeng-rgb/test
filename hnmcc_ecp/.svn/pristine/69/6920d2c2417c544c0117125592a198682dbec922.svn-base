package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IDetailsInvoiceService;
import com.xwtech.xwecp.service.logic.pojo.QRY201002Result;

public class DetailsInvoiceServiceImpl  implements IDetailsInvoiceService{

	@Override
	public QRY201002Result detailsInvoice(String invoiceDsId, String queryMonth, String billId) {

		 Map<String, Object> mapParam = new HashMap<String, Object>();
	        mapParam.put("invoiceDsId", invoiceDsId);
	        mapParam.put("queryMonth", queryMonth);
	        mapParam.put("billId", billId);
	        mapParam.put("__cmd", "QRY201002");
	        BaseServiceInvocationResult rs;
	        try {
	            rs = RemoteCaller.callRemote(mapParam);
	            return (QRY201002Result) rs;
	        } catch (LIException e) {
	            e.printStackTrace();
	        }
	        
	        return null;
	}

}
