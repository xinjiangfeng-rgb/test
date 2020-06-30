package com.xwtech.xwecp.msg;


import org.slf4j.Logger;import org.slf4j.LoggerFactory;


public class MessageHead
{
	private static final Logger logger = LoggerFactory.getLogger(MessageHead.class);
	
	private String cmd;
	
	private String sequence;
	
	private String clientTime;
	
	private String serverTime;
	
	private String type;
	
	private String channel;
	
	private String user;
	
	private String pwd;
	
	private String opType;
	
	private String userMobile;
	
	private String userBrand;
	
	private String userCity;
	
	private String bizCode;
	
	private String operId;

	private String TranceId;

	private String clientPort;

	private String pageNum;//三统一推荐页面编码

	private String recNum;//三统一触点位编码

	private String serialNum;//三统一推荐结果获取流水

	private String jfserialNum;//经分推荐流水

	private String prodId;//活动id

	public String getClientPort() {
		return clientPort;
	}

	public void setClientPort(String clientPort) {
		this.clientPort = clientPort;
	}

	public String getTranceId() {
		return TranceId;
	}

	public void setTranceId(String tranceId) {
		TranceId = tranceId;
	}

	public String getBizCode()
	{
		return bizCode;
	}

	public void setBizCode(String bizCode)
	{
		this.bizCode = bizCode;
	}

	public String getOpType()
	{
		return opType;
	}

	public String getOperId()
	{
		return operId;
	}

	public void setOperId(String operId)
	{
		this.operId = operId;
	}

	public void setOpType(String opType)
	{
		this.opType = opType;
	}

	public String getUserBrand()
	{
		return userBrand;
	}

	public void setUserBrand(String userBrand)
	{
		this.userBrand = userBrand;
	}

	public String getUserCity()
	{
		return userCity;
	}

	public void setUserCity(String userCity)
	{
		this.userCity = userCity;
	}

	public String getUserMobile()
	{
		return userMobile;
	}

	public void setUserMobile(String userMobile)
	{
		this.userMobile = userMobile;
	}

	public String getChannel()
	{
		return channel;
	}

	public void setChannel(String channel)
	{
		this.channel = channel;
	}

	public String getClientTime()
	{
		return clientTime;
	}

	public void setClientTime(String clientTime)
	{
		this.clientTime = clientTime;
	}

	public String getCmd()
	{
		return cmd;
	}

	public void setCmd(String cmd)
	{
		this.cmd = cmd;
	}

	public String getSequence()
	{
		return sequence;
	}

	public void setSequence(String sequence)
	{
		this.sequence = sequence;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getServerTime()
	{
		return serverTime;
	}

	public void setServerTime(String serverTime)
	{
		this.serverTime = serverTime;
	}

	public String getPwd()
	{
		return pwd;
	}

	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getRecNum() {
		return recNum;
	}

	public void setRecNum(String recNum) {
		this.recNum = recNum;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getJfserialNum() {
		return jfserialNum;
	}

	public void setJfserialNum(String jfserialNum) {
		this.jfserialNum = jfserialNum;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
}
