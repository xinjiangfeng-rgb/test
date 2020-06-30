package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class DEL190523Result extends BaseServiceInvocationResult {
    private String flag;
    private String errMsg;
    private String orderCode;
    private String ploy_ord_code;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getPloy_ord_code() {
        return ploy_ord_code;
    }

    public void setPloy_ord_code(String ploy_ord_code) {
        this.ploy_ord_code = ploy_ord_code;
    }
}