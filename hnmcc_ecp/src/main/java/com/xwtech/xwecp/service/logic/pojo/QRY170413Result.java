package com.xwtech.xwecp.service.logic.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY170413Result extends BaseServiceInvocationResult implements Serializable {

	private List<ViceCardOutLog> viceCardOutLog = new ArrayList<ViceCardOutLog>();

	public List<ViceCardOutLog> getViceCardOutLog() {
		return viceCardOutLog;
	}

	public void setViceCardOutLog(List<ViceCardOutLog> viceCardOutLog) {
		this.viceCardOutLog = viceCardOutLog;
	}
	
}
