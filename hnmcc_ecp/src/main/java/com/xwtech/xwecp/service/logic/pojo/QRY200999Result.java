package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY200999Result extends BaseServiceInvocationResult{

	private String canPrintFlag;
	private String canPrintAmount;
	private String msgInfo;
	private String invoiceInstanceId;
	public String getCanPrintFlag() {
		return canPrintFlag;
	}
	public void setCanPrintFlag(String canPrintFlag) {
		this.canPrintFlag = canPrintFlag;
	}
	public String getCanPrintAmount() {
		return canPrintAmount;
	}
	public void setCanPrintAmount(String canPrintAmount) {
		this.canPrintAmount = canPrintAmount;
	}
	public String getMsgInfo() {
		return msgInfo;
	}
	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}
	public String getInvoiceInstanceId() {
		return invoiceInstanceId;
	}
	public void setInvoiceInstanceId(String invoiceInstanceId) {
		this.invoiceInstanceId = invoiceInstanceId;
	}
	
	
}
