package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

/**
 * 查询某用户号码的画像信息
 */
public class QRY181107Result extends BaseServiceInvocationResult {

    private String errorCode;//错误码。 0：请求成功 其它：请求失败
    private String errorInfo;//如果为返回null  能力平台会过滤
    private Data data;//返回数据对象
    private String servNo;//手机号码

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getServNo() {
        return servNo;
    }

    public void setServNo(String servNo) {
        this.servNo = servNo;
    }
}
