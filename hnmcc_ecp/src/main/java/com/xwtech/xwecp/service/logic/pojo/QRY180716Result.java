package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY180716Result extends BaseServiceInvocationResult {

    private String rstCode;
    private String rstNum;
    private String rstMsg;
    private String isWarn;

    public String getRstCode() {
        return rstCode;
    }

    public void setRstCode(String rstCode) {
        this.rstCode = rstCode;
    }

    public String getRstNum() {
        return rstNum;
    }

    public void setRstNum(String rstNum) {
        this.rstNum = rstNum;
    }

    public String getRstMsg() {
        return rstMsg;
    }

    public void setRstMsg(String rstMsg) {
        this.rstMsg = rstMsg;
    }

    public String getIsWarn() {
        return isWarn;
    }

    public void setIsWarn(String isWarn) {
        this.isWarn = isWarn;
    }
}
