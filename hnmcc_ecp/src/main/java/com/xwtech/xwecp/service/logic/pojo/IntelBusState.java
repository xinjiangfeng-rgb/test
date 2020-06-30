package com.xwtech.xwecp.service.logic.pojo;

/**
 * 查询用户订购国际业务到期时间及状态
 */
public class IntelBusState {

    private String serviceId;//服务ID

    private String serviceame;//用户余额

    private String busiype;//办理方式

    private String validDate;//服务生效时间

    private String expireate;//服务失效时间

    private String status;//状态

    private String statusame;//状态名称

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceame() {
        return serviceame;
    }

    public void setServiceame(String serviceame) {
        this.serviceame = serviceame;
    }

    public String getBusiype() {
        return busiype;
    }

    public void setBusiype(String busiype) {
        this.busiype = busiype;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getExpireate() {
        return expireate;
    }

    public void setExpireate(String expireate) {
        this.expireate = expireate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusame() {
        return statusame;
    }

    public void setStatusame(String statusame) {
        this.statusame = statusame;
    }
}
