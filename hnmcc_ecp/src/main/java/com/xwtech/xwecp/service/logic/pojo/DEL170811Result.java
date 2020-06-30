package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

/**
 * Created by Administrator on 2017/8/29.
 */
public class DEL170811Result  extends BaseServiceInvocationResult{
    //外部流水
    private String optSN;
    //发票信息
    private String billInfo;
    //开户信息
    private String optInfo;


    public String getOptSN() {
        return optSN;
    }

    public void setOptSN(String optSN) {
        this.optSN = optSN;
    }

    public String getBillInfo() {
        return billInfo;
    }

    public void setBillInfo(String billInfo) {
        this.billInfo = billInfo;
    }

    public String getOptInfo() {
        return optInfo;
    }

    public void setOptInfo(String optInfo) {
        this.optInfo = optInfo;
    }
}
