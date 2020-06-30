package com.xwtech.xwecp.service.logic.pojo;

public class AddValueCheckResult {

    private String offerId;
    private String canSel;
    private String errMsg;
    private String selFee;
    private String offerCode;

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getCanSel() {
        return canSel;
    }

    public void setCanSel(String canSel) {
        this.canSel = canSel;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getSelFee() {
        return selFee;
    }

    public void setSelFee(String selFee) {
        this.selFee = selFee;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public void setOfferCode(String offerCode) {
        this.offerCode = offerCode;
    }
}
