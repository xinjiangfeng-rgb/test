package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryPersonBusiness;
import com.xwtech.xwecp.service.logic.pojo.QRY912114Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 54344 on 2018/4/16.
 */
public class QueryPersonBusinessImpl implements IQueryPersonBusiness {

    @Override
    public QRY912114Result queryBusiness(String svnNum, String QryType) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("SvcNum", svnNum);
        mapParam.put("QryType", QryType);

        mapParam.put("__cmd", "QRY912114");
        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY912114Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }
        return null;
    }
}
