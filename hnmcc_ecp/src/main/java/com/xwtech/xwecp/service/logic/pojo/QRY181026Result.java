package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.List;

public class QRY181026Result extends BaseServiceInvocationResult {
    private List<SoMemberDeal> result;

    public QRY181026Result() {
    }

    public List<SoMemberDeal> getResult() {
        return this.result;
    }

    public void setResult(List<SoMemberDeal> result) {
        this.result = result;
    }
}
