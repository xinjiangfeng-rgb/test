package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryintegraService;
import com.xwtech.xwecp.service.logic.pojo.QRY180525Result;

import java.util.HashMap;
import java.util.Map;

public class QueryintegraServiceImpl implements IQueryintegraService {
    @Override
    public QRY180525Result queryScore(String svcNum, String startDate, String endDate) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("__cmd", "QRY180525");
        mapParam.put("svcNum",svcNum);
        mapParam.put("startDate",startDate);
        mapParam.put("endDate",endDate);


        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY180525Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }

        return null;
    }
}
