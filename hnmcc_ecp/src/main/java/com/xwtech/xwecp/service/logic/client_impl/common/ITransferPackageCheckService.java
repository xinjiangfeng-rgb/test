package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY219416Result;

public interface ITransferPackageCheckService {

    public QRY219416Result transferPackageCheck(String svcNum, String prodId) throws LIException;

}
