package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

/**
 * Created by wuyankai on 2017/8/23.
 */
public class DEL930021Result extends BaseServiceInvocationResult{
    private String effMode;
    private String optSN;


    public String getEffMode() {
        return effMode;
    }

    public void setEffMode(String effMode) {
        this.effMode = effMode;
    }

    public String getOptSN() {
        return optSN;
    }

    public void setOptSN(String optSN) {
        this.optSN = optSN;
    }
}
