package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.List;

public class QRY420032Result extends BaseServiceInvocationResult {


    private List<ConsumptionResult>  consumptionResults;

    public List<ConsumptionResult> getConsumptionResults() {
        return consumptionResults;
    }

    public void setConsumptionResults(List<ConsumptionResult> consumptionResults) {
        this.consumptionResults = consumptionResults;
    }
}
