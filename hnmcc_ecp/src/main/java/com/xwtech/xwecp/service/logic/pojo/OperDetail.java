package com.xwtech.xwecp.service.logic.pojo;


public class OperDetail
{
	/**
	 * 办理时间
	 */
	private String oprTime;

	private String oprBiz;
	/**
	 * 业务名称
	 */
	private String oprChannel;
	/**
	 * 费用
	 */
	private String formNum;

	public String getFormNum() {
		return formNum;
	}

	public void setFormNum(String formNum) {
		this.formNum = formNum;
	}

	public void setOprTime(String oprTime)
	{
		this.oprTime = oprTime;
	}

	public String getOprTime()
	{
		return this.oprTime;
	}

	public void setOprBiz(String oprBiz)
	{
		this.oprBiz = oprBiz;
	}

	public String getOprBiz()
	{
		return this.oprBiz;
	}

	public void setOprChannel(String oprChannel)
	{
		this.oprChannel = oprChannel;
	}

	public String getOprChannel()
	{
		return this.oprChannel;
	}

}