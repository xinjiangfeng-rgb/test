package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryLineUpstateService;
import com.xwtech.xwecp.service.logic.pojo.QRY180120Result;

import java.util.HashMap;
import java.util.Map;

public class QueryLineUpstateServiceImpl implements IQueryLineUpstateService {
    @Override
    public QRY180120Result queryLineUpState(String svcNum, String orgId, String isVip, String isAppointment) {

        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("svcNum", svcNum);
        mapParam.put("orgId", orgId);
        mapParam.put("isVip", isVip);
        mapParam.put("isAppointment", isAppointment);

        mapParam.put("__cmd", "QRY180120");
        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY180120Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }
        return null;
    }
}
