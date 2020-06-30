package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.List;

/**
 * 主副卡资源信息查询接口
 */
public class QRY180818Result extends BaseServiceInvocationResult {

    private String respCode;//接口调用的返回码   00000：成功，其他：失败

    private String respDesc;//接口调用的返回消息描述。
    private List<QryResourceBean> result;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public List<QryResourceBean> getResult() {
        return result;
    }

    public void setResult(List<QryResourceBean> result) {
        this.result = result;
    }
}
