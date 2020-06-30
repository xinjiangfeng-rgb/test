package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY241038Result;

public interface IQueryDetailedBillInformationService {

    public QRY241038Result queryDetailedBillInformation(String busiCode,String type,String acount,String channelId,String provinceId,String regionId) throws LIException;
}
