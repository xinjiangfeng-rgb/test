package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

/**
 * 5.1.3.	成员处理情况查询
 * 根据外围渠道订单流水查询运营商订单处理情况
 */
public class QRY181230Result extends BaseServiceInvocationResult {


    private MemberHandling result;

    public MemberHandling getResult() {
        return result;
    }

    public void setResult(MemberHandling result) {
        this.result = result;
    }
}
