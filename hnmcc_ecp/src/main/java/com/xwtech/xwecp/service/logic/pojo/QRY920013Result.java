package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

import java.io.Serializable;

public class QRY920013Result extends BaseServiceInvocationResult implements Serializable {

	private String totalFee;
	private String balance;
	private String immediatelySign;
	private String validDate;
	private String feeEndDate;
	private String lastFee;
	private String accountDay;

	private String userType;
	private String specialBalance;
	private String payOfOther;
	private String persionalPay;
	private String fuPayOfOther;

	public String getTotalFee(){
		return totalFee;
	}
	public void setTotalFee(String totalFee){
		this.totalFee = totalFee;
	}
	public String getBalance(){
		return balance;
	}
	public void setBalance(String balance){
		this.balance = balance;
	}
	public String getImmediatelySign(){
		return immediatelySign;
	}
	public void setImmediatelySign(String immediatelySign){
		this.immediatelySign = immediatelySign;
	}
	public String getValidDate(){
		return validDate;
	}
	public void setValidDate(String validDate){
		this.validDate = validDate;
	}
	public String getFeeEndDate(){
		return feeEndDate;
	}
	public void setFeeEndDate(String feeEndDate){
		this.feeEndDate = feeEndDate;
	}
	public String getLastFee(){
		return lastFee;
	}
	public void setLastFee(String lastFee){
		this.lastFee = lastFee;
	}
	public String getAccountDay(){
		return accountDay;
	}
	public void setAccountDay(String accountDay){
		this.accountDay = accountDay;
	}
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getSpecialBalance() {
		return specialBalance;
	}

	public void setSpecialBalance(String specialBalance) {
		this.specialBalance = specialBalance;
	}

	public String getPayOfOther() {
		return payOfOther;
	}

	public void setPayOfOther(String payOfOther) {
		this.payOfOther = payOfOther;
	}

	public String getPersionalPay() {
		return persionalPay;
	}

	public void setPersionalPay(String persionalPay) {
		this.persionalPay = persionalPay;
	}

	public String getFuPayOfOther() {
		return fuPayOfOther;
	}

	public void setFuPayOfOther(String fuPayOfOther) {
		this.fuPayOfOther = fuPayOfOther;
	}
}
