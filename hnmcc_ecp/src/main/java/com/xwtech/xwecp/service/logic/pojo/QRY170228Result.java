package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
public class QRY170228Result  extends BaseServiceInvocationResult{
    private List<QRY170228ResultList> qry170228ResultList = new ArrayList<QRY170228ResultList>();

    public List<QRY170228ResultList> getQry170228ResultList() {
        return qry170228ResultList;
    }

    public void setQry170228ResultList(List<QRY170228ResultList> qry170228ResultList) {
        this.qry170228ResultList = qry170228ResultList;
    }
}
