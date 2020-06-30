package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.List;

public class QRY191212Result extends BaseServiceInvocationResult {
    private String respCode;
    private String respDesc;
    private List<CardFailure> CardFailures;


    public List<CardFailure> getCardFailures() {
        return CardFailures;
    }

    public void setCardFailures(List<CardFailure> cardFailures) {
        CardFailures = cardFailures;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }
}
