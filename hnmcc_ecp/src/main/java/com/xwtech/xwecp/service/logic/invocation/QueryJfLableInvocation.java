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
import com.xwtech.xwecp.service.logic.pojo.LableDetail;
import com.xwtech.xwecp.service.logic.pojo.QRY060002Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.MessageJsonUtil;
import com.xwtech.xwecp.util.ParseXmlConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryJfLableInvocation extends BaseInvocation implements
		ILogicalService {
	private static final Logger logger = LoggerFactory.getLogger(QueryJfLableInvocation.class);

	private static final String BODY = "Body";
	private static final String PROPERTYCATALOG = "PropertyCatalog";
	private static final String PROPERTYLIST = "PropertyList";
	private static final String PROPERTY = "Property";

	private BossTeletextUtil bossTeletextUtil;

	private IRemote remote;

	private ParseXmlConfig config;

	private String REQUEST_BOSSTELETEXT = "qry_jflable_01";

	public QueryJfLableInvocation() {
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		this.bossTeletextUtil = (BossTeletextUtil) (springCtx.getBean("bossTeletextUtil"));
		this.remote = (IRemote) (springCtx.getBean("bossRemote"));
		this.wellFormedDAO = (WellFormedDAO) (springCtx.getBean("wellFormedDAO"));
		this.config = new ParseXmlConfig();
	}
	
	public BaseServiceInvocationResult executeService(String accessId,
			ServiceConfig config, List<RequestParameter> params) {
		QRY060002Result result = new QRY060002Result();

		String reqXml = "";
		String rspXml = "";
		try
		{
			reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT, params);

			Map<String, Object> remotingMap = this.bossTeletextUtil.mergeRemoteTeletext(REQUEST_BOSSTELETEXT, params);
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
					}
					else if ("header_pagenum".equals(paramName)) {
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
			//rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT, this.generateCity(params)));

			rspXml = (String)this.remote.callRemote(new StringTeletext(sysParam,busiParam,type,reqXml, accessId,
					REQUEST_BOSSTELETEXT, this.generateCity(params),headTraceId,headUserMobile,headUserBrand,headUserCity,
					headPageNum,headRecNum,headSerialNum,headJfserialNum,headProdId));

			List<LableDetail> monthFluxsList = new ArrayList<LableDetail>();

			if (StringUtils.isEmpty(rspXml)) {
				result.setBossCode(LOGIC_ERROR);
				result.setErrorCode(LOGIC_ERROR);
				return result;
			}

			MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) rspXml);
			String bossCode = bossJson.getBossCode();
			String bossDesc = bossJson.getBossDesc();
			if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)){
				result.setBossCode(LOGIC_ERROR);
				result.setErrorCode(LOGIC_ERROR);
				return result;
			}

			result.setResultCode(MessageJsonUtil.LOGIC_SUCESS);
			result.setBossCode(bossCode);
			result.setErrorMessage(StringUtils.isNotBlank(bossDesc)?bossDesc:bossJson.getStringResult());

			JSONObject jsonObj = bossJson.getJSONObjectResult();
			if(null != jsonObj){
				JSONObject body = jsonObj.getJSONObject(BODY);
				if(null != body){
					JSONArray propertyCatalog = getJSONArrayPropertyCatalog(body,PROPERTYCATALOG);
					if(null != propertyCatalog && propertyCatalog.size()>0){
						for (int j = 0; j < propertyCatalog.size(); j++) {
							JSONObject propertyCatalogObj = (JSONObject)propertyCatalog.get(j);
							if(null == propertyCatalogObj){
								continue;
							}

							String tmp = propertyCatalogObj.getString(PROPERTYLIST);
							if(null != tmp && !"".equals(tmp)){
								JSONObject propertyList = JSONObject.parseObject(tmp);
								if(null != propertyList){
									Object o = propertyList.get(PROPERTY);
									JSONArray array = new JSONArray();
									if(null != o){
										if (o instanceof JSONArray) {
											array = (JSONArray) o;
										} else if (o instanceof JSONObject) {
											JSONArray json = new JSONArray();
											json.add(o);
											array = json;
										}

										LableDetail lableDetail;
										JSONObject messageObj;
										for (int i = 0; i < array.size(); i++) {
											messageObj = (JSONObject) array.get(i);
											if(messageObj.getString("PropertyType").equals("2") || messageObj.getString("PropertyType").equals("1")){
												lableDetail = new LableDetail();
												lableDetail.setPropertyDesc(messageObj.getString("PropertyDesc"));
												lableDetail.setPropertyId(messageObj.getString("PropertyId"));
												lableDetail.setPropertyName(messageObj.getString("PropertyName"));
												lableDetail.setPropertyOriginalValue(messageObj.getString("PropertyOriginalValue"));
												lableDetail.setPropertyType(messageObj.getString("PropertyType"));
												lableDetail.setPropertyValue(messageObj.getString("PropertyValue"));
												monthFluxsList.add(lableDetail);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			result.setPkgInfoList(monthFluxsList);
			return result;
		}
		catch (Exception e)
		{
			logger.error("", e);
		}
		return result;
	}

	public JSONArray getJSONArrayPropertyCatalog(JSONObject body,String node) {
		Object o = body.get(node);
		if (o instanceof JSONArray) {
			return (JSONArray) o;
		} else if (o instanceof JSONObject) {
			JSONArray json = new JSONArray();
			json.add(o);
			return json;
		}
		return new JSONArray();
	}
}
