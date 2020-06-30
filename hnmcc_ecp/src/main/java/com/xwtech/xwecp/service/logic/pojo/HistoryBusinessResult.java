package com.xwtech.xwecp.service.logic.pojo;

public class HistoryBusinessResult {
    private String keyNum;
    private String itemName;
    private String freeResPriortity;
    private String forwardCycle;
    private String propId;
    private String itemId;
    private String cycle;
    private String doneCode;
    private String freeRes;
    private String freeResUsed;
    private String lastFreeResUsed;
    private String validDate;
    private String expireDate;
    private String subDate;
    private String remark;

    public String getFreeResPriortity() {
        return freeResPriortity;
    }

    public void setFreeResPriortity(String freeResPriortity) {
        this.freeResPriortity = freeResPriortity;
    }

    public String getForwardCycle() {
        return forwardCycle;
    }

    public void setForwardCycle(String forwardCycle) {
        this.forwardCycle = forwardCycle;
    }

    public String getPropId() {
        return propId;
    }

    public void setPropId(String propId) {
        this.propId = propId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getDoneCode() {
        return doneCode;
    }

    public void setDoneCode(String doneCode) {
        this.doneCode = doneCode;
    }

    public String getFreeRes() {
        return freeRes;
    }

    public void setFreeRes(String freeRes) {
        this.freeRes = freeRes;
    }

    public String getFreeResUsed() {
        return freeResUsed;
    }

    public void setFreeResUsed(String freeResUsed) {
        this.freeResUsed = freeResUsed;
    }

    public String getLastFreeResUsed() {
        return lastFreeResUsed;
    }

    public void setLastFreeResUsed(String lastFreeResUsed) {
        this.lastFreeResUsed = lastFreeResUsed;
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

    public String getSubDate() {
        return subDate;
    }

    public void setSubDate(String subDate) {
        this.subDate = subDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String resultDesc;

    private String resultCode;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getKeyNum() {
        return keyNum;
    }

    public void setKeyNum(String keyNum) {
        this.keyNum = keyNum;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
