package com.xwtech.xwecp.service;


import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.dao.service.IServiceConfigDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.server.SocketServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;



@Service
public class ServiceLocator
{
	private static final Logger logger = LoggerFactory.getLogger(ServiceLocator.class);

	@Resource(name = "serviceConfigDAO")
	private IServiceConfigDAO serviceConfigDAO;
	
	public IServiceConfigDAO getServiceConfigDAO()
	{
		return serviceConfigDAO;
	}

	public void setServiceConfigDAO(IServiceConfigDAO serviceConfigDAO)
	{
		this.serviceConfigDAO = serviceConfigDAO;
	}



	/**
	 * 定位ServiceInfo
	 * @param cmd
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public ServiceInfo locate(String cmd, List<RequestParameter> params) throws ServiceException
	{
		ServiceInfo info = null;
		
		ServiceConfig config = serviceConfigDAO.getServiceConfig(cmd);
		if(config == null)
		{
			throw new ServiceException("找不到逻辑接口配置[cmd="+cmd+"]!");
		}
        IService service = (IService) XWECPApp.SPRING_CONTEXT.getBean(SocketServiceImpl.class);
        logger.debug("SocketServiceImpl hash :" + service.hashCode());
        service.initialize(config, params);
		info = new ServiceInfo();
		info.setServiceInstance(service);
		
		return info;
	}
}
