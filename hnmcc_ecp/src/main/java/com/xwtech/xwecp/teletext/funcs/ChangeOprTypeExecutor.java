package com.xwtech.xwecp.teletext.funcs;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 业务赠送 转换oprType操作类型
 * @author yuantao
 * 2010-01-26
 */
@Component
public class ChangeOprTypeExecutor extends AbstractFunctionExecutor
{
	private static final Logger logger = LoggerFactory.getLogger(ToOprTypeExecutor.class);
	
	public ChangeOprTypeExecutor ()
	{
		super("changeOprType");
	}
	
	public String execute(String parameter)
	{
		String str = "";
		
		try
		{
			if (null != parameter && !"".equals(parameter))
			{
				if ("0".equals(parameter))  //取消赠送
				{
					str = "02";
				}
				else if ("1".equals(parameter))  //赠送
				{
					str = "01";
				}
			}
		}
		catch (Exception e)
		{
			logger.error("", e);
		}
		
		return str;
	}
}
