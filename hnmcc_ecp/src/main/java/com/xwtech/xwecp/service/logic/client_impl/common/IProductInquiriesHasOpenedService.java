package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY912115Result;

/**
 * Created by 54344 on 2018/4/13.
 */
public interface IProductInquiriesHasOpenedService {
    QRY912115Result productInquiriesHasOpened(String svnNum, String QryType) throws LIException;

}
