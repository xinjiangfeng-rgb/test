package com.xwtech.xwecp.service.logic.pojo;

import java.io.Serializable;


public class MonthBill implements Serializable
{
	/**
	 * 月份
	 */
	private String month;
	/**
	 * 流量值
	 */
	private String flow;
	/**
	 * 单位
	 */
	private String util;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getUtil() {
		return util;
	}

	public void setUtil(String util) {
		this.util = util;
	}
}