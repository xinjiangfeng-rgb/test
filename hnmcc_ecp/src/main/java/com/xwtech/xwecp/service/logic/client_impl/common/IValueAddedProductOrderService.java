package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.DEL930044Result;

public interface IValueAddedProductOrderService {

    public DEL930044Result valueAddedProductOrder(String SvcNum, String OperMode, String ProdCustomParaType, String productId, String ProdPrcPara, String OptrId,String Telephonist,String channelSource) throws LIException;
}
