package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryPackageRemainService;
import com.xwtech.xwecp.service.logic.pojo.QRY180410Result;

import java.util.HashMap;
import java.util.Map;

public class QueryPackageRemainServiceImpl implements IQueryPackageRemainService {
    @Override
    public QRY180410Result queryPackageRemain(String billId) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("billId", billId);
        mapParam.put("__cmd", "QRY180410");
        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY180410Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }

        return null;
    }
}
