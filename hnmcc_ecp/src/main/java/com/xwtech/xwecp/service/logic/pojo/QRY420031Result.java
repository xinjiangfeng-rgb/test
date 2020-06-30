package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.List;

public class QRY420031Result extends BaseServiceInvocationResult {
private List<BillResult> billResults;

    public List<BillResult> getBillResults() {
        return billResults;
    }

    public void setBillResults(List<BillResult> billResults) {
        this.billResults = billResults;
    }
}
