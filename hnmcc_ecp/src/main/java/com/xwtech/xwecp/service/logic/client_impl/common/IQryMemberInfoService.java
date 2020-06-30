package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY180706Result;

/**
 * 移动之家2018成员信息查询接口
 */
public interface IQryMemberInfoService {


    /**
     *
     * @param billId    成员号码
     * @return
     * @throws LIException
     */
    QRY180706Result qryMemberInfo(String billId)throws LIException;

}
