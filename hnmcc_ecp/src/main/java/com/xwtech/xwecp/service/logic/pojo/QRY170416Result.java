package com.xwtech.xwecp.service.logic.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY170416Result extends BaseServiceInvocationResult implements Serializable {

	private List<BillChangeLog> billChangeLog = new ArrayList<BillChangeLog>();

	public List<BillChangeLog> getBillChangeLog() {
		return billChangeLog;
	}

	public void setBillChangeLog(List<BillChangeLog> billChangeLog) {
		this.billChangeLog = billChangeLog;
	}

	
	
}
