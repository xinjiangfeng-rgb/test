package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IActivityCancelService;
import com.xwtech.xwecp.service.logic.pojo.QRY170614Result;
import com.xwtech.xwecp.service.logic.pojo.QRY170716Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/17.
 */
public class ActivityCancelServiceIml implements IActivityCancelService {
    @Override
    public QRY170716Result activityCancel(String billid, String offerid) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("billId", billid);
        mapParam.put("offerId", offerid);
        mapParam.put("__cmd", "QRY170716");
        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY170716Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }

        return null;
    }
}
