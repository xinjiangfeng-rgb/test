package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.List;

public class QRY190425Result extends BaseServiceInvocationResult {

private List<CurrentPriceBean> CurrentPrice;

    public List<CurrentPriceBean> getCurrentPrice() {
        return CurrentPrice;
    }

    public void setCurrentPrice(List<CurrentPriceBean> currentPrice) {
        CurrentPrice = currentPrice;
    }
}
