package com.xwtech.xwecp.teletext.funcs;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.service.logic.pojo.BossParmDT;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BusinessCode2BossCode4FunctionExecutor extends AbstractFunctionExecutor
{
	private static final Logger logger = LoggerFactory.getLogger(BusinessCode2BossCode4FunctionExecutor.class);

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

	public BusinessCode2BossCode4FunctionExecutor()
	{
		super("to_boss_code4");
	}

	/**
	* 获取业务对应码3
	*/
	public String execute(String parameter)
	{
		try
		{
			if (!"".equals(parameter))
			{
				BossParmDT dt = this.wellFormedDAO.getBossParm(parameter);
				return dt.getParm4();
			}
		}
		catch (Exception e)
		{
			logger.error("", e);
		}

		return "";
	}
}
