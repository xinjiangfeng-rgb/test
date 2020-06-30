package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY180717Result;

public interface IQueryTransfernetCarryUserService {


    public QRY180717Result queryTransfernetCarryUser(String billId) throws LIException;
}
