package com.xwtech.xwecp.service.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ServiceInputParameter extends BeanFieldInfo
{
	private static final Logger logger = LoggerFactory.getLogger(ServiceInputParameter.class);
	
	private int index;
	
	private String regular;

	public String getRegular()
	{
		return regular;
	}

	public void setRegular(String regular)
	{
		this.regular = regular;
	}

	public int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		this.index = index;
	}
}
