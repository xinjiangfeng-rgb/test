package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY180528Result;

public interface IQueryNewPointsChangeDetailsService {


    public QRY180528Result queryNewPointsChangeDetails(String svcNum) throws LIException;
}
