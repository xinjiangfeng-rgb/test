package com.xwtech.xwecp.teletext.funcs;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ChangeOprTypeFunctionTSBExecutor extends AbstractFunctionExecutor
{
	private static final Logger logger = LoggerFactory .getLogger(ChangeOprTypeFunctionTSBExecutor.class);
	
	public ChangeOprTypeFunctionTSBExecutor()
	{
		super("change_oprType");
	}

	public String execute(String parameter)
	{
		String oprType = parameter;
		if ("2".equals(parameter))
		{
			oprType = "0";
		}
		
		return oprType;
	}
}
