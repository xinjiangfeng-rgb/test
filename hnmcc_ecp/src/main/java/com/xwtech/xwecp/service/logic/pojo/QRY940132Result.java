package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/18.
 */
public class QRY940132Result extends BaseServiceInvocationResult implements Serializable {
    private String startTime;
    private String vplmn;
    private String callType;
    private String oppNumber;
    private String duration;
    private String tollType;
    private String offerName;
    private String freeRes;
    private String charge;
    private String totalInfo;


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getVplmn() {
        return vplmn;
    }

    public void setVplmn(String vplmn) {
        this.vplmn = vplmn;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getOppNumber() {
        return oppNumber;
    }

    public void setOppNumber(String oppNumber) {
        this.oppNumber = oppNumber;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTollType() {
        return tollType;
    }

    public void setTollType(String tollType) {
        this.tollType = tollType;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getFreeRes() {
        return freeRes;
    }

    public void setFreeRes(String freeRes) {
        this.freeRes = freeRes;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getTotalInfo() {
        return totalInfo;
    }

    public void setTotalInfo(String totalInfo) {
        this.totalInfo = totalInfo;
    }
}
