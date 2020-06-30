package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.List;

public class DEL929207Result extends BaseServiceInvocationResult {

   private List<OrderMarket> orderMarket;

    public List<OrderMarket> getOrderMarket() {
        return orderMarket;
    }

    public void setOrderMarket(List<OrderMarket> orderMarket) {
        this.orderMarket = orderMarket;
    }
}
