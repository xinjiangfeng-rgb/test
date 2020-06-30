package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

/**
 *线上统一支付验签接口通用服务
 */
public class PAY181114Result extends BaseServiceInvocationResult {


    private String respCode;//
    private String respDesc;//

    private UnifiedPay unifiedPays = new UnifiedPay();

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

    public UnifiedPay getUnifiedPays() {
        return unifiedPays;
    }

    public void setUnifiedPays(UnifiedPay unifiedPays) {
        this.unifiedPays = unifiedPays;
    }
}
