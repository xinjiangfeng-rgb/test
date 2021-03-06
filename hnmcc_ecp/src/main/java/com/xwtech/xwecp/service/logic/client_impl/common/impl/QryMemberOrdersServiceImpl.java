package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQryMemberOrdersService;
import com.xwtech.xwecp.service.logic.pojo.QRY181229Result;

import java.util.HashMap;
import java.util.Map;

/**
 * 集团成员已订购流量查询
 */
public class QryMemberOrdersServiceImpl implements IQryMemberOrdersService {
    @Override
    public QRY181229Result qryMemberOrders(String billId, String flag) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("__cmd", "QRY181229");
        mapParam.put("BILL_ID",billId);
        mapParam.put("FLAG",flag);
        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY181229Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }

        return null;
    }
}
