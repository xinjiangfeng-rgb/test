package com.xwtech.xwecp.communication;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommunicateException extends Exception
{
	private static final Logger logger = LoggerFactory.getLogger(CommunicateException.class);

	public CommunicateException()
	{
	}

	public CommunicateException(Exception e)
	{
		super(e);
	}

	public CommunicateException(String s)
	{
		super(s);
	}
}
