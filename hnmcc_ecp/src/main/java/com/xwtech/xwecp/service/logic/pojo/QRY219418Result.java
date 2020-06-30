package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.io.Serializable;
import java.util.List;

public class QRY219418Result extends BaseServiceInvocationResult implements Serializable {

    private List<ProdsInfoBean> result;

    public List<ProdsInfoBean> getResult() {
        return result;
    }

    public void setResult(List<ProdsInfoBean> result) {
        this.result = result;
    }
}
