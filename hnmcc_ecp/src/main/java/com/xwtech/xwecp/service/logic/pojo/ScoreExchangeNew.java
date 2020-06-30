package com.xwtech.xwecp.service.logic.pojo;

import java.io.Serializable;

public class ScoreExchangeNew implements Serializable {
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private String optDate;//变更时间
	private String score;//变更分值
	private String optCode;
	private String optName;//变更方式
	private String type;//积分类型
	private String effDate;
	private String expDate;//有效期
	private String remark;

	public String getOptDate() {
		return optDate;
	}

	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getOptCode() {
		return optCode;
	}

	public void setOptCode(String optCode) {
		this.optCode = optCode;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEffDate() {
		return effDate;
	}

	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
