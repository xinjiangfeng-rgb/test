package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import java.util.List;
import java.util.ArrayList;
import com.xwtech.xwecp.service.logic.pojo.FeeDetail;

public class QRY040099Result extends BaseServiceInvocationResult
{
	private String balance;//实时余额
	
	private String totalFee;//消费额
	
	private String feeResUsed;//流量使用流量
	
	private String score;//积分

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getFeeResUsed() {
		return feeResUsed;
	}

	public void setFeeResUsed(String feeResUsed) {
		this.feeResUsed = feeResUsed;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
}