package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

public class DEL010021Result extends BaseServiceInvocationResult
{
	/***
	 * 产品ID
	 */
	private String offerId;
	/***
	 * 是否能够办理 1：能办理   2：不能办理
	 */
	private String canSel;
	/***
	 * 不能办理的原因
	 */
	private String errMsg;
	/***
	 * 活动费用
	 */
	private String selFel;
	/***
	 * 活动编码
	 */
	private String offerCode;
	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	public String getCanSel() {
		return canSel;
	}
	public void setCanSel(String canSel) {
		this.canSel = canSel;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getSelFel() {
		return selFel;
	}
	public void setSelFel(String selFel) {
		this.selFel = selFel;
	}
	public String getOfferCode() {
		return offerCode;
	}
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

}