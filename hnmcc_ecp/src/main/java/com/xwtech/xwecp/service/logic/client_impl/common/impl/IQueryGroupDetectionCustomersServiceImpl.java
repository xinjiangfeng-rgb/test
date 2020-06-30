package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryGroupDetectionCustomersService;
import com.xwtech.xwecp.service.logic.pojo.QRY010004Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 54344 on 2018/3/3.
 */
public class IQueryGroupDetectionCustomersServiceImpl implements IQueryGroupDetectionCustomersService {

    @Override
    public QRY010004Result queryGroupDetectionCustomers(String phone) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("phone", phone);
        mapParam.put("__cmd", "QRY010004");
        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY010004Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }
        return null;
    }
}
