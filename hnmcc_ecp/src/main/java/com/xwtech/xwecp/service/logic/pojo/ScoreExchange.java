package com.xwtech.xwecp.service.logic.pojo;


/**
 * 积分M值兑换历史
 * @author Administrator
 *
 */
public class ScoreExchange
{
	/**
	 * 服务号码
	 */
	private String svcNum;
	/**
	 * 品牌
	 */
	private String brandId;
	/**
	 * 礼品名称
	 */
	private String giftName;
	/**
	 * 消费积分
	 */
	private String useScore;
	/**
	 * 操作类型
	 */
	private String exchangeType;
	/**
	 * 生产日期
	 */
	private String crtDate;
	/**
	 * 操作日期
	 */
	private String optDate;
	/**
	 * 操作员工号
	 */
	private String operID;
	/**
	 * 消费类型
	 */
	private String useType;
	/**
	 * 兑换渠道
	 */
	private String exchangeChannel;
	/**
	 * 兑换数量
	 */
	private String exchangeAmount;
	/**
	 * 销毁标识
	 */
	private String destroyFlag;
	
	private String exchangeTime;	

	private long exchangeScore;

	private long exchangeFee;
	

	public void setExchangeTime(String exchangeTime)
	{
		this.exchangeTime = exchangeTime;
	}

	public String getExchangeTime()
	{
		return this.exchangeTime;
	}

	public void setExchangeType(String exchangeType)
	{
		this.exchangeType = exchangeType;
	}

	public String getExchangeType()
	{
		return this.exchangeType;
	}

	public void setExchangeScore(long exchangeScore)
	{
		this.exchangeScore = exchangeScore;
	}

	public long getExchangeScore()
	{
		return this.exchangeScore;
	}

	public void setExchangeFee(long exchangeFee)
	{
		this.exchangeFee = exchangeFee;
	}

	public long getExchangeFee()
	{
		return this.exchangeFee;
	}

	public void setExchangeChannel(String exchangeChannel)
	{
		this.exchangeChannel = exchangeChannel;
	}

	public String getExchangeChannel()
	{
		return this.exchangeChannel;
	}

	public String getSvcNum() {
		return svcNum;
	}

	public void setSvcNum(String svcNum) {
		this.svcNum = svcNum;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public String getUseScore() {
		return useScore;
	}

	public void setUseScore(String useScore) {
		this.useScore = useScore;
	}

	public String getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}

	public String getOptDate() {
		return optDate;
	}

	public void setOptDate(String optDate) {
		this.optDate = optDate;
	}

	public String getOperID() {
		return operID;
	}

	public void setOperID(String operID) {
		this.operID = operID;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getExchangeAmount() {
		return exchangeAmount;
	}

	public void setExchangeAmount(String exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
	}

	@Override
	public String toString() {
		return "ScoreExchange [svcNum=" + svcNum + ", brandId=" + brandId
				+ ", giftName=" + giftName + ", useScore=" + useScore
				+ ", exchangeType=" + exchangeType + ", crtDate=" + crtDate
				+ ", optDate=" + optDate + ", operID=" + operID + ", useType="
				+ useType + ", exchangeChannel=" + exchangeChannel
				+ ", exchangeAmount=" + exchangeAmount + "]";
	}
	

}