package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY180820Result;

public interface IQueryMainAndSubCardsInfoNewService {


    public QRY180820Result queryMainAndSubCardsInfoNew(String SvcNum, String IsAblity) throws LIException;

}
