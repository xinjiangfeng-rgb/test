package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.io.Serializable;

/**
 * Created by 54344 on 2018/3/3.
 */
public class QRY010004Result extends BaseServiceInvocationResult implements Serializable {
    private String flag ;


    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
