package com.xwtech.xwecp.teletext.funcs;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ToOrderCode extends AbstractFunctionExecutor
{
	private static final Logger logger = LoggerFactory.getLogger(ToOrderCode.class);
	
	public ToOrderCode()
	{
		super("to_order_code");
	}
	
	public String execute(String parameter)
	{
		String code = "11";
		
		try
		{
			if ("1".equals(parameter))
			{
				code = "11";
			}
			else if ("2".equals(parameter))
			{
				code = "12";
			}
			else
			{
				code = "13";
			}
		}
		catch (Exception e)
		{
			logger.error("", e);
			code = "11";
		}
		
		return code;
	}
}
