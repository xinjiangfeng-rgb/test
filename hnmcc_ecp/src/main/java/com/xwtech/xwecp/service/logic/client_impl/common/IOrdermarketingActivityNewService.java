package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.DEL929206Result;

public interface IOrdermarketingActivityNewService {


    public DEL929206Result ordermarketingActivityNew(String flag, String payPath, String authNum, String passWd, String svcNum, String svcNumA, String prodPrcId, String zDOptCode , String regionflag, String channelSource) throws LIException;


}
