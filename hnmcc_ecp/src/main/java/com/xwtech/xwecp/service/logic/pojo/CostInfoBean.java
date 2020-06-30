package com.xwtech.xwecp.service.logic.pojo;

import java.util.List;

public class CostInfoBean {

private List<MonthInAccountBean> monthInAccount;
private List<LastMonthBalanceBean> lastMonthBalance;
private List<MonthOutExpendBean> monthOutExpend;
private List<NowMonthBalanceBean> nowMonthBalance;

    public List<MonthInAccountBean> getMonthInAccount() {
        return monthInAccount;
    }

    public void setMonthInAccount(List<MonthInAccountBean> monthInAccount) {
        this.monthInAccount = monthInAccount;
    }

    public List<LastMonthBalanceBean> getLastMonthBalance() {
        return lastMonthBalance;
    }

    public void setLastMonthBalance(List<LastMonthBalanceBean> lastMonthBalance) {
        this.lastMonthBalance = lastMonthBalance;
    }

    public List<MonthOutExpendBean> getMonthOutExpend() {
        return monthOutExpend;
    }

    public void setMonthOutExpend(List<MonthOutExpendBean> monthOutExpend) {
        this.monthOutExpend = monthOutExpend;
    }

    public List<NowMonthBalanceBean> getNowMonthBalance() {
        return nowMonthBalance;
    }

    public void setNowMonthBalance(List<NowMonthBalanceBean> nowMonthBalance) {
        this.nowMonthBalance = nowMonthBalance;
    }
}
