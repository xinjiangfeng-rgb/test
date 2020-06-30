package com.xwtech.xwecp.service.logic.pojo;


public class RecommendDetail3 {
	private String campId;
	private String campSn;
	private String messageSn;
	private String offerId;
	private String orderNo;
	private String productCode;
	private String productName;
	private String promptId;
	private String promptSn;


	public String getCampId() {
		return campId;
	}

	public void setCampId(String campId) {
		this.campId = campId;
	}

	public String getCampSn() {
		return campSn;
	}

	public void setCampSn(String campSn) {
		this.campSn = campSn;
	}

	public String getMessageSn() {
		return messageSn;
	}

	public void setMessageSn(String messageSn) {
		this.messageSn = messageSn;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPromptId() {
		return promptId;
	}

	public void setPromptId(String promptId) {
		this.promptId = promptId;
	}

	public String getPromptSn() {
		return promptSn;
	}

	public void setPromptSn(String promptSn) {
		this.promptSn = promptSn;
	}

	@Override
	public String toString() {
		return "RecommendDetail3{" +
				"campId='" + campId + '\'' +
				", campSn='" + campSn + '\'' +
				", messageSn='" + messageSn + '\'' +
				", offerId='" + offerId + '\'' +
				", orderNo='" + orderNo + '\'' +
				", productCode='" + productCode + '\'' +
				", productName='" + productName + '\'' +
				", promptId='" + promptId + '\'' +
				", promptSn='" + promptSn + '\'' +
				'}';
	}
}