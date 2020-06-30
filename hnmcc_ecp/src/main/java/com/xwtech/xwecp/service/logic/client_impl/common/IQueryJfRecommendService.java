package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY010097Result;
import com.xwtech.xwecp.service.logic.pojo.QRY060001Result;

public interface IQueryJfRecommendService {
	public QRY060001Result qryRecommend(String phoneNum,String cityCode,String eventPropertyParam) throws LIException;
}