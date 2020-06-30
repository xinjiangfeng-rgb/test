package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 2017/6/21.
 */
public class QRY170611Result  extends BaseServiceInvocationResult{

    private String  balance;
    private String  speBalance;
    private String sumBalance;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getSpeBalance() {
        return speBalance;
    }

    public void setSpeBalance(String speBalance) {
        this.speBalance = speBalance;
    }

    public String getSumBalance() {
        return sumBalance;
    }

    public void setSumBalance(String sumBalance) {
        this.sumBalance = sumBalance;
    }
}
