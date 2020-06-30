package com.xwtech.xwecp.service.logic.invocation;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.BossParmDT;
import com.xwtech.xwecp.service.logic.pojo.QRY040104Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.MessageUtil.Message;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QueryValidProductIdBussInvocation extends BaseInvocation implements
		ILogicalService {
	
	private static final Logger logger = LoggerFactory.getLogger(QueryValidProductIdBussInvocation.class);
	
	private BossTeletextUtil bossTeletextUtil;
	
	private IRemote remote;
	
	private Map<String, String> offerTypeMap = new HashMap<String, String>();

	private String REQUEST_BOSSTELETEXT_1 = "hnmcc_validproductid_200887";//订购新产品时查询生效方式
	
	public QueryValidProductIdBussInvocation()
	{
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		this.bossTeletextUtil = (BossTeletextUtil)(springCtx.getBean("bossTeletextUtil"));
		this.remote = (IRemote)(springCtx.getBean("bossRemote"));
		this.wellFormedDAO = (WellFormedDAO)(springCtx.getBean("wellFormedDAO"));
		//BASE（订购基本产品）	OTHER（订购增值产品）	DSMP（订购梦网产品）
		offerTypeMap.put("ZZCP", "OTHER");
		offerTypeMap.put("MWCP", "OTHER");
		offerTypeMap.put("FWB", "OTHER");
		offerTypeMap.put("JBFWB", "BASE");
		}
	
	
	public BaseServiceInvocationResult executeService(String accessId,ServiceConfig config, List<RequestParameter> params)
	{
		
		QRY040104Result result = new QRY040104Result();
		BossParmDT dt;
		String reqXml = "";
		String rspXml = "";
		try
		{
			dt = this.wellFormedDAO.getBossParm(this.getParameter(params, "busCode").getParameterValue().toString());
			if(dt!=null){
				if("JBFWB".equals(dt.getParm1())){
					params.add(new RequestParameter("offerType",offerTypeMap.get(dt.getParm1())));
					params.add(new RequestParameter("offerId",dt.getParm2()));
				}else{
					params.add(new RequestParameter("offerType",offerTypeMap.get(dt.getParm1())));
					params.add(new RequestParameter("offerId",dt.getParm2()));
				}
				
			}
			//订购新产品时查询生效方式
			reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT_1, params);
			rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT_1, this.generateCity(params)));			
		     if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120)
		      {
		            result.setBossCode(LOGIC_ERROR);
		            result.setErrorCode(LOGIC_ERROR);
		            return result;
		      }
		     Message bossMsg = MessageUtil.parse(rspXml);
		     if(BOSS_SUCCESS.equals(bossMsg.getHead().getCode())){
		    	 result.setResultCode(LOGIC_SUCESS);
			     result.setBossCode(bossMsg.getHead().getCode());
			     result.setErrorMessage(bossMsg.getHead().getDesc());	
			     String[] resArray = bossMsg.getBody().asArray();
			     if("2".equals(resArray[0])){
			    	 result.setValidType(resArray[0]);
				     result.setValidName("次月生效");
			     }else{
			    	 result.setValidType(resArray[0]);
				     result.setValidName(resArray[1]);
			     }
			     
		     }else{
		    	 result.setResultCode(LOGIC_ERROR);
			     result.setBossCode(bossMsg.getHead().getCode());
			     result.setErrorMessage(bossMsg.getHead().getDesc());	
		     }
		     		     
		       
		}
		catch (Exception e)
		{
			logger.error("", e);
		}
		return result;
	}
	
}
