package com.xwtech.xwecp.service.logic.client_impl.common.impl;


import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryLineOrgNumService;
import com.xwtech.xwecp.service.logic.pojo.QRY180411Result;

import java.util.HashMap;
import java.util.Map;

public class QueryLineOrgNumServiceImpl implements IQueryLineOrgNumService {
    @Override
    public QRY180411Result queryLineOrgNum(String orgId, String isVip) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("orgId", orgId);
        mapParam.put("isVip", isVip);

        mapParam.put("__cmd", "QRY180411");
        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY180411Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }
        return null;

    }
}
