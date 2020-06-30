package com.xwtech.xwecp.service.logic.client_impl.common;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.pojo.DEL400030Result;

public interface IFailureWorkOrderToDispatchCenterService {

    public DEL400030Result failureWorkOrderToDispatchCenter(String systemId,
                                                            String serialNumber,
                                                            String customerPhone,
                                                            String businessType,
                                                            String taskOrderType,
                                                            String prodectCode,
                                                            String prodectDescribe,
                                                            String taskContet,
                                                            String creatFailTime,
                                                            String soPhone,
                                                            String subBusinessType) throws LIException;
}
