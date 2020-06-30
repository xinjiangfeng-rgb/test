package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY170422Result;

public interface IQueryAccountNameByPhoneService {
    public QRY170422Result queryAccountNameByPhone(String SVCNUM) throws LIException;
}
