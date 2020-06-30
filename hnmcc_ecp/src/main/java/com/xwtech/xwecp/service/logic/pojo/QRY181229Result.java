package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 5.1.2.	集团成员已订购流量查询
 */
public class QRY181229Result extends BaseServiceInvocationResult {


    private List<MemberOrders> result = new ArrayList<>();


    public List<MemberOrders> getResult() {
        return result;
    }

    public void setResult(List<MemberOrders> result) {
        this.result = result;
    }
}
