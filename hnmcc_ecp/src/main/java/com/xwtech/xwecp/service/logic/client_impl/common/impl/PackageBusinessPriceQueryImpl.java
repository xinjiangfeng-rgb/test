package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IPackageBusinessPriceQuery;
import com.xwtech.xwecp.service.logic.pojo.QRY929406Result;

import java.util.HashMap;
import java.util.Map;

public class PackageBusinessPriceQueryImpl implements IPackageBusinessPriceQuery {
    @Override
    public QRY929406Result query(String mobile) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("__cmd", "QRY929406");
        mapParam.put("svcNum", mobile);

        BaseServiceInvocationResult rs;
        try {

            rs = RemoteCaller.callRemote(mapParam);
            return (QRY929406Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }
        return null;

    }
}
