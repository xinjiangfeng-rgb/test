package com.xwtech.xwecp.service.logic.pojo;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;

/**
 * MarketingCommunicationServiceResult<br/>
 * M + C + S + Result=MCSResult
 * 
 * @author SongWantao
 * @version 20170323
 *
 */
public class MCSResult extends BaseServiceInvocationResult {
	private static final long serialVersionUID = 1L;
	private String Message;// 营销活动
	private String MessageSn;// 推荐序列号
	private String PromptId;// 营销用语ID
	private String Title;// 推荐标题
	private String CatalogId;// 传播类别
	private String CatalogName;// 传播类别名称
	private String ImageUrl;// 图片文件名
	private String TargetUrl;// 目标URL
	private String Introduction;// 内容介绍
	private String OfferId;// 内容关联的产品OfferID
	private String SmsPrompt;// 短信营销语
	private String OrderNo;// 排序号
	private String EventProperty;// 事件扩展属性
	private String PropertyName;// 扩展属性名
	private String PropertyValue;// 扩展属性值
	private String HasSendSms;// 是否发送过短信
	private String respCode;// 结果代码
	private String respDesc;// 结果说明
	private String result;// 返回结果

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getMessageSn() {
		return MessageSn;
	}

	public void setMessageSn(String messageSn) {
		MessageSn = messageSn;
	}

	public String getPromptId() {
		return PromptId;
	}

	public void setPromptId(String promptId) {
		PromptId = promptId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getCatalogId() {
		return CatalogId;
	}

	public void setCatalogId(String catalogId) {
		CatalogId = catalogId;
	}

	public String getCatalogName() {
		return CatalogName;
	}

	public void setCatalogName(String catalogName) {
		CatalogName = catalogName;
	}

	public String getImageUrl() {
		return ImageUrl;
	}

	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}

	public String getTargetUrl() {
		return TargetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		TargetUrl = targetUrl;
	}

	public String getIntroduction() {
		return Introduction;
	}

	public void setIntroduction(String introduction) {
		Introduction = introduction;
	}

	public String getOfferId() {
		return OfferId;
	}

	public void setOfferId(String offerId) {
		OfferId = offerId;
	}

	public String getSmsPrompt() {
		return SmsPrompt;
	}

	public void setSmsPrompt(String smsPrompt) {
		SmsPrompt = smsPrompt;
	}

	public String getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}

	public String getEventProperty() {
		return EventProperty;
	}

	public void setEventProperty(String eventProperty) {
		EventProperty = eventProperty;
	}

	public String getPropertyName() {
		return PropertyName;
	}

	public void setPropertyName(String propertyName) {
		PropertyName = propertyName;
	}

	public String getPropertyValue() {
		return PropertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		PropertyValue = propertyValue;
	}

	public String getHasSendSms() {
		return HasSendSms;
	}

	public void setHasSendSms(String hasSendSms) {
		HasSendSms = hasSendSms;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}