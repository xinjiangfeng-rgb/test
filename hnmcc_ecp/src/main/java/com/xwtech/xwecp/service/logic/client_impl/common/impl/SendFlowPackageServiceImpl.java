package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.ISendFlowPackageService;
import com.xwtech.xwecp.service.logic.pojo.DEL170811Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/29.
 */
@SuppressWarnings("ConstantConditions")
public class SendFlowPackageServiceImpl implements ISendFlowPackageService {
    @Override
    public DEL170811Result sendFlowPackage(String AuthNum, String ProdPrcId, String SvcNum, String PayPath) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("__cmd", "DEL170811");
        mapParam.put("AuthNum", AuthNum);
        mapParam.put("ProdPrcId", ProdPrcId);

        mapParam.put("SvcNum", SvcNum);
        mapParam.put("PayPath", PayPath);
        BaseServiceInvocationResult rs;
        try {

            rs = RemoteCaller.callRemote(mapParam);
            return (DEL170811Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }
        return null;
    }
}
