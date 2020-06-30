package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryFreeResFlowService;
import com.xwtech.xwecp.service.logic.pojo.QRY170228Result;
import com.xwtech.xwecp.service.logic.pojo.QRY170414Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/22.
 */
public class QueryFreeResFlowServiceImpl implements IQueryFreeResFlowService {

    @Override
    public QRY170228Result queryFreeResFlow(String SvcNum, String queryCode) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("SvcNum", SvcNum);
        mapParam.put("queryCode", queryCode);
        mapParam.put("__cmd", "QRY170228");
        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY170228Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }

        return null;
    }
}
