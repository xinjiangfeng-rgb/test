package com.xwtech.xwecp.service.logic.pojo;

import java.util.List;

public class RightList {
    private String subscriptionId;
    private String bossCode;
    private String interestsName;
    private String orderTime;
    private String selectSubServiceId;
    private String recvStatus;
    private String recvMessage;
    private String receiveTime;
    private List<Interests> interests;

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getBossCode() {
        return bossCode;
    }

    public void setBossCode(String bossCode) {
        this.bossCode = bossCode;
    }

    public String getInterestsName() {
        return interestsName;
    }

    public void setInterestsName(String interestsName) {
        this.interestsName = interestsName;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getSelectSubServiceId() {
        return selectSubServiceId;
    }

    public void setSelectSubServiceId(String selectSubServiceId) {
        this.selectSubServiceId = selectSubServiceId;
    }

    public String getRecvStatus() {
        return recvStatus;
    }

    public void setRecvStatus(String recvStatus) {
        this.recvStatus = recvStatus;
    }

    public String getRecvMessage() {
        return recvMessage;
    }

    public void setRecvMessage(String recvMessage) {
        this.recvMessage = recvMessage;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public List<Interests> getInterests() {
        return interests;
    }

    public void setInterests(List<Interests> interests) {
        this.interests = interests;
    }

    @Override
    public String toString() {
        return "RightList{" +
                "subscriptionId='" + subscriptionId + '\'' +
                ", bossCode='" + bossCode + '\'' +
                ", interestsName='" + interestsName + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", selectSubServiceId='" + selectSubServiceId + '\'' +
                ", recvStatus='" + recvStatus + '\'' +
                ", recvMessage='" + recvMessage + '\'' +
                ", receiveTime='" + receiveTime + '\'' +
                ", interests=" + interests +
                '}';
    }
}
