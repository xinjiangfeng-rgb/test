package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IBeforePrintInvoiceService;
import com.xwtech.xwecp.service.logic.pojo.QRY201000Result;

public class BeforePrintInvoiceServiceImpl implements IBeforePrintInvoiceService {

	@Override
	public QRY201000Result beforeprintInovice(String acctId, String acctName, String printNum,
			String billingCycleId, String invoiceContent, String ghfNsrmc, String ghfNsrsbh, String ghfDz,
			String ghfDhhm, String ghfQylx, String pushBillId, String pushEmail, String remark, String ghfYhzh,
			String optCode) {
		

		 Map<String, Object> mapParam = new HashMap<String, Object>();
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
		 	
		 	
	        mapParam.put("__cmd", "QRY201000");
	        BaseServiceInvocationResult rs;
	        try {
	            rs = RemoteCaller.callRemote(mapParam);
	            return (QRY201000Result) rs;
	        } catch (LIException e) {
	            e.printStackTrace();
	        }
	        
	        return null;
		
		
	}

}
