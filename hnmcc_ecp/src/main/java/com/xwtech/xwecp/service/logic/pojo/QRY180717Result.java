package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.List;

public class QRY180717Result extends BaseServiceInvocationResult {
    private List<TransfernetCarryUser> soMemberDeal;

    public List<TransfernetCarryUser> getSoMemberDeal() {
        return soMemberDeal;
    }

    public void setSoMemberDeal(List<TransfernetCarryUser> soMemberDeal) {
        this.soMemberDeal = soMemberDeal;
    }

    @Override
    public String toString() {
        return "QRY180717Result{" +
                "soMemberDeal=" + soMemberDeal +
                '}';
    }
}
