package com.xwtech.xwecp.service.logic.pojo;

import java.util.List;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

/**
 * 用户信用星级评定明细查询
 * @author wangjiajia
 * 2015-03-23
 */
public class QRY040079Result extends BaseServiceInvocationResult {
	private static final long serialVersionUID = 1L;
	private long         credit_level;
	private long         all_score;
	private List<Detail> detail;
	private List<Cfg>    cfg;
	//wangjiajia
	/**
	 * 级别
	 */
	private String creditLevel;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 信用度
	 */
	private String creditLimit;
	/**
	 * 是否存在待升级的级别
	 */
	private String isexits;
	/**
	 * 是否存在待升级的月份
	 */
	private String month;
	/**
	 * 级别
	 */
	private String wCreditLevel;
	/**
	 * 信用度
	 */
	private String wCreditLimit;
	/**
	 * 是否评级
	 */
	private String flag;
	
	public long getCredit_level() {
		return credit_level;
	}
	public void setCredit_level(long creditLevel) {
		credit_level = creditLevel;
	}
	public long getAll_score() {
		return all_score;
	}
	public void setAll_score(long allScore) {
		all_score = allScore;
	}
	public List<Detail> getDetail() {
		return detail;
	}
	public void setDetail(List<Detail> detail) {
		this.detail = detail;
	}
	public List<Cfg> getCfg() {
		return cfg;
	}
	public void setCfg(List<Cfg> cfg) {
		this.cfg = cfg;
	}
	public String getCreditLevel() {
		return creditLevel;
	}
	public void setCreditLevel(String creditLevel) {
		this.creditLevel = creditLevel;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}
	public String getIsexits() {
		return isexits;
	}
	public void setIsexits(String isexits) {
		this.isexits = isexits;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getWCreditLevel() {
		return wCreditLevel;
	}
	public void setWCreditLevel(String wCreditLevel) {
		this.wCreditLevel = wCreditLevel;
	}
	public String getWCreditLimit() {
		return wCreditLimit;
	}
	public void setWCreditLimit(String wCreditLimit) {
		this.wCreditLimit = wCreditLimit;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
