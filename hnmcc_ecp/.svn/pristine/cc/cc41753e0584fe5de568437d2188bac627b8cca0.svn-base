package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQryMemberHandlingService;
import com.xwtech.xwecp.service.logic.pojo.QRY181230Result;

import java.util.HashMap;
import java.util.Map;

/**
 * 5.1.3.	成员处理情况查询
 */
public class QryMemberHandlingImpl implements IQryMemberHandlingService {

    /**
     * 根据外围渠道订单流水查询运营商订单处理情况
     * @param custOrderId   流水号
     * @param billId        手机号
     * @return
     */
    @Override
    public QRY181230Result qryMemberHandling(String custOrderId, String billId) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("__cmd", "QRY181230");
        mapParam.put("CUST_ORDER_ID",custOrderId);
        mapParam.put("BILL_ID",billId);


        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY181230Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }

        return null;
    }
}
