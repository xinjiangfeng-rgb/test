package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY300012Result extends BaseServiceInvocationResult {

    private ResultObject result;
    private ReturnInfos returnInfo;

    public ResultObject getResult() {
        return result;
    }

    public void setResult(ResultObject result) {
        this.result = result;
    }

    public ReturnInfos getReturnInfo() {
        return returnInfo;
    }

    public void setReturnInfo(ReturnInfos returnInfo) {
        this.returnInfo = returnInfo;
    }
}
