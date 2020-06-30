package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryVoiceAmoutService;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryVoiceRealAmoutService;
import com.xwtech.xwecp.service.logic.pojo.QRY170611Result;
import com.xwtech.xwecp.service.logic.pojo.QRY170612Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 123 on 2017/6/21.
 */
public class QueryVoiceRealAmoutServiceImpl implements IQueryVoiceRealAmoutService {
    @Override
    public QRY170611Result queryVoiceAmout(String BillId) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("__cmd", "QRY170611");
        mapParam.put("BillId", BillId);
        mapParam.put("phoneNum", BillId);//此处是集团探测号码字段，170611对于探测号码调用170806
        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY170611Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }
        return null;
    }
}
