package com.xwtech.xwecp.service.logic.pojo;

public class Interests {
    private String subServiceId;
    private String subServiceName;
    private String status;
    private String dataEntry;

    public String getSubServiceId() {
        return subServiceId;
    }

    public void setSubServiceId(String subServiceId) {
        this.subServiceId = subServiceId;
    }

    public String getSubServiceName() {
        return subServiceName;
    }

    public void setSubServiceName(String subServiceName) {
        this.subServiceName = subServiceName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataEntry() {
        return dataEntry;
    }

    public void setDataEntry(String dataEntry) {
        this.dataEntry = dataEntry;
    }

    @Override
    public String toString() {
        return "Interests{" +
                "subServiceId='" + subServiceId + '\'' +
                ", subServiceName='" + subServiceName + '\'' +
                ", status='" + status + '\'' +
                ", dataEntry='" + dataEntry + '\'' +
                '}';
    }
}
