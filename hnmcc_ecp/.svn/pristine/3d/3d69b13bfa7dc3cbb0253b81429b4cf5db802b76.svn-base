package com.xwtech.xwecp.teletext.funcs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.teletext.IExternalFunctionExecutor;

public abstract class AbstractFunctionExecutor implements IExternalFunctionExecutor
{
	private static final Logger logger = LoggerFactory.getLogger(AbstractFunctionExecutor.class);

	protected String functionName;

	protected AbstractFunctionExecutor(String functionName)
	{
		this.functionName = functionName;
	}

	public String getFunctionName()
	{
		return functionName;
	}

	public void setFunctionName(String functionName)
	{
		this.functionName = functionName;
	}

}
