package com.xwtech.xwecp.service.logic.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY170415Result   extends BaseServiceInvocationResult implements Serializable {

	private List<MarketingActivityPayLog> marketingActivityPayLog = new ArrayList<MarketingActivityPayLog>();

	public List<MarketingActivityPayLog> getMarketingActivityPayLog() {
		return marketingActivityPayLog;
	}

	public void setMarketingActivityPayLog(List<MarketingActivityPayLog> marketingActivityPayLog) {
		this.marketingActivityPayLog = marketingActivityPayLog;
	}

}
