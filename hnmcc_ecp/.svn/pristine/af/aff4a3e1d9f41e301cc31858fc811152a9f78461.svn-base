package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.ITransactBusiness;
import com.xwtech.xwecp.service.logic.pojo.QRY930018Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 54344 on 2018/2/5.
 */
public class TransactBusinessImpl implements ITransactBusiness {

    @Override
    public QRY930018Result transactBusiness(String phoneNum, String bossCode, String OperMode, String Optrld) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("__cmd", "QRY930018");
        mapParam.put("phoneNum", phoneNum);
        mapParam.put("bossCode", bossCode);

        mapParam.put("OperMode", OperMode);
        mapParam.put("Optrld", Optrld);
        BaseServiceInvocationResult rs;
        try {

            rs = RemoteCaller.callRemote(mapParam);
            return (QRY930018Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }
        return null;
    }
}
