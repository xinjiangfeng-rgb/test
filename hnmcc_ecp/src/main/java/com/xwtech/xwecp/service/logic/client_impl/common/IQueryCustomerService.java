package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY300012Result;

public interface IQueryCustomerService  {

    public QRY300012Result queryCustomer(Object publics,Object busiInfo) throws LIException;
}
