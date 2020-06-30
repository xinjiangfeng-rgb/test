package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY040045Result extends BaseServiceInvocationResult
{
	private String totalFlux = "";

	private String ragingRes = "";

	private String outRes = "";

	public String getTotalFlux() {
		return totalFlux;
	}

	public void setTotalFlux(String totalFlux) {
		this.totalFlux = totalFlux;
	}

	public String getRagingRes() {
		return ragingRes;
	}

	public void setRagingRes(String ragingRes) {
		this.ragingRes = ragingRes;
	}

	public String getOutRes() {
		return outRes;
	}

	public void setOutRes(String outRes) {
		this.outRes = outRes;
	}

	@Override
	public String toString() {
		return "QRY040045Result [totalFlux=" + totalFlux + ", ragingRes="
				+ ragingRes + ", outRes=" + outRes + "]";
	}


}