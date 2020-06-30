package com.xwtech.xwecp.service.logic.pojo;



import java.util.List;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY040049Result extends BaseServiceInvocationResult {
	
	/**
	 * 月流量列表
	 */
	private List<GPRSFluxsMonth> monthFluxsList;

	public List<GPRSFluxsMonth> getMonthFluxsList() {
		return monthFluxsList;
	}

	public void setMonthFluxsList(List<GPRSFluxsMonth> monthFluxsList) {
		this.monthFluxsList = monthFluxsList;
	}


	
}
