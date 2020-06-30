package com.xwtech.xwecp.service.logic.pojo;

public class SocreNum {

    private String optDate;//积分变更时间  yyyy-MM-dd HH:mm:ss
    private String score;//变更分值
    private String optName;//变更方式   用户操作操作名称
    private String type;//积分类型
    private String effDate;//生效时间   yyyy-MM-dd HH:mm:ss
    private String expDate;//失效时间   yyyy-MM-dd HH:mm:ss
    private String remark;//支付方式
    private String optType;//判断积分增加还是积分减少的分类参数 1表示积分增加，2表示积分减少
    private String actName;//积分变更活动（业务）名称


    public String getOptDate() {
        return optDate;
    }

    public void setOptDate(String optDate) {
        this.optDate = optDate;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEffDate() {
        return effDate;
    }

    public void setEffDate(String effDate) {
        this.effDate = effDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }
}
