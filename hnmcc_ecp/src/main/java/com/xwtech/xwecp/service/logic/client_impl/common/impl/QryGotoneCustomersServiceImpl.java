package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQryGotoneCustomersService;
import com.xwtech.xwecp.service.logic.pojo.QRY181222Result;

import java.util.HashMap;
import java.util.Map;

/**
 * 全球通客户查询接口
 *
 *
 */
public class QryGotoneCustomersServiceImpl implements IQryGotoneCustomersService {
    @Override
    public QRY181222Result qryGotoneCustomers(String billId) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("__cmd", "QRY181222");
        mapParam.put("billId",billId);

        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY181222Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }

        return null;
    }
}
