package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY300011Result;

public interface IQueryClaimForEquityService {


    QRY300011Result queryClaimForEquity(Object publics,Object busiInfo) throws LIException;
}
