package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IPrintInvoiceService;
import com.xwtech.xwecp.service.logic.pojo.QRY200999Result;

public class PrintInvoiceServiceImpl implements IPrintInvoiceService {

	@Override
	public QRY200999Result printInvoice(String paymentId, String acctId, String acctName, String printNum,
			String billingCycleId, String invoiceContent, String ghfNsrmc, String ghfNsrsbh, String ghfDz,
			String ghfDhhm, String ghfQylx, String pushBillId, String pushEmail, String remark, String ghfYhzh,
			String optCode) {
		
		
		 Map<String, Object> mapParam = new HashMap<String, Object>();
		 	mapParam.put("paymentId", paymentId);
		 	mapParam.put("acctId", acctId);
		 	mapParam.put("acctName", acctName);
		 	mapParam.put("printNum", printNum);
		 	mapParam.put("billingCycleId", billingCycleId);
		 	mapParam.put("invoiceContent", invoiceContent);
		 	mapParam.put("ghfNsrmc", ghfNsrmc);
		 	mapParam.put("ghfNsrsbh", ghfNsrsbh);
		 	mapParam.put("ghfDz", ghfDz);
		 	mapParam.put("ghfDhhm", ghfDhhm);
		 	mapParam.put("ghfQylx", ghfQylx);
		 	mapParam.put("pushBillId", pushBillId);
		 	mapParam.put("pushEmail", pushEmail);
		 	mapParam.put("remark", remark);
		 	mapParam.put("ghfYhzh", ghfYhzh);
		 	mapParam.put("optCode", optCode);
		 	
		 	
	        mapParam.put("__cmd", "QRY200999");
	        BaseServiceInvocationResult rs;
	        try {
	            rs = RemoteCaller.callRemote(mapParam);
	            return (QRY200999Result) rs;
	        } catch (LIException e) {
	            e.printStackTrace();
	        }
	        
	        return null;
	}


	
}
