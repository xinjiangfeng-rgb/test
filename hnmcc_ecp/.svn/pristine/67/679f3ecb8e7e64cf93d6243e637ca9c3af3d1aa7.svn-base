package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.QRY181107Result;

/**
 * 查询某用户号码的画像信息
 */
public interface IQryCustomerPortraitService {

    /**
     *
     * @param servNo  主键，可以指定任一个属性为 主键。例如：客户手机号码。
     * @param profileId   客户画像ID，依赖于已经配置 的客户画像ID。由营销管理平台分配。
     * @param username  用户名
     * @param password  密码
     * @return
     * @throws LIException
     */
    QRY181107Result qryCustomerPortrait(String servNo, String profileId, String username, String password)throws LIException;

}
