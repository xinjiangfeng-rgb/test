package com.xwtech.xwecp.service.logic.pojo;


import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.List;

public class QRY161203Result extends BaseServiceInvocationResult {
	
	/**
	 * 月流量列表(流量账单)
	 */
	private List<MonthBill> monthFluxsList;

	public List<MonthBill> getMonthFluxsList() {
		return monthFluxsList;
	}

	public void setMonthFluxsList(List<MonthBill> monthFluxsList) {
		this.monthFluxsList = monthFluxsList;
	}


	
}
