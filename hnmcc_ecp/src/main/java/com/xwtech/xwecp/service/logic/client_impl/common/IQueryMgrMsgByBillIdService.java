package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY912116Result;

public interface IQueryMgrMsgByBillIdService {

    public QRY912116Result queryMgrMsgByBillId(String billId) throws LIException;

}
