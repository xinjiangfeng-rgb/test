package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY040105Result extends BaseServiceInvocationResult
{
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 描述
	 */
	private String desc;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "QRY040105Result [state=" + state + ", desc=" + desc + "]";
	}
	
}	