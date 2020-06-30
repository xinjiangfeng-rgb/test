package com.xwtech.xwecp.service.logic.pojo;

public class InternationalBasic
{

	/***
	 * 订单编码
	 */
	private String optsn;
	/***
	 * 二次确认标志
	 */
	private String cofirmFlag;

	public String getOptsn() {
		return optsn;
	}

	public void setOptsn(String optsn) {
		this.optsn = optsn;
	}

	public String getCofirmFlag() {
		return cofirmFlag;
	}

	public void setCofirmFlag(String cofirmFlag) {
		this.cofirmFlag = cofirmFlag;
	}

	@Override
	public String toString() {
		return "InternationalBasic{" +
				"optsn='" + optsn + '\'' +
				", cofirmFlag='" + cofirmFlag + '\'' +
				'}';
	}
}