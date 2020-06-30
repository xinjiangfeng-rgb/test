package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.DEL980013Result;

public interface IChangesAddedProductTcService {
    public DEL980013Result changesAddedProductTc(String svcNum, String operMode, String prodCustomParaType, String addProdPrcId, String prodPrcPara, String optrId, String telephonist) throws LIException;
}
