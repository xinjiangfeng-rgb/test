package com.xwtech.xwecp.teletext.funcs;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 切换操作类型
 * @author 吴宗德
 * 2010-01-28
 */
@Component
public class ToOprType2Executor extends AbstractFunctionExecutor
{
	private static final Logger logger = LoggerFactory.getLogger(ToOprType2Executor.class);
	
	public ToOprType2Executor ()
	{
		super("to_opr_type2");
	}
	
	public String execute(String parameter)
	{
		String str = "";
		
		if (null != parameter && !"".equals(parameter))
		{
			//订购
			if ("1".equals(parameter))
			{
				str = "06";
			}  //退订
			else if ("2".equals(parameter))
			{
				str = "07";
			}
		}
		
		return str;
	}
}
