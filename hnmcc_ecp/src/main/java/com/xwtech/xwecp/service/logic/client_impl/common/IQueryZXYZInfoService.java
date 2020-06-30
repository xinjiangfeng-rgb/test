package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY020022Result;

public interface IQueryZXYZInfoService
{
    public QRY020022Result queryZXYZInfo(String phoneNum)
        throws LIException;
}
