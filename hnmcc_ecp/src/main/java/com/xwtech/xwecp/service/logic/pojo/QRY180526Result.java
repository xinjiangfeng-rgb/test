package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.List;

public class QRY180526Result extends BaseServiceInvocationResult {


    private List<ScoreBean> result;

    public List<ScoreBean> getResult() {
        return result;
    }

    public void setResult(List<ScoreBean> result) {
        this.result = result;
    }
}
