package com.xwtech.xwecp.service.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class ServiceOutput
{
	private static final Logger logger = LoggerFactory.getLogger(ServiceOutput.class);
	
	private List<ServiceOutputField> outputFields = new ArrayList<ServiceOutputField>();

	public List<ServiceOutputField> getOutputFields()
	{
		return outputFields;
	}

	public void setOutputFields(List<ServiceOutputField> outputFields)
	{
		this.outputFields = outputFields;
	}
}
