package com.xwtech.xwecp.teletext.funcs;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ToOprTypeFunctionExecutor extends AbstractFunctionExecutor
{
	private static final Logger logger = LoggerFactory.getLogger(ToOprTypeFunctionExecutor.class);
	
		
	public ToOprTypeFunctionExecutor()
	{
		super("to_opr_type");
	}
	
	public String execute(String parameter)
	{
		String oprType = "";
		if ("1".equals(parameter))
		{
			oprType = "02";
		}
		else if ("2".equals(parameter))
		{
			oprType = "01";
		}
		return oprType;
	}
}
