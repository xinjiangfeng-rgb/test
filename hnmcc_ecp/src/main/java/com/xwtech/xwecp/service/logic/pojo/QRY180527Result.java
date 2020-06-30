package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.List;

public class QRY180527Result extends BaseServiceInvocationResult {


    private List<NewScoreBean>  result;

    public List<NewScoreBean> getResult() {
        return result;
    }

    public void setResult(List<NewScoreBean> result) {
        this.result = result;
    }
}
