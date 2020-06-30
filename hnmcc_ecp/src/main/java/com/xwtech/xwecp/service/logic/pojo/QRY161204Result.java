package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.io.Serializable;

public class QRY161204Result extends BaseServiceInvocationResult implements Serializable {
	private String totalFlow;//流量总量
	private String usedFlow;//使用量
	private String dayFlow;//日均使用量

	public String getTotalFlow() {
		return totalFlow;
	}

	public void setTotalFlow(String totalFlow) {
		this.totalFlow = totalFlow;
	}

	public String getUsedFlow() {
		return usedFlow;
	}

	public void setUsedFlow(String usedFlow) {
		this.usedFlow = usedFlow;
	}

	public String getDayFlow() {
		return dayFlow;
	}

	public void setDayFlow(String dayFlow) {
		this.dayFlow = dayFlow;
	}
}