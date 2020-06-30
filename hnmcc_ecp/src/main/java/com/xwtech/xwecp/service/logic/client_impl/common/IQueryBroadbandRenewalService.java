package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY191119Result;

public interface IQueryBroadbandRenewalService {

    QRY191119Result queryBroadbandRenewal(String svnNum) throws LIException;

}
