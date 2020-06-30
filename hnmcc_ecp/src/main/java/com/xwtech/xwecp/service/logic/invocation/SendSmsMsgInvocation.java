package com.xwtech.xwecp.service.logic.invocation;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY080001Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * 短消息发送
 * @author 吴宗德
 * 
 */

public class SendSmsMsgInvocation extends BaseInvocation implements ILogicalService
{
	private static final Logger logger = LoggerFactory.getLogger(SendSmsMsgInvocation.class);
	
	 /**
     * 请求Boss的接口
     */
    private static final String REQUEST_BOSSTELETEXT = "cc_sendSmsMessage";
    
    private BossTeletextUtil bossTeletextUtil = null;
    
    private IRemote remote = null;
    
    
	public SendSmsMsgInvocation()
	{
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
        this.bossTeletextUtil = (BossTeletextUtil)(springCtx.getBean("bossTeletextUtil"));
        this.remote = (IRemote)(springCtx.getBean("bossRemote"));
	}
	
	public BaseServiceInvocationResult executeService(String accessId, 
			                ServiceConfig config, List<RequestParameter> params)
	{
		QRY080001Result result = new QRY080001Result();
		result.setResultCode(LOGIC_ERROR);
		result.setErrorMessage("");
		String reqXml = bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT, params);
		try
		{
//			String mobile = (String)this.getParameters(params, "mobile");
//			String content = (String)this.getParameters(params, "content");
//			String spCode = (String)this.getParameters(params, "spCode");
//			String serviceId = (String)this.getParameters(params, "serviceId");
			String rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT,
	                this.generateCity(params)));
	            
	            if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120)
	            {
	                result.setBossCode(LOGIC_ERROR);
	                result.setErrorCode(LOGIC_ERROR);
	                return result;
	            }
	            
	            String errCode = new String(rspXml.getBytes(), 66, 4);
	            String errDesc = new String(rspXml.getBytes(), 70, 42);
	            
	            result.setResultCode(BOSS_SUCCESS.equals(errCode) ? LOGIC_SUCESS : LOGIC_ERROR);
	            result.setBossCode(errCode);
	            result.setErrorMessage(StringUtils.trim(errDesc));
			
		}
		catch (Exception e)
		{
			logger.error("", e);
			result.setErrorCode(LOGIC_EXCEPTION);
		}
		
		return result;
	}
	
}
