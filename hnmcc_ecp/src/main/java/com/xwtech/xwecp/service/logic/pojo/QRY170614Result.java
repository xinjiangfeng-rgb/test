package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

/**
 * Created by Administrator on 2017/7/3.
 */
public class QRY170614Result extends BaseServiceInvocationResult {
    private String billId;
    private String offerId;
    private String validType;
    private String isCancle;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getValidType() {
        return validType;
    }

    public void setValidType(String validType) {
        this.validType = validType;
    }

    public String getIsCancle() {
        return isCancle;
    }

    public void setIsCancle(String isCancle) {
        this.isCancle = isCancle;
    }
}
