package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY199412Result extends BaseServiceInvocationResult {

    private String returnCode;//接口调用状态
    private String bxlFlag;//不限量标识 {0:非不限量用户,1:不限量用户,2:不限量权益用户}
    private String bxlGrade;//赠送不限量权益值总量 [10,15,20,30,40,60,100] 单位G 目前7档 仅BXL_FLAG=2 时返回
    private String limitFlag;//限速标识 {1:正常状态,2:限速速率384kbps,3:限速速率128kbps} 网管中心管控 仅 BXL_FLAG<>0 时返回
    private String limitTime;//限速时间 计费要求限速,CRM限速时间 仅LIMIT_FLAG<>1 时返回
    private String functionFlag;//是否关停上网功能


    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getBxlFlag() {
        return bxlFlag;
    }

    public void setBxlFlag(String bxlFlag) {
        this.bxlFlag = bxlFlag;
    }

    public String getBxlGrade() {
        return bxlGrade;
    }

    public void setBxlGrade(String bxlGrade) {
        this.bxlGrade = bxlGrade;
    }

    public String getLimitFlag() {
        return limitFlag;
    }

    public void setLimitFlag(String limitFlag) {
        this.limitFlag = limitFlag;
    }

    public String getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(String limitTime) {
        this.limitTime = limitTime;
    }

    public String getFunctionFlag() {
        return functionFlag;
    }

    public void setFunctionFlag(String functionFlag) {
        this.functionFlag = functionFlag;
    }
}
