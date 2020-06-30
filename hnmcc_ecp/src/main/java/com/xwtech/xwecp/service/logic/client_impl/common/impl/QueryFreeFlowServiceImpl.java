package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.client.BaseClientServiceImpl;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryFreeFlowService;
import com.xwtech.xwecp.service.logic.pojo.QRY170331Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 启东 on 2017/4/11.
 */
public class QueryFreeFlowServiceImpl extends BaseClientServiceImpl implements IQueryFreeFlowService {
    @Override
    public QRY170331Result queryFreeFlow(String svcNum) throws LIException {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("svcNum", svcNum);
        mapParam.put("__cmd", "QRY170331");
        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY170331Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }
        return null;
    }
}
