package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

/**
 * 集团联系人查询
 * @author Wangjiajia
 * 2015-09-17
 */
public class QRY040109Result extends BaseServiceInvocationResult
{
	private String result;
	private String gbillId;
	private String name;
	private String billId;
	private String PName;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getGbillId() {
		return gbillId;
	}
	public void setGbillId(String gbillId) {
		this.gbillId = gbillId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getPName() {
		return PName;
	}
	public void setPName(String pName) {
		PName = pName;
	}
}