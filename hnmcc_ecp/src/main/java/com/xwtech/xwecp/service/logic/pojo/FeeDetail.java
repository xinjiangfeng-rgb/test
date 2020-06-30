package com.xwtech.xwecp.service.logic.pojo;


public class FeeDetail
{
	private Integer level;

	private String feeTypeId;

	private Integer showChild;

	private String prefeeTypeId;

	private String feeName;

	private String fee;

	private Long disc;
	


	public void setLevel(Integer level)
	{
		this.level = level;
	}

	public Integer getLevel()
	{
		return this.level;
	}

	public void setFeeTypeId(String feeTypeId)
	{
		this.feeTypeId = feeTypeId;
	}

	public String getFeeTypeId()
	{
		return this.feeTypeId;
	}

	public void setShowChild(Integer showChild)
	{
		this.showChild = showChild;
	}

	public Integer getShowChild()
	{
		return this.showChild;
	}

	public void setPrefeeTypeId(String prefeeTypeId)
	{
		this.prefeeTypeId = prefeeTypeId;
	}

	public String getPrefeeTypeId()
	{
		return this.prefeeTypeId;
	}

	public void setFeeName(String feeName)
	{
		this.feeName = feeName;
	}

	public String getFeeName()
	{
		return this.feeName;
	}


	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public void setDisc(Long disc)
	{
		this.disc = disc;
	}

	public Long getDisc()
	{
		return this.disc;
	}

}