package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.DEL170108Result;

public interface IMonthlyInvoiceOrderService {

    public DEL170108Result monthlyInvoiceOrder(String SvcNum, String opType, String pushType, String pushMail,String pushTime,String pushWay) throws LIException;
}
