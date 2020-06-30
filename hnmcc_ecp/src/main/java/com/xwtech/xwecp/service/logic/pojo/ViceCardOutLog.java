package com.xwtech.xwecp.service.logic.pojo;

public class ViceCardOutLog {

	private String amount;
	private String payType;
	/**
	 * "0"为副卡转入，"1"为其 他账户转入
	 */
	private String isViceCard;
	private String outKeynum;
	private String payDate;
	private String spePayment;
	private String expDate;
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getIsViceCard() {
		return isViceCard;
	}
	public void setIsViceCard(String isViceCard) {
		this.isViceCard = isViceCard;
	}
	public String getOutKeynum() {
		return outKeynum;
	}
	public void setOutKeynum(String outKeynum) {
		this.outKeynum = outKeynum;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}


	public String getSpePayment() {
		return spePayment;
	}
	public void setSpePayment(String spePayment) {
		this.spePayment = spePayment;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	
}
