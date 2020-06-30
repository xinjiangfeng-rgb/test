package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY202003Result extends BaseServiceInvocationResult {
    private String kdRate;
    private String kdRadiusRate;
    public String getKdRate() {
        return kdRate;
    }

    public void setKdRate(String kdRate) {
        this.kdRate = kdRate;
    }

    public String getKdRadiusRate() {
        return kdRadiusRate;
    }

    public void setKdRadiusRate(String kdRadiusRate) {
        this.kdRadiusRate = kdRadiusRate;
    }
}
