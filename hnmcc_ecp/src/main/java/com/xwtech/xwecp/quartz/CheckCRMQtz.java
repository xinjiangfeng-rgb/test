package com.xwtech.xwecp.quartz;

import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.ICheckCRMLoginService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.CheckCRMLoginClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY040103Result;
/**
 * CRM认证任务 此任务未启用
 * @author Administrator
 *
 */
public class CheckCRMQtz extends QuartzJobBean {

	private static Logger logger = LoggerFactory.getLogger(CheckCRMQtz.class);
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("--------------CheckCRMQtz start");
		logger.info(context.getMergedJobDataMap().get("clientchannel").toString());
		logger.info(context.getMergedJobDataMap().get("platformurl").toString());
		logger.info(context.getMergedJobDataMap().get("platformuser").toString());
		logger.info(context.getMergedJobDataMap().get("platformpwd").toString());
        // 初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", String.valueOf(context.getMergedJobDataMap().get("clientchannel")));
		props.put("platform.url", String.valueOf(context.getMergedJobDataMap().get("platformurl")));
		props.put("platform.user", String.valueOf(context.getMergedJobDataMap().get("platformuser")));
		props.put("platform.password", String.valueOf(context.getMergedJobDataMap().get("platformpwd")));
        
        XWECPLIClient client = XWECPLIClient.createInstance(props);
        // 逻辑接口调用片段
        ICheckCRMLoginService co = new CheckCRMLoginClientImpl();
        QRY040103Result re;
		try {
			re = co.sendLoginRequest();
			logger.info(" ====== 开始返回参数 ======");
	        if (re != null)
	        {
	            logger.info(" === CRM认证: === " +re.getBossCode()+"|"+re.getErrorMessage());
	        }
	        logger.info(" === CRM认证结束 === ");
		} catch (LIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("CRM认证异常:"+e.getMessage());
		}
        

	}

}
