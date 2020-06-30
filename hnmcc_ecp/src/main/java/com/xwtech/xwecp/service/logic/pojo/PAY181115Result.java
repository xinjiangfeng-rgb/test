package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class PAY181115Result extends BaseServiceInvocationResult {
    private String bizDesc;
    private String bizCode;
    private PayResponse payResponse;
    private String checkSignFlag;
    private String retMsg;
    private Object parametersMap;
    private String payEwm;
    private String retCode;

    public String getBizDesc() {
        return bizDesc;
    }

    public void setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public PayResponse getPayResponse() {
        return payResponse;
    }

    public void setPayResponse(PayResponse payResponse) {
        this.payResponse = payResponse;
    }

    public String getCheckSignFlag() {
        return checkSignFlag;
    }

    public void setCheckSignFlag(String checkSignFlag) {
        this.checkSignFlag = checkSignFlag;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public Object getParametersMap() {
        return parametersMap;
    }

    public void setParametersMap(Object parametersMap) {
        this.parametersMap = parametersMap;
    }

    public String getPayEwm() {
        return payEwm;
    }

    public void setPayEwm(String payEwm) {
        this.payEwm = payEwm;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }
}
