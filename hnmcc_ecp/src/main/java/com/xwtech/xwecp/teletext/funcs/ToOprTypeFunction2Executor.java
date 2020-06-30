package com.xwtech.xwecp.teletext.funcs;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ToOprTypeFunction2Executor extends AbstractFunctionExecutor
{
	private static final Logger logger = LoggerFactory.getLogger(ToOprTypeFunction2Executor.class);
	
		
	public ToOprTypeFunction2Executor()
	{
		super("to_opr_type2");
	}
	
	public String execute(String parameter)
	{
		String oprType = "";
		if ("1".equals(parameter))
		{
			oprType = "01";
		}
		else if ("2".equals(parameter))
		{
			oprType = "02";
		}
		return oprType;
	}
}
