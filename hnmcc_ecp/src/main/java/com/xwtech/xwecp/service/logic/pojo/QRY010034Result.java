package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import java.util.List;
import java.util.ArrayList;
import com.xwtech.xwecp.service.logic.pojo.PayHistory;

public class QRY010034Result extends BaseServiceInvocationResult
{
	/**
	 * 充值记录列表
	 */
	private List<PayHistoryList> payHistoryList;
	private String typeReuslt;

	public String getTypeReuslt() {
		return typeReuslt;
	}

	public void setTypeReuslt(String typeReuslt) {
		this.typeReuslt = typeReuslt;
	}

	public List<PayHistoryList> getPayHistoryList() {
		return payHistoryList;
	}

	public void setPayHistoryList(List<PayHistoryList> payHistoryList) {
		this.payHistoryList = payHistoryList;
	}


	
}