package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY190424Result;

public interface IQueryCurrentPriceByPhoneService  {


    public QRY190424Result queryCurrentPriceByPhone(String billId) throws LIException;
}
