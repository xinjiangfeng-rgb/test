package com.xwtech.xwecp.service.logic.client;


import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;


public class LIInvocationContext
{
	private static final Logger logger = LoggerFactory.getLogger(LIInvocationContext.class);

	private static ThreadLocal<LIInvocationContext> context = new ThreadLocal<LIInvocationContext>();
	public static String USER;

	public static String PWD;

	protected String opType;

	protected String userMobile;

	protected String userBrand;

	protected String userCity;

	protected String bizCode;

	protected String operId;

	protected InvocationContext clientIC;

	private String globalReqSeqNo;

	private String tranceId;//链式id

	private String clientPort;//wap或者client端口号

	private String pageNum;//三统一推荐页面编码

	private String recNum;//三统一触点位编码

	private String serialNum;//三统一推荐结果获取流水

	private String jfserialNum;//经分推荐流水
	private String prodId;//产品id或者活动id

	//初始化渠道
	protected static void initialize(String user, String pwd)
	{
		USER = user;
		PWD = pwd;
	}

	public static LIInvocationContext getContext()
	{
		LIInvocationContext ci = context.get();
		if(ci == null)
		{
			ci = new LIInvocationContext();
			//全局流水号
			ci.setGlobalReqSeqNo("");
			context.set(ci);
		}
		return ci;
	}

	//删除当前线程局部变量的值
	public static void remove(){
		context.remove();
	}

	public String getBizCode()
	{
		return bizCode;
	}

	public void setBizCode(String bizCode)
	{
		this.bizCode = bizCode;
	}

	public InvocationContext getContextParameter()
	{
		return clientIC;
	}

	public void setContextParameter(InvocationContext contextParameter)
	{
		this.clientIC = contextParameter;
	}

	public String getOpType()
	{
		return opType;
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

	public String getTranceId() {
		return tranceId;
	}

	public void setTranceId(String tranceId) {
		this.tranceId = tranceId;
	}

	public String getClientPort() {
		return clientPort;
	}

	public void setClientPort(String clientPort) {
		this.clientPort = clientPort;
	}

	public String getUserMobile()
	{
		return userMobile;
	}

	public void setUserMobile(String userMobile)
	{
		this.userMobile = userMobile;
	}

	public String getOperId()
	{
		return operId;
	}

	public void setOperId(String operId)
	{
		this.operId = operId;
	}

	public static void main(String args[])
	{
		LIInvocationContext.initialize("zlbbq", "zlbbq99");
		for(int i = 0;i<5;i++)
		{
			new Thread("T->" + i){
				public void run()
				{
					while(true)
					{
						logger.info(this.getName() + " -> " + LIInvocationContext.getContext());
						try
						{
							Thread.sleep(2000);
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
					}
				}
			}.start();
		}
	}

	public String getGlobalReqSeqNo() {
		return globalReqSeqNo;
	}

	public void setGlobalReqSeqNo(String globalReqSeqNo) {
		this.globalReqSeqNo = globalReqSeqNo;
	}


	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getRecNum() {
		return recNum;
	}

	public void setRecNum(String recNum) {
		this.recNum = recNum;
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
