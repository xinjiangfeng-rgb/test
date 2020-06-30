package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.DEL190523Result;

public interface IUnsubscribeAddChangeBasicproductService {

    public DEL190523Result usubscribeAddChangeBasicproduct(String svcNum, String prodId, String offerOthers, String offerPloy) throws LIException;


}
