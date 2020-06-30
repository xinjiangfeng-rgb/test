package com.xwtech.xwecp.service.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LIException extends Exception
{
	private static final Logger logger = LoggerFactory.getLogger(LIException.class);
	
	public LIException()
	{		
	}
	
	public LIException(Exception e)
	{
		super(e);
	}
	
	public LIException(String s)
	{
		super(s);
	}
}
