package com.xwtech.xwecp.service.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class ServiceExtension
{
	private static final Logger logger = LoggerFactory.getLogger(ServiceExtension.class);
	
	private List<ExtensionClassInfo> extensions = new ArrayList<ExtensionClassInfo>();

	public List<ExtensionClassInfo> getExtensions()
	{
		return extensions;
	}

	public void setExtensions(List<ExtensionClassInfo> extensions)
	{
		this.extensions = extensions;
	}
}
