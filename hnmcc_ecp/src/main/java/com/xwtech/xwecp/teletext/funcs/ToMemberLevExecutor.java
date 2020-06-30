package com.xwtech.xwecp.teletext.funcs;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 转换会员级别
 * @author yuantao
 *
 */
@Component
public class ToMemberLevExecutor extends AbstractFunctionExecutor
{
	private static final Logger logger = LoggerFactory.getLogger(ToMemberLevExecutor.class);
	
	public ToMemberLevExecutor()
	{
		super("to_member_lev");
	}
	
	public String execute(String parameter)
	{
		String lev = "1";
		
		try
		{
			return "WXYYJLB_PTHY".equals(parameter)?"1":"2";
		}
		catch (Exception e)
		{
			logger.error("", e);
			lev = "1";
		}
		
		return lev;
	}
}
