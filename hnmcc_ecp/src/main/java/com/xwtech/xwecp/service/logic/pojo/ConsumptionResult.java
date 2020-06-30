package com.xwtech.xwecp.service.logic.pojo;

public class ConsumptionResult {

    private String feeTypeName;
    private String receAmount;
    private String billingCycle;
    private String resultDesc;
    private String feeTypeId;
    private String feeTypeAmount;
    private String resultCode;

    public String getFeeTypeName() {
        return feeTypeName;
    }

    public void setFeeTypeName(String feeTypeName) {
        this.feeTypeName = feeTypeName;
    }

    public String getReceAmount() {
        return receAmount;
    }

    public void setReceAmount(String receAmount) {
        this.receAmount = receAmount;
    }

    public String getBillingCycle() {
        return billingCycle;
    }

    public void setBillingCycle(String billingCycle) {
        this.billingCycle = billingCycle;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getFeeTypeId() {
        return feeTypeId;
    }

    public void setFeeTypeId(String feeTypeId) {
        this.feeTypeId = feeTypeId;
    }

    public String getFeeTypeAmount() {
        return feeTypeAmount;
    }

    public void setFeeTypeAmount(String feeTypeAmount) {
        this.feeTypeAmount = feeTypeAmount;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
