package com.xwtech.xwecp.service.logic.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY920731Result  extends BaseServiceInvocationResult implements Serializable  {

	private List<NewBillQueryInfo> newBillQueryInfo = new ArrayList<NewBillQueryInfo>();

	public List<NewBillQueryInfo> getNewBillQueryInfo() {
		return newBillQueryInfo;
	}

	public void setNewBillQueryInfo(List<NewBillQueryInfo> newBillQueryInfo) {
		this.newBillQueryInfo = newBillQueryInfo;
	}

}
