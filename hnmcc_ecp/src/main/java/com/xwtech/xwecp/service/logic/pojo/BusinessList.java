package com.xwtech.xwecp.service.logic.pojo;

import java.util.List;

public class BusinessList {
    private String bossCodes;
    private String type;
    private String servicePeriod;
    private String claimedPeriod;
    private String effectTime;
    private String expireTime;
    private List<SubService> subServices;

    public String getBossCodes() {
        return bossCodes;
    }

    public void setBossCodes(String bossCodes) {
        this.bossCodes = bossCodes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getServicePeriod() {
        return servicePeriod;
    }

    public void setServicePeriod(String servicePeriod) {
        this.servicePeriod = servicePeriod;
    }

    public String getClaimedPeriod() {
        return claimedPeriod;
    }

    public void setClaimedPeriod(String claimedPeriod) {
        this.claimedPeriod = claimedPeriod;
    }

    public String getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(String effectTime) {
        this.effectTime = effectTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public List<SubService> getSubServices() {
        return subServices;
    }

    public void setSubServices(List<SubService> subServices) {
        this.subServices = subServices;
    }
}
