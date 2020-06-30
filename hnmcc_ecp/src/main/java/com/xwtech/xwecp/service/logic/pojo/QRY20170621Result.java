package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.util.ArrayList;
import java.util.List;

public class QRY20170621Result extends BaseServiceInvocationResult{

	public List<QRY20170621ResultList> qry20170621ResultList = new ArrayList<>();

	public List<QRY20170621ResultList> getQry20170621ResultList() {
		return qry20170621ResultList;
	}

	public void setQry20170621ResultList(List<QRY20170621ResultList> qry20170621ResultList) {
		this.qry20170621ResultList = qry20170621ResultList;
	}
}
