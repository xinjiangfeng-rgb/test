package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.List;

public class QRY202001Result  extends BaseServiceInvocationResult {

    private List<QRY202001ResultList> qry202001ResultList;

    public List<QRY202001ResultList> getQry202001ResultList() {
        return qry202001ResultList;
    }

    public void setQry202001ResultList(List<QRY202001ResultList> qry202001ResultList) {
        this.qry202001ResultList = qry202001ResultList;
    }
}
