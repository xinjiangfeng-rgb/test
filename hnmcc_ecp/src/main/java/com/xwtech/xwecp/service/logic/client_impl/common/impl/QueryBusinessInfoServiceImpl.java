package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryBusinessInfoService;
import com.xwtech.xwecp.service.logic.pojo.MemberDeal;
import com.xwtech.xwecp.service.logic.pojo.QRY201704Result;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class QueryBusinessInfoServiceImpl implements IQueryBusinessInfoService{

	@Override
	public QRY201704Result queryBusinessInfo(String SvcNum, String OPT_CODE, String JTCP) {
		 Map<String, Object> mapParam = new HashMap<String, Object>();
	        mapParam.put("SvcNum", SvcNum);
	        mapParam.put("OPT_CODE", OPT_CODE);
	        mapParam.put("__cmd", "QRY201704");
	        mapParam.put("JTCP", JTCP);
	        BaseServiceInvocationResult rs;
	        try {
	            rs = RemoteCaller.callRemote(mapParam);
				if (rs != null) {
					QRY201704Result rs2 = (QRY201704Result) rs;
					Iterator<MemberDeal> it = rs2.getMemberDeal().iterator();
					while (it.hasNext()) {
						MemberDeal d = it.next();
						if ("".equals(d.getSoId()) || "".equals(d.getKeyProdName())) {
							it.remove();
						}
					}
				}
				return (QRY201704Result) rs;
	        } catch (LIException e) {

	            e.printStackTrace();
	        }
	        return null;
	}

}
