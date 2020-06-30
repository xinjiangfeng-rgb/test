package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.List;

public class QRY420033Result extends BaseServiceInvocationResult {

    private List<BusinessResult> businessResults;

    public List<BusinessResult> getBusinessResults() {
        return businessResults;
    }

    public void setBusinessResults(List<BusinessResult> businessResults) {
        this.businessResults = businessResults;
    }
}
