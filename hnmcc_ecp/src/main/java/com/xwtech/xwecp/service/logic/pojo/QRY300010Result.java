package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY300010Result extends BaseServiceInvocationResult {


    private ResultObject result;

    private ReturnInfoBeans returnInfo;

    public ResultObject getResult() {
        return result;
    }

    public void setResult(ResultObject result) {
        this.result = result;
    }

    public ReturnInfoBeans getReturnInfo() {
        return returnInfo;
    }

    public void setReturnInfo(ReturnInfoBeans returnInfo) {
        this.returnInfo = returnInfo;
    }

    @Override
    public String toString() {
        return "QRY300010Result{" +
                "result=" + result +
                ", returnInfo=" + returnInfo +
                '}';
    }
}
