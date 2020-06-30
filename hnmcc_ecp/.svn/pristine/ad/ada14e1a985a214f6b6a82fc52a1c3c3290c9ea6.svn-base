package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.ILineUpService;
import com.xwtech.xwecp.service.logic.pojo.QRY180119Result;

import java.util.HashMap;
import java.util.Map;

public class LineUpServiceImpl implements ILineUpService {
    @Override
    public QRY180119Result LineUp(String svcNum, String orgId, String isAppointment, String isVip, String startTime, String endTime) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("svcNum", svcNum);
        mapParam.put("orgId", orgId);
        mapParam.put("isAppointment", isAppointment);
        mapParam.put("isVip", isVip);

        mapParam.put("startTime", startTime);
        mapParam.put("endTime", endTime);

        mapParam.put("__cmd", "QRY180119");
        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY180119Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }
        return null;
    }
}
