package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.pojo.QRY010002Result;

/**
 * Created by Administrator on 2017/7/24.
 */
public interface IQueryBillDetailService2 {
    public QRY010002Result queryBillDetail(String SVCNUM, String START_DATE, String END_DATE,String OPP_NUMBER , String RANDOM_CODE,String queryType,String filterParam);

}
