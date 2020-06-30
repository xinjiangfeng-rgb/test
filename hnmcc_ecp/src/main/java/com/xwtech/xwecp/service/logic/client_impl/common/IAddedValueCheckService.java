package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.DEL200604Result;

public interface IAddedValueCheckService {

    public DEL200604Result addedValueCheck(String SvcNum, String OfferId, String FiltMarket) throws LIException;
}
