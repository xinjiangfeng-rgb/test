package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY190108Result;

public interface IQueryBroadTvByPhoneOrBasicService {

    public QRY190108Result queryBroadTvByPhoneOrBasic(String billId, String offerId) throws LIException;
}
