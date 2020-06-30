package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户回复短信查询的Bean
 */
public class QRY180822Result extends BaseServiceInvocationResult {

    private String returnCode;//返回代码    0000 查询成功 其它编码为失败

    private String returnMessage;//返回信息

    private List<QryClientAnswerSmsBean> recordValue = new ArrayList<>();//返回信息

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public List<QryClientAnswerSmsBean> getRecordValue() {
        return recordValue;
    }

    public void setRecordValue(List<QryClientAnswerSmsBean> recordValue) {
        this.recordValue = recordValue;
    }
}
