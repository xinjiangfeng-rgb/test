package com.xwtech.xwecp.service.logic.pojo;

public class Customers {

    private String id;
    private String interestsName;
    private String orderTime;
    private String selectSubServiceName;
    private String status;
    private String receiveTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSelectSubServiceName() {
        return selectSubServiceName;
    }

    public void setSelectSubServiceName(String selectSubServiceName) {
        this.selectSubServiceName = selectSubServiceName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }
}
