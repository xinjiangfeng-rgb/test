package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.RemoteCaller;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryBillDetailService2;
import com.xwtech.xwecp.service.logic.pojo.QRY010002Result;
import com.xwtech.xwecp.service.logic.pojo.QRY170416Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/24.
 */
public class QueryBillDetailService2impl implements IQueryBillDetailService2 {


    @Override
    public QRY010002Result queryBillDetail(String SVCNUM, String START_DATE, String END_DATE,String OPP_NUMBER, String RANDOM_CODE,String queryType,String filterParam) {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        mapParam.put("SVCNUM", SVCNUM);
        mapParam.put("START_DATE", START_DATE);
        mapParam.put("__cmd", "QRY010002");
        mapParam.put("END_DATE", END_DATE);
        mapParam.put("OPP_NUMBER", OPP_NUMBER);
        mapParam.put("RANDOM_CODE", RANDOM_CODE);
        mapParam.put("queryType", queryType);
        mapParam.put("filterParam", filterParam);

        BaseServiceInvocationResult rs;
        try {
            rs = RemoteCaller.callRemote(mapParam);
            return (QRY010002Result) rs;
        } catch (LIException e) {
            e.printStackTrace();
        }

        return null;
    }
}
