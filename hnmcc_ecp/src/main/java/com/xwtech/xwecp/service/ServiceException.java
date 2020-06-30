package com.xwtech.xwecp.service;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

public class ServiceException extends Exception
{
	private static final Logger logger = LoggerFactory.getLogger(ServiceException.class);
	
	public ServiceException()
	{
	}
	
	public ServiceException(Exception e)
	{
		super(e);
	}
	
	public ServiceException(String s)
	{
		super(s);
	}
}
