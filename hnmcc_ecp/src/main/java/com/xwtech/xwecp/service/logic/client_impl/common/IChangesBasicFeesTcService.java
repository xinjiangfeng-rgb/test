package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.DEL930047Result;

public interface IChangesBasicFeesTcService {



    public DEL930047Result changesBasicFeesTc(String svcNum, String instanceId, String mainProdPrcParaValue, String mainProdPrcCustParaType, String optrId, String Telephonist) throws LIException;



}
