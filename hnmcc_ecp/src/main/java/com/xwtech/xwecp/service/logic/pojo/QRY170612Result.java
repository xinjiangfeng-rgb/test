package com.xwtech.xwecp.service.logic.pojo;

import java.util.ArrayList;
import java.util.List;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY170612Result extends BaseServiceInvocationResult {

	
	
	private List<QRY170612ResultList> qry170612ResultList = new ArrayList<QRY170612ResultList>();

	public List<QRY170612ResultList> getQry170612ResultList() {
		return qry170612ResultList;
	}

	public void setQry170612ResultList(List<QRY170612ResultList> qry170612ResultList) {
		this.qry170612ResultList = qry170612ResultList;
	}
}
