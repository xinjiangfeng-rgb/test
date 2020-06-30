package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryVoiceRealAmoutService2;
import com.xwtech.xwecp.service.logic.pojo.QRY170612Result;
import com.xwtech.xwecp.service.logic.pojo.QRY170806Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/6.
 */
public class QueryVoiceRealAmoutService2Impl implements IQueryVoiceRealAmoutService2 {
    @Override
    public QRY170806Result queryVoiceAmout(String SvcNum) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("__cmd", "QRY170806");
        mapParam.put("phoneNum", SvcNum);
        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY170806Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }
        return null;
    }
}
