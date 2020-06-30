package com.xwtech.xwecp.service.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class ServiceInput
{
	private static final Logger logger = LoggerFactory.getLogger(ServiceInput.class);
	
	private List<ServiceInputParameter> params = new ArrayList<ServiceInputParameter>();

	public List<ServiceInputParameter> getParams()
	{
		return params;
	}

	public void setParams(List<ServiceInputParameter> params)
	{
		this.params = params;
	}
}
