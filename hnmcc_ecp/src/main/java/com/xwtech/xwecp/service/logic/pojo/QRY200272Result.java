package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询用户订购国际业务到期时间及状态
 */
public class QRY200272Result extends BaseServiceInvocationResult {
    private List<IntelBusState> result = new ArrayList<>();

    public List<IntelBusState> getResult() {
        return result;
    }

    public void setResult(List<IntelBusState> result) {
        this.result = result;
    }
}
