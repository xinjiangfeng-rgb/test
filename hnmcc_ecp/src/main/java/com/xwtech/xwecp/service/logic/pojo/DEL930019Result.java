package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.io.Serializable;

public class DEL930019Result extends BaseServiceInvocationResult implements Serializable {
    private String optsn;
    private String confirmFlag;
    private String rtnType;

    public String getOptsn() {
        return optsn;
    }

    public void setOptsn(String optsn) {
        this.optsn = optsn;
    }

    public String getConfirmFlag() {
        return confirmFlag;
    }

    public void setConfirmFlag(String confirmFlag) {
        this.confirmFlag = confirmFlag;
    }

    public String getRtnType() {
        return rtnType;
    }

    public void setRtnType(String rtnType) {
        this.rtnType = rtnType;
    }
}
