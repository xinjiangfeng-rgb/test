package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IOpenedProductsQueryService;
import com.xwtech.xwecp.service.logic.pojo.QRY912117Result;

import java.util.HashMap;
import java.util.Map;

public class OpenedProductsQueryServiceImpl implements IOpenedProductsQueryService {

    @Override
    public QRY912117Result openedProductsQuery(String svnNum, String QryType) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("SvcNum", svnNum);
        mapParam.put("QryType", QryType);
        mapParam.put("__cmd", "QRY912117");
        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY912117Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }
        return null;
    }
}