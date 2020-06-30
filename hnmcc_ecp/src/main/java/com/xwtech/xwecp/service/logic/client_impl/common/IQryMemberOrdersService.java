package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.pojo.QRY181229Result;

public interface IQryMemberOrdersService {


    /**
     * 集团成员已订购流量查询
     * @param billId    手机号码
     * @param flag      业务类型
     * @return
     */
  public QRY181229Result qryMemberOrders(String billId, String flag);
}
