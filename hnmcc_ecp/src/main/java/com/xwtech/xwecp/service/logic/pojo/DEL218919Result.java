package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

/**
 * 转套餐并参加活动
 */
public class DEL218919Result extends BaseServiceInvocationResult {

    private String returnCode;//返回码 0000:业务办理成功 9999:业务办理失败
    private String returnMsg;//业务办理描述|业务办理失败原因


    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    @Override
    public String toString() {
        return "DEL218919Result{" +
                "returnCode='" + returnCode + '\'' +
                ", returnMsg='" + returnMsg + '\'' +
                '}';
    }
}
