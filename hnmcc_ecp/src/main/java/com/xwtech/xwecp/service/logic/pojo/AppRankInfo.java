package com.xwtech.xwecp.service.logic.pojo;

/*
* 用户的流量使用情况API接口---AppRankInfo
* */
public class AppRankInfo {
    private String ranks;//排行
    private String prop;//流量使用占比
    private String phoneNo;//手机号
    private String busiName;//App名称

    public String getRanks() {
        return ranks;
    }

    public void setRanks(String ranks) {
        this.ranks = ranks;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getBusiName() {
        return busiName;
    }

    public void setBusiName(String busiName) {
        this.busiName = busiName;
    }
}
