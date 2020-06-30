package com.xwtech.xwecp.service.logic.pojo;

/**
 * 全球通客户查询接口
 */
public class GotoneCustomers {

    private String gotoneLevel;//全球通档次
    private String gotoneFee;//全球通价值分
    private String validDate;//生效时间
    private String expireDate;//失效时间

    public String getGotoneLevel() {
        return gotoneLevel;
    }

    public void setGotoneLevel(String gotoneLevel) {
        this.gotoneLevel = gotoneLevel;
    }

    public String getGotoneFee() {
        return gotoneFee;
    }

    public void setGotoneFee(String gotoneFee) {
        this.gotoneFee = gotoneFee;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
}
