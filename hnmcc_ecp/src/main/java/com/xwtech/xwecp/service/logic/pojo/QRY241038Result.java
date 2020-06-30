package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY241038Result extends BaseServiceInvocationResult {
    private String newBalance;
    private String custName;
    private SubjectList subjectList;
    private String reserveBalance;
    private String acount;
    private CostinfoList costinfoList;
    private String busiCode;
    private String startTime;

    public String getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(String newBalance) {
        this.newBalance = newBalance;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public SubjectList getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(SubjectList subjectList) {
        this.subjectList = subjectList;
    }

    public String getReserveBalance() {
        return reserveBalance;
    }

    public void setReserveBalance(String reserveBalance) {
        this.reserveBalance = reserveBalance;
    }

    public String getAcount() {
        return acount;
    }

    public void setAcount(String acount) {
        this.acount = acount;
    }

    public CostinfoList getCostinfoList() {
        return costinfoList;
    }

    public void setCostinfoList(CostinfoList costinfoList) {
        this.costinfoList = costinfoList;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
