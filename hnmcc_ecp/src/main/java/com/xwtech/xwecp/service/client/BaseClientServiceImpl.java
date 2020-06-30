package com.xwtech.xwecp.service.client;

import com.xwtech.xwecp.communication.IRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseClientServiceImpl
{
	private static final Logger logger = LoggerFactory.getLogger(BaseClientServiceImpl.class);
	
	private IRemote remote;

	public IRemote getRemote()
	{
		return remote;
	}

	public void setRemote(IRemote remote)
	{
		this.remote = remote;
	}
}
