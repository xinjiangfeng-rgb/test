package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.DEL930019Result;

/**
 * Created by 54344 on 2018/2/5.
 */
public interface IAddedProductSubscriptionService {

    public DEL930019Result addedProductSubscription(String svcNum, String addProdPrcId, String operMode, String optrld, String telephonist, String effDate, String expDate) throws LIException;
}
