package com.xwtech.xwecp.service.logic.client_impl.common;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY020025Result;

public class IQueryhdhslServiceImpl implements IQueryhdhslService {

	@Override
	public QRY020025Result queryhdhsl(String billId, String followId, String opr, String servId, String seq) {
		 Map<String, Object> mapParam = new HashMap<String, Object>();
		 mapParam.put("__cmd", "QRY020025");
		 
		 mapParam.put("BILL_ID",billId );
		 mapParam.put("FOLLOW_ID", followId);
		 mapParam.put("OPR",opr );
		 mapParam.put("SERV_ID", servId);
		 mapParam.put("SEQ", seq);
	        BaseServiceInvocationResult rs;
	        try {
	            rs = RemoteCaller.callRemote(mapParam);
	            return (QRY020025Result) rs;
	        } catch (LIException e) { 
	            e.printStackTrace();
	        }
	        
	        return null;
	}

}
