package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

/**
 * 5.1.	发送实时短信家客
 */
public class QRY180821Result extends BaseServiceInvocationResult {

    private String returnCode;//返回代码    0000 短信发送成功 其它编码为失败

    private String returnMessage;//返回信息

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
}
