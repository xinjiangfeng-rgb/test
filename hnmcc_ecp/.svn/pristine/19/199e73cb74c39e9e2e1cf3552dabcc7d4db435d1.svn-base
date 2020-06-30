package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY170421Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;

public class QueryAccountInvocation extends BaseInvocation implements ILogicalService {

	private static final Logger logger = LoggerFactory.getLogger(QueryAccountInvocation.class);
	
	private BossTeletextUtil bossTeletextUtil;
	
	private IRemote remote;
	//查询已订购梦网产品
	private String REQUEST_BOSSTELETEXT_2 = "cc_queryAccount";
	
	public QueryAccountInvocation()
	{
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		this.bossTeletextUtil = (BossTeletextUtil)(springCtx.getBean("bossTeletextUtil"));
		this.remote = (IRemote)(springCtx.getBean("bossRemote"));
		this.wellFormedDAO = (WellFormedDAO)(springCtx.getBean("wellFormedDAO"));
		}
	
	
	
	@Override
	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
			List<RequestParameter> params) {
		
		QRY170421Result ret = new QRY170421Result();
		try{
			Map<String, Object> remotingMap = this.bossTeletextUtil.mergeRemoteTeletext(REQUEST_BOSSTELETEXT_2, params);
			String sysParam = "";
			String busiParam = "";
			String type = StringTeletext.DEFAULT_REQ_TYPE;
			if(null!=remotingMap){
				sysParam = remotingMap.get("sysParam")==null?"":(String)remotingMap.get("sysParam");
				busiParam = remotingMap.get("busiParam")==null?"":(String)remotingMap.get("busiParam");
				type = remotingMap.get("type")==null?"0":(String)remotingMap.get("type");
			}
			String headTraceId = "";
			String headUserMobile = "";
			String headUserBrand = "";
			String headUserCity = "";
			String headPageNum = "";
			String headRecNum = "";
			String headSerialNum = "";
			String headJfserialNum = "";
			String headProdId = "";
			if (null != params && 0 < params.size()) {
				for (RequestParameter parameter : params) {
					String paramName = parameter.getParameterName();
					if ("header_traceId".equals(paramName)) {
						headTraceId = (String)parameter.getParameterValue();
					}else if ("header_usermobile".equals(paramName)) {
						headUserMobile = (String)parameter.getParameterValue();
					}else if ("header_userbrand".equals(paramName)) {
						headUserBrand = (String)parameter.getParameterValue();
					}else if ("header_usercity".equals(paramName)) {
						headUserCity = (String)parameter.getParameterValue();
					}else if ("header_pagenum".equals(paramName)) {
						headPageNum = (String) parameter.getParameterValue();
					} else if ("header_recnum".equals(paramName)) {
						headRecNum = (String) parameter.getParameterValue();
					} else if ("header_serialnum".equals(paramName)) {
						headSerialNum = (String) parameter.getParameterValue();
					} else if ("header_jfserialnum".equals(paramName)) {
						headJfserialNum = (String) parameter.getParameterValue();
					}else if ("header_prodId".equals(paramName)) {
						headProdId = (String)parameter.getParameterValue();
					}
				}
			}
			headTraceId = headTraceId == null ? "" : headTraceId;
			headUserMobile = headUserMobile == null ? "" : headUserMobile;
			headUserBrand = headUserBrand == null ? "" : headUserBrand;
			headUserCity = headUserCity == null ? "" : headUserCity;

			headPageNum = headPageNum == null ? "" : headPageNum;
			headRecNum = headRecNum == null ? "" : headRecNum;
			headSerialNum = headSerialNum == null ? "" : headSerialNum;
			headJfserialNum = headJfserialNum == null ? "" : headJfserialNum;
			headProdId = headProdId == null ? "" : headProdId;
			String rspStr = (String) this.remote.callRemote(new StringTeletext(sysParam,busiParam,type,"", accessId,
					REQUEST_BOSSTELETEXT_2, this.generateCity(params),headTraceId,headUserMobile,headUserBrand,headUserCity,
					headPageNum,headRecNum,headSerialNum,headJfserialNum,headProdId));
			
			//能力开放平台
			if(StringTeletext.REMOTING_REQ_TYPE.equals(type)){
				if (StringUtils.isEmpty(rspStr)){
					ret.setBossCode(LOGIC_ERROR);
					ret.setErrorCode(LOGIC_ERROR);
					return ret;
				}
				MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) rspStr);
				String bossCode = bossJson.getBossCode();
				String bossDesc = bossJson.getBossDesc();
				if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)){
					ret.setBossCode(LOGIC_ERROR);
					ret.setErrorCode(LOGIC_ERROR);
					return ret;
				}

				JSONObject json = bossJson.getResult();
				ret.setAccName(json.getString("ACCNAME"));
				ret.setRoleType(json.getString("ROLETYPE"));
				ret.setResultCode(MessageJsonUtil.LOGIC_SUCESS);
				ret.setBossCode(bossCode);
				ret.setErrorMessage(StringUtils.isNotBlank(bossDesc)?bossDesc:bossJson.getStringResult());
				return ret;
			}else{
				return ret;
				
			}
		}catch(Exception e){
			logger.error("", e);
			return ret;
		}
		
	}

}
