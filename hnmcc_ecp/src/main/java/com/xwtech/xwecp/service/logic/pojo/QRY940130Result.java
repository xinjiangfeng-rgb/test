package com.xwtech.xwecp.service.logic.pojo;


import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/18.
 */
public class QRY940130Result extends BaseServiceInvocationResult implements Serializable {
    private String  randomFlag;

    public String getRandomFlag() {
        return randomFlag;
    }

    public void setRandomFlag(String randomFlag) {
        this.randomFlag = randomFlag;
    }
}
