package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY190425Result;

public interface IQueryCurrentPriceNewByPhoneService {


    public QRY190425Result queryCurrentPriceNewByPhone(String billId) throws LIException;
}
