package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY199412Result;

public interface ISearchUserUnlimitSpeedLimitedService {


    public QRY199412Result searchUserUnlimitSpeedLimited(String phoneNum) throws LIException;


}
