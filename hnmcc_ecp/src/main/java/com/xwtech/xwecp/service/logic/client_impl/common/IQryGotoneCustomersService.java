package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.pojo.QRY181222Result;

public interface IQryGotoneCustomersService {


    /**
     * 全球通客户查询接口
     * @param billId
     * @return
     */
  public QRY181222Result qryGotoneCustomers(String billId);
}
