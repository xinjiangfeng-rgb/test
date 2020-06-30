package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryIntegralExpirationService;
import com.xwtech.xwecp.service.logic.pojo.QRY180526Result;

import java.util.HashMap;
import java.util.Map;

public class QueryIntegralExpirationServiceImpl implements IQueryIntegralExpirationService {
    @Override
    public QRY180526Result queryIntegralExpiration(String aBillId) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("__cmd", "QRY180526");
        mapParam.put("aBillId",aBillId);
        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY180526Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
            return null;
        }
    }
}
