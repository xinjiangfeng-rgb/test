package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY191211Result;

public interface IQueryMainDeputyCardHistoryService {
    public QRY191211Result queryMainDeputyCardHistory(String svcNum, String month) throws LIException;
}
