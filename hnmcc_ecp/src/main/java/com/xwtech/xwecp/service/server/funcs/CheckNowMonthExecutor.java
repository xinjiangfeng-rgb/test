package com.xwtech.xwecp.service.server.funcs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.teletext.funcs.AbstractFunctionExecutor;
import org.springframework.stereotype.Component;

/**
 * 判断转入参数是否是当前的月份，如果是则返回“now”，如果不是则返回“othor”
 * @author 邵琪
 *
 */
@Component
public class CheckNowMonthExecutor extends AbstractFunctionExecutor 
{
	private static final Logger logger = LoggerFactory.getLogger(CheckNowMonthExecutor.class);
	
	public CheckNowMonthExecutor()
	{
		super("check_now_month");
	}
	
	public String execute(String parameter)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String nowMonth = sdf.format(new Date());
		String rtnValue;
		if(nowMonth.equals(parameter)){
			rtnValue = "now";
		}else{
			rtnValue = "other";
		}
		return rtnValue;
	}
}
