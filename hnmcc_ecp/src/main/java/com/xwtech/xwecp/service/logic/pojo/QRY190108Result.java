package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.List;

public class QRY190108Result extends BaseServiceInvocationResult {


    private List<BroadPhoneBasic> BroadPhoneBasics;
    private String respCode;

    public List<BroadPhoneBasic> getBroadPhoneBasics() {
        return BroadPhoneBasics;
    }

    public void setBroadPhoneBasics(List<BroadPhoneBasic> broadPhoneBasics) {
        BroadPhoneBasics = broadPhoneBasics;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }
}
