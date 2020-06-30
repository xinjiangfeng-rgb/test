package com.xwtech.xwecp.service.logic.pojo;

import java.io.Serializable;


public class GPRSFluxsMonth implements Serializable
{
	/**
	 * 月份
	 */
	private String month;
	/**
	 * 套餐外流量
	 */
	private String outRes;
	/**
	 * 套餐内流量
	 */
	private String ratingRes;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getOutRes() {
		return outRes;
	}
	public void setOutRes(String outRes) {
		this.outRes = outRes;
	}
	public String getRatingRes() {
		return ratingRes;
	}
	public void setRatingRes(String ratingRes) {
		this.ratingRes = ratingRes;
	}
}