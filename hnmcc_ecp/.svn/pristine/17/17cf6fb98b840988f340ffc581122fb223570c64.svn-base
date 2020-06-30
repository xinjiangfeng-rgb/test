package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryBillDetailsendSmsCodeService;
import com.xwtech.xwecp.service.logic.pojo.QRY170416Result;
import com.xwtech.xwecp.service.logic.pojo.QRY940130Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/24.
 */
public class QueryBillDetailsendSmsCodeServiceImpl implements IQueryBillDetailsendSmsCodeService {
    @Override
    public QRY940130Result sendSmsCode(String SVCNUM) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("SVCNUM", SVCNUM);
        mapParam.put("__cmd", "QRY940130");
        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY940130Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }

        return null;
    }
}
