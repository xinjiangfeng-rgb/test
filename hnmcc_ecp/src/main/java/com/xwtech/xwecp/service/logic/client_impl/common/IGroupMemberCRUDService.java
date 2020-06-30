package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.DEL181228Result;

/**
 * 集团成员的添加、变更和删除
 */
public interface IGroupMemberCRUDService {


    /**
     *
     * @param gBillId       集团计费号
     * @param flag          操作标识
     * @param billId        成员号码
     * @param validMonth    赠送流量有效期
     * @param validType     生效方式
     * @param memSrvpkg     成员资费包
     * @param custOrderId   订单流水
     * @param smsTemplate   短息模板
     * @return
     * @throws LIException
     */
    DEL181228Result delGroupMemberCRUD(String gBillId,
                                       String flag,
                                       String billId,
                                       String validMonth,
                                       String validType,
                                       String memSrvpkg,
                                       String custOrderId,
                                       String smsTemplate)throws LIException;

}
