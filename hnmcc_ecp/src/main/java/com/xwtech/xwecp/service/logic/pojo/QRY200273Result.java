package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断用户是否欠费 判断用户的余额
 */
public class QRY200273Result extends BaseServiceInvocationResult {
    private List<QryUserOwesFee> result = new ArrayList<>();

    public List<QryUserOwesFee> getResult() {
        return result;
    }

    public void setResult(List<QryUserOwesFee> result) {
        this.result = result;
    }
}
