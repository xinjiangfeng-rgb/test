package com.xwtech.xwecp.teletext.funcs;

import org.springframework.stereotype.Component;

@Component
public class ChooseFlagFunctionExecutor extends AbstractFunctionExecutor
{

	public ChooseFlagFunctionExecutor()
	{
		super("choose_flag");
	}

	public String execute(String parameter)
	{
		String chooseFlag = "";
		
		if ("1".equals(parameter))
		{
			chooseFlag = "0";
		}
		else if ("3".equals(parameter))
		{
			chooseFlag = "1";
		}
		return chooseFlag;
	}
}
