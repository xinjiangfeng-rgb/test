package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.client.BaseClientServiceImpl;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryEffectType;
import com.xwtech.xwecp.service.logic.pojo.DEL010032Result;
import com.xwtech.xwecp.service.RemoteCaller;

public class QueryEffectTypeImpl extends BaseClientServiceImpl implements IQueryEffectType {

	public static Map<String, String> OFFER_TYPE_MAP = new HashMap<>();

	static {
		OFFER_TYPE_MAP.put("ZZCP", "OTHER");
		OFFER_TYPE_MAP.put("MWCP", "OTHER");
		OFFER_TYPE_MAP.put("FWB", "OTHER");
		OFFER_TYPE_MAP.put("JBFWB", "BASE");
	}

	@Override
	public DEL010032Result queryEffect(String phone, String offerType, String offerId) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("phoneNum", phone);
		mapParam.put("offerType", OFFER_TYPE_MAP.get(offerType));

		mapParam.put("offerId", offerId);
		mapParam.put("__cmd", "DEL010032");

		BaseServiceInvocationResult rs;
		try {
			rs = RemoteCaller.callRemote(mapParam);
			return (DEL010032Result) rs;
		} catch (LIException e) {
			e.printStackTrace();
		}
		return null;
	}

}
