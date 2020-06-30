package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IPushInvoiceService;
import com.xwtech.xwecp.service.logic.pojo.QRY201001Result;

public class PushInvoiceServiceImpl implements IPushInvoiceService {

	@Override
	public QRY201001Result pushInvoice(String INVOICE_DS_ID, String billingCycleId, String BILL_ID, String PUSH_BILL_ID,
			String PUSH_EMAIL) {
		
		 Map<String, Object> mapParam = new HashMap<String, Object>();
	        mapParam.put("INVOICE_DS_ID", INVOICE_DS_ID);
	        mapParam.put("billingCycleId", billingCycleId);
	        mapParam.put("BILL_ID", BILL_ID);
	        mapParam.put("PUSH_BILL_ID", PUSH_BILL_ID);
	        mapParam.put("__cmd", "QRY201001");
	        BaseServiceInvocationResult rs;
	        try {
	            rs = RemoteCaller.callRemote(mapParam);
	            return (QRY201001Result) rs;
	        } catch (LIException e) {
	            e.printStackTrace();
	        }
	        
	        return null;
	}
	

}
