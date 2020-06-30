package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.List;

public class DEL200604Result extends BaseServiceInvocationResult {

    private String respCode;

    private List<AddValueCheckResult> AddValueCheckResults;

    public List<AddValueCheckResult> getAddValueCheckResults() {
        return AddValueCheckResults;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public void setAddValueCheckResults(List<AddValueCheckResult> addValueCheckResults) {
        AddValueCheckResults = addValueCheckResults;
    }
}
