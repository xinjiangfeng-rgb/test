package com.xwtech.xwecp.service.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidConfigException extends Exception
{
	private static final Logger logger = LoggerFactory.getLogger(InvalidConfigException.class);

	public InvalidConfigException()
	{
	}

	public InvalidConfigException(String s)
	{
		super(s);
	}

	public InvalidConfigException(Exception e)
	{
		super(e);
	}
}
