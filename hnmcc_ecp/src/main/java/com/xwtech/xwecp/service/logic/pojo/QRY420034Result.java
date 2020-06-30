package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.List;

public class QRY420034Result extends BaseServiceInvocationResult {

    private List<BillAccountResult> billAccountResults;

    public List<BillAccountResult> getBillAccountResults() {
        return billAccountResults;
    }

    public void setBillAccountResults(List<BillAccountResult> billAccountResults) {
        this.billAccountResults = billAccountResults;
    }
}
