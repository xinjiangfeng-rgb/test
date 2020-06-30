package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.DEL930048Result;

public interface IChangesBasicFeesTmService {



    public DEL930048Result changesBasicFeesTm(String svcNum, String instanceId, String mainProdPrcParaValue, String mainProdPrcCustParaType, String optrId, String Telephonist) throws LIException;



}
