package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryInvoiceService;
import com.xwtech.xwecp.service.logic.pojo.QRY200997Result;

public class QueryInvoiceServiceImpl implements IQueryInvoiceService {

	@Override
	public QRY200997Result queryInvoice(String billId, String queryMonth, String invoiceStyle, String printFlag) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("billId", billId);
		mapParam.put("queryMonth", queryMonth);
		mapParam.put("__cmd", "QRY200997");
		mapParam.put("invoiceStyle",invoiceStyle);
		mapParam.put("printFlag",printFlag);

		BaseServiceInvocationResult rs;
		try {
			rs = RemoteCaller.callRemote(mapParam);
			return (QRY200997Result) rs;
		} catch (LIException e) {
			e.printStackTrace();
		}

		return null;	
	}

}
