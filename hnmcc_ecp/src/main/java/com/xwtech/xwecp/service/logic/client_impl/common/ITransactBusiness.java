package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.pojo.QRY930018Result;

/**
 * Created by 54344 on 2018/2/5.
 */
public interface ITransactBusiness {

    public QRY930018Result transactBusiness(String phoneNum, String bossCode, String OperMode, String Optrld);
}
