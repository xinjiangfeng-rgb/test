package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY180716Result;

public interface IQueryVerificationCarryOutRuleService {

    public QRY180716Result queryVerificationCarryOutRule(String billId,String custName,String credNumber,String flag,String IS_ACTION) throws LIException;
}
