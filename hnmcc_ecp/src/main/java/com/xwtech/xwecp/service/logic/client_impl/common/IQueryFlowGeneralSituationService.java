package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY161204Result;

public interface IQueryFlowGeneralSituationService {
	public QRY161204Result queryFlowGeneralSituation(String phoneNum) throws LIException;
}