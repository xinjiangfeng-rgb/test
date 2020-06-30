package com.xwtech.xwecp.teletext.funcs;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ChangeOprTypeFunctionExecutor extends AbstractFunctionExecutor
{
	private static final Logger logger = LoggerFactory.getLogger(ChangeOprTypeFunctionExecutor.class);
	
	public ChangeOprTypeFunctionExecutor()
	{
		super("change_oprType");
	}

	public String execute(String parameter)
	{
		String oprType = parameter;
		if ("2".equals(parameter))
		{
			oprType = "3";
		}
		else if ("3".equals(parameter))
		{
			oprType = "2";
		}
		
		return oprType;
	}
}
