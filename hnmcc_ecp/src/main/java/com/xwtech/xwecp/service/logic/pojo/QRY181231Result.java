package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

/**
 * 集团帐户实时余额查询
 */
public class QRY181231Result extends BaseServiceInvocationResult {


    /**
     * 返回金额 余额(单位：元)
     */
    private GroupAccount result ;

    public GroupAccount getResult() {
        return result;
    }

    public void setResult(GroupAccount result) {
        this.result = result;
    }
}
