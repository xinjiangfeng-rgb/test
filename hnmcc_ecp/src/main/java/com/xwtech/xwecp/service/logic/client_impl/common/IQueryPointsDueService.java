package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY180527Result;

public interface IQueryPointsDueService {


    public QRY180527Result queryPointsDue(String svcNum) throws LIException;
}
