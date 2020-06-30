package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class QRY161108Result extends BaseServiceInvocationResult {

	//国内，省内，闲时，定向，当前可用
	private String gnUsed;//国内已用
	private String gnLeft;//国内剩余
	private String snUsed;//省内已用
	private String snLeft;//省内剩余
	private String xsUsed;//闲时已用
	private String xsLeft;//闲时剩余
	private String dxUsed;//定向已用
	private String dxLeft;//定向剩余
	private String zll;//当前可用总流量
	private String ysyzl;//当前可用已使用
	private String leftzl;//当前可用剩余

	//如下三个是一个接口中结果
	private String byyyzll;//本月已用总流量
	private String tw;//套餐外流量
	private String tn;//套餐内流量

	//今日已用
	private String jryy;

	public String getGnUsed() {
		return gnUsed;
	}

	public void setGnUsed(String gnUsed) {
		this.gnUsed = gnUsed;
	}

	public String getGnLeft() {
		return gnLeft;
	}

	public void setGnLeft(String gnLeft) {
		this.gnLeft = gnLeft;
	}

	public String getSnUsed() {
		return snUsed;
	}

	public void setSnUsed(String snUsed) {
		this.snUsed = snUsed;
	}

	public String getSnLeft() {
		return snLeft;
	}

	public void setSnLeft(String snLeft) {
		this.snLeft = snLeft;
	}

	public String getXsUsed() {
		return xsUsed;
	}

	public void setXsUsed(String xsUsed) {
		this.xsUsed = xsUsed;
	}

	public String getXsLeft() {
		return xsLeft;
	}

	public void setXsLeft(String xsLeft) {
		this.xsLeft = xsLeft;
	}

	public String getDxUsed() {
		return dxUsed;
	}

	public void setDxUsed(String dxUsed) {
		this.dxUsed = dxUsed;
	}

	public String getDxLeft() {
		return dxLeft;
	}

	public void setDxLeft(String dxLeft) {
		this.dxLeft = dxLeft;
	}

	public String getZll() {
		return zll;
	}

	public void setZll(String zll) {
		this.zll = zll;
	}

	public String getYsyzl() {
		return ysyzl;
	}

	public void setYsyzl(String ysyzl) {
		this.ysyzl = ysyzl;
	}

	public String getLeftzl() {
		return leftzl;
	}

	public void setLeftzl(String leftzl) {
		this.leftzl = leftzl;
	}

	public String getByyyzll() {
		return byyyzll;
	}

	public void setByyyzll(String byyyzll) {
		this.byyyzll = byyyzll;
	}

	public String getTw() {
		return tw;
	}

	public void setTw(String tw) {
		this.tw = tw;
	}

	public String getTn() {
		return tn;
	}

	public void setTn(String tn) {
		this.tn = tn;
	}

	public String getJryy() {
		return jryy;
	}

	public void setJryy(String jryy) {
		this.jryy = jryy;
	}
}
