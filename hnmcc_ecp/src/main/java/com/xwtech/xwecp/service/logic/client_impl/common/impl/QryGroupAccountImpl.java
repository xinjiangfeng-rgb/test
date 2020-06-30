package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQryGroupAccountService;
import com.xwtech.xwecp.service.logic.pojo.QRY181231Result;

import java.util.HashMap;
import java.util.Map;

/**
 * 集团帐户实时余额查询
 */
public class QryGroupAccountImpl implements IQryGroupAccountService {

    /**
     * 集团帐户实时余额查询
     * @param billId    集团计费号
     * @return
     */
    @Override
    public QRY181231Result qryGroupAccount( String billId) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("__cmd", "QRY181231");
        mapParam.put("BILL_ID",billId);


        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY181231Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }

        return null;
    }
}
