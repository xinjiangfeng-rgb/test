package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.BossParmDT;
import com.xwtech.xwecp.service.logic.pojo.DEL010001Result;
import com.xwtech.xwecp.service.logic.pojo.GommonBusiness;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.MessageJsonUtil;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.MessageUtil.Message;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DelCancelMWCPBussInvocation extends BaseInvocation implements
		ILogicalService {
	
	private static final Logger logger = LoggerFactory.getLogger(DelCancelMWCPBussInvocation.class);
	
	private BossTeletextUtil bossTeletextUtil;
	
	private IRemote remote;
	//取消新客户端业务列表中不存在的梦网产品
	private String REQUEST_BOSSTELETEXT_1 = "hnmcc_cancel_mwcp_01";
	//查询已订购梦网产品
	private String REQUEST_BOSSTELETEXT_2 = "ac_alreadymwcp_100801";
	
	public DelCancelMWCPBussInvocation()
	{
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		this.bossTeletextUtil = (BossTeletextUtil)(springCtx.getBean("bossTeletextUtil"));
		this.remote = (IRemote)(springCtx.getBean("bossRemote"));
		this.wellFormedDAO = (WellFormedDAO)(springCtx.getBean("wellFormedDAO"));
		}
	
	
	public BaseServiceInvocationResult executeService(String accessId,ServiceConfig config, List<RequestParameter> params)
	{
		
		DEL010001Result result = new DEL010001Result();
		List<GommonBusiness> gbList = new ArrayList<GommonBusiness>();
		BossParmDT dt;
		String reqXml = "";
		String rspXml = "";
		String busName = "";
		try
		{
			gbList = queryAlreadyBus(accessId, config, params);
			if(gbList!=null && gbList.size()>0){
				busName = String.valueOf(this.getParameter(params, "reserve1").getParameterValue());
				if(StringUtils.isNotBlank(busName)){
					for(GommonBusiness gb:gbList){
						//设置BOSS报文中SP信息
						if(busName.equals(gb.getName())){
							params.add(new RequestParameter("spId",gb.getJtvwId()));
							params.add(new RequestParameter("spBizCode",gb.getReserve1()));
							params.add(new RequestParameter("bizType",gb.getReserve2()));
						}
					}
				}
			}
			//取消新客户端业务列表中不存在的梦网产品
			reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT_1, params);
			rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT_1, this.generateCity(params)));			
		     if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120)
		      {
		            result.setBossCode(LOGIC_ERROR);
		            result.setErrorCode(LOGIC_ERROR);
		            return result;
		      }		         
		     Message bossMsg = MessageUtil.parse(rspXml);	            
		     result.setResultCode(BOSS_SUCCESS.equals(bossMsg.getHead().getCode()) ? LOGIC_SUCESS : LOGIC_ERROR);
		     result.setBossCode(bossMsg.getHead().getCode());
		     result.setErrorMessage(bossMsg.getHead().getDesc());			     
		       
		}
		catch (Exception e)
		{
			logger.error("", e);
		}
		return result;
	}
	
	private List<GommonBusiness> queryAlreadyBus(String accessId,ServiceConfig config, List<RequestParameter> params){
		List<GommonBusiness> resList = new ArrayList<GommonBusiness>();
		GommonBusiness gb;
		String reqXml = "";
		String rspXml = "";
		try{
			//查询已开梦网产品
			reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT_2, params);

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
			//rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT_2, this.generateCity(params)));

			rspXml = (String)this.remote.callRemote(new StringTeletext(sysParam,busiParam,type,reqXml, accessId,
					REQUEST_BOSSTELETEXT_2, this.generateCity(params),headTraceId,headUserMobile,headUserBrand,headUserCity,
					headPageNum,headRecNum,headSerialNum,headJfserialNum,headProdId));

			if(StringTeletext.DEFAULT_REQ_TYPE.equals(type)){
				logger.info("******** Boss返回数据为*****　" + rspXml);
				if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120)
				{
					return resList;
				}
				Message bossMsg = MessageUtil.parse(rspXml);
				if(!BOSS_SUCCESS.equals(bossMsg.getHead().getCode()) || StringUtils.isBlank(bossMsg.getBody().toString())){
					return resList;
				}

				List<String[]> resArray = bossMsg.getBody().asList();
				if(resArray !=null){
					for(String[] temp:resArray){

						gb = new GommonBusiness();
						gb.setName(temp[1]);
						gb.setJtvwId(temp[9]);//spid
						gb.setReserve1(temp[10]);//spcode
						gb.setReserve2(StringUtils.isNotBlank(temp[22])?temp[22]:"03");//biztype
						resList.add(gb);
					}
				}
			}else if(StringTeletext.REMOTING_REQ_TYPE.equals(type)){
				if (StringUtils.isEmpty(rspXml)) {
					return resList;
				}

				MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) rspXml);
				String bossCode = bossJson.getBossCode();
				if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)){
					return resList;
				}
				JSONArray valueList = bossJson.getJSONArrayResult();
				if(null != valueList && valueList.size()>0){
					for (int j = 0; j < valueList.size(); j++) {
						JSONObject jsonObj = (JSONObject) valueList.get(j);
						gb = new GommonBusiness();
						gb.setName(jsonObj.getString("子业务名称"));
						gb.setJtvwId(jsonObj.getString("企业代码"));//spid
						gb.setReserve1(jsonObj.getString("子业务代码"));//spcode
						gb.setReserve2(StringUtils.isNotBlank(jsonObj.getString("BIZTYPE"))?jsonObj.getString("BIZTYPE"):"03");//biztype
						resList.add(gb);
					}
				}
			}
		     
		}catch(Exception e){
			logger.error("", e);
		}
		
		
		return resList;
	}
	
}
