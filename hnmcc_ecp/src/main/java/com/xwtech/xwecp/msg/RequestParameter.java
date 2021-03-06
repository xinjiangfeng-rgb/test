package com.xwtech.xwecp.msg;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RequestParameter
{
	private static final Logger logger = LoggerFactory.getLogger(RequestParameter.class);
	
	private String parameterName;
	
	private Object parameterValue;
	
	public RequestParameter()
	{
		
	}
	
	public RequestParameter(String name, Object value)
	{
		this.parameterName = name;
		this.parameterValue = value;
	}

	public String getParameterName()
	{
		return parameterName;
	}

	public void setParameterName(String parameterName)
	{
		this.parameterName = parameterName;
	}

	public Object getParameterValue()
	{
		return parameterValue;
	}

	public void setParameterValue(Object parameterValue)
	{
		this.parameterValue = parameterValue;
	}
}
