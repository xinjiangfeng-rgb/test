package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.ICancleCheckService;
import com.xwtech.xwecp.service.logic.pojo.QRY170614Result;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/3.
 */
public class CancleCheckServiceImpl implements ICancleCheckService {

    @Override
    public QRY170614Result cancleCheck(String BILL_ID, String OFFER_ID) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("BILL_ID", BILL_ID);
        mapParam.put("OFFER_ID", OFFER_ID);
        mapParam.put("__cmd", "QRY170614");
        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY170614Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }

        return null;
    }
}
