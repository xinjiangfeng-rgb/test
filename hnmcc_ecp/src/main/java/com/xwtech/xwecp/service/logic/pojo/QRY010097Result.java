package com.xwtech.xwecp.service.logic.pojo;

import java.io.Serializable;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY010097Result extends BaseServiceInvocationResult implements Serializable
{
	/**
	 * 当天消费额
	 */
	private String currentDayFee;

	private String daiFuFee;

	private String geRenFee;




	

	public String getCurrentDayFee() {
		return currentDayFee;
	}

	public void setCurrentDayFee(String currentDayFee) {
		this.currentDayFee = currentDayFee;
	}

	public String getDaiFuFee() {
		return daiFuFee;
	}

	public void setDaiFuFee(String daiFuFee) {
		this.daiFuFee = daiFuFee;
	}

	public String getGeRenFee() {
		return geRenFee;
	}

	public void setGeRenFee(String geRenFee) {
		this.geRenFee = geRenFee;
	}
}