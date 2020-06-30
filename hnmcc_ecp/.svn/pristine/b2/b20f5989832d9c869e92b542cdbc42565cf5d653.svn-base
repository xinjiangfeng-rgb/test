package com.xwtech.xwecp.teletext.funcs;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.service.logic.pojo.BossParmDT;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BusinessCode2BossCode2FunctionExecutor extends AbstractFunctionExecutor
{
	private static final Logger logger = LoggerFactory.getLogger(BusinessCode2BossCode2FunctionExecutor.class);

	@Resource(name = "wellFormedDAO")
	private WellFormedDAO wellFormedDAO;
	
	public WellFormedDAO getWellFormedDAO()
	{
		return wellFormedDAO;
	}
	
	public void setWellFormedDAO(WellFormedDAO wellFormedDAO)
	{
		this.wellFormedDAO = wellFormedDAO;
	}
	
	public BusinessCode2BossCode2FunctionExecutor()
	{
		super("to_boss_code2");
	}
	
	/**
	 * 获取业务对应码2
	 */
	public String execute(String parameter)
	{
		try
		{
			if (!"".equals(parameter))
			{
				BossParmDT dt = this.wellFormedDAO.getBossParm(parameter);
				return dt.getParm2();
			}
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
		}
		
		return "";
	}
}
