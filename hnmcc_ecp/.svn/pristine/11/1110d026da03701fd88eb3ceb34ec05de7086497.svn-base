package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.client.BaseClientServiceImpl;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.ITransferPackageCheckService;
import com.xwtech.xwecp.service.logic.pojo.QRY219416Result;

import java.util.HashMap;
import java.util.Map;

public class TransferPackageCheckServiceImpl extends BaseClientServiceImpl implements ITransferPackageCheckService {
    @Override
    public QRY219416Result transferPackageCheck(String svcNum, String prodId) throws LIException {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("SvcNum", svcNum);
        mapParam.put("ProdId", prodId);
        mapParam.put("__cmd", "QRY219416");
        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY219416Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
            return null;
        }

    }
}
