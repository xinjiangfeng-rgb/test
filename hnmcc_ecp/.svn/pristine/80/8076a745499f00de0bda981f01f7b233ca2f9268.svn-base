package com.xwtech.xwecp.service.server.funcs;


import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.teletext.funcs.AbstractFunctionExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BusinessCode2BusinessTypeExecutor2 extends AbstractFunctionExecutor 
{
	private static final Logger logger = LoggerFactory.getLogger(BusinessCode2BusinessTypeExecutor2.class);


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
	
	public BusinessCode2BusinessTypeExecutor2()
	{
		super("qry_business_typeof");
	}
	
	public String execute(String parameter)
	{
		String busiType = "";
		
		try
		{
			busiType = this.wellFormedDAO.getBusiParm(parameter, "QRY050026", "");
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
		}
		//logger.info(" ====== busiType ====== " + busiType);
		return busiType;
	}
}
