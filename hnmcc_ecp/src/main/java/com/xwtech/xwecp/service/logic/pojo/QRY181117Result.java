package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY181117Result extends BaseServiceInvocationResult {
    private String resultCode;
    private String bizDesc;
    private String resultDesc;
    private String bizCode;

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getBizDesc() {
        return bizDesc;
    }

    public void setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }
/*    private List<PayResultList> SoMemberDeal;

    public List<PayResultList> getSoMemberDeal() {
        return SoMemberDeal;
    }

    public void setSoMemberDeal(List<PayResultList> soMemberDeal) {
        SoMemberDeal = soMemberDeal;
    }*/
}
