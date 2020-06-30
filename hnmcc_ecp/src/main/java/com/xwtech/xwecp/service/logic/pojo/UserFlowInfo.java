package com.xwtech.xwecp.service.logic.pojo;

/*
* 用户的流量使用情况API接口---UserFlowInfo
* */
public class UserFlowInfo {
    private String phoneNo;//手机号
    private String ranks;//排行
    private String gprsFlow;//流量使用量
    private String prop;//排行百分比

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getRanks() {
        return ranks;
    }

    public void setRanks(String ranks) {
        this.ranks = ranks;
    }

    public String getGprsFlow() {
        return gprsFlow;
    }

    public void setGprsFlow(String gprsFlow) {
        this.gprsFlow = gprsFlow;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }
}
