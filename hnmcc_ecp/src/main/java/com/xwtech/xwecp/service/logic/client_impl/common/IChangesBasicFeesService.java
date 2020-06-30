package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.DEL930049Result;

public interface IChangesBasicFeesService {



    public DEL930049Result changeBaseFee(String svcNum, String instanceId, String mainProdPrcParaValue, String mainProdPrcCustParaType, String optrId, String Telephonist) throws LIException;



}
