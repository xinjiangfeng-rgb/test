package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.pojo.QRY912114Result;

/**
 * Created by 54344 on 2018/4/13.
 */
public interface IQueryPersonBusiness {
    QRY912114Result queryBusiness(String svnNum, String QryType);

}
