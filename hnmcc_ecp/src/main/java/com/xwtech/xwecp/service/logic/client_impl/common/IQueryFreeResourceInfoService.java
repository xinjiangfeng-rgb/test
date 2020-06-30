package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY020021Result;

public interface IQueryFreeResourceInfoService
{
    public QRY020021Result queryFreeResourceInfo(String phoneNum) throws LIException;
}
