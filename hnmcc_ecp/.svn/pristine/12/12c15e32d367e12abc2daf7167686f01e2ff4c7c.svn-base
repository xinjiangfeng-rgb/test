package com.xwtech.xwecp.service.logic.invocation;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.GPRSFluxsMonth;
import com.xwtech.xwecp.service.logic.pojo.QRY040049Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.MessageJsonUtil;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.MessageUtil.Message;
import com.xwtech.xwecp.util.ParseXmlConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryGPRSFluxsInvocation extends BaseInvocation implements
		ILogicalService {
	private static final Logger logger = LoggerFactory.getLogger(QueryGPRSFluxsInvocation.class);

	private BossTeletextUtil bossTeletextUtil;

	private IRemote remote;

	private ParseXmlConfig config;

	private String REQUEST_BOSSTELETEXT = "ac_agetgprsflux_717";
	
	public QueryGPRSFluxsInvocation() {
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		this.bossTeletextUtil = (BossTeletextUtil) (springCtx.getBean("bossTeletextUtil"));
		this.remote = (IRemote) (springCtx.getBean("bossRemote"));
		this.wellFormedDAO = (WellFormedDAO) (springCtx.getBean("wellFormedDAO"));
		this.config = new ParseXmlConfig();
	}
	
	public BaseServiceInvocationResult executeService(String accessId,
			ServiceConfig config, List<RequestParameter> params) {
		QRY040049Result result = new QRY040049Result();

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
			//rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT, this.generateCity(params)));

			rspXml = (String)this.remote.callRemote(new StringTeletext(sysParam,busiParam,type,reqXml, accessId,
					REQUEST_BOSSTELETEXT, this.generateCity(params),headTraceId,headUserMobile,headUserBrand,headUserCity,
					headPageNum,headRecNum,headSerialNum,headJfserialNum,headProdId));

			List<GPRSFluxsMonth> monthFluxsList = new ArrayList<GPRSFluxsMonth>();
			if(StringTeletext.DEFAULT_REQ_TYPE.equals(type)){
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

				String resStr = bossMsg.getBody().toString();

				if(rspXml.getBytes().length>70){
					String resCode = new String(rspXml.getBytes(),66,4);
					if("0000".endsWith(resCode)){
						//获取月流量json数据
						resStr = resStr.substring(resStr.lastIndexOf("["));
						monthFluxsList = parseMonthFluxs(resStr);
					}
				}
			}else if(StringTeletext.REMOTING_REQ_TYPE.equals(type)){
				logger.info("******** Boss返回数据为*****　" + rspXml);
				if (StringUtils.isEmpty(rspXml))
				{
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

				String str = bossJson.getGprsPkgUseHis();
				GPRSFluxsMonth monthFlux;
				JSONArray array = JSONArray.fromObject(str);
				JSONObject jsonObj;
				for(int i = 0;i<array.size();i++){
					jsonObj = (JSONObject)array.get(i);
					monthFlux = new GPRSFluxsMonth();
					monthFlux.setMonth(jsonObj.getString("MONTH"));
					monthFlux.setOutRes(jsonObj.getString("OUT_RES"));
					monthFlux.setRatingRes(jsonObj.getString("RATING_RES"));
					monthFluxsList.add(monthFlux);
				}
			}
			result.setMonthFluxsList(monthFluxsList);
		}
		catch (Exception e)
		{
			logger.error("", e);
		}
		return result;
	}
	
	private String formatGPRSDetail(String gprsFlux)
	{
		double flux = Double.parseDouble((String) gprsFlux);
		DecimalFormat df = new DecimalFormat("#0.00");
		gprsFlux = df.format(flux / 1024 / 1024);
		
		return gprsFlux;
	}
	
	/**
	 * 觖析月流量json字符串
	 * @param str
	 * @return
	 */
	//[{\"MONTH\":\"9月\",\"OUT_RES\":\"30.77\",\"RATING_RES\":\"36.83\"},{\"MONTH\":\"10月\",\"OUT_RES\":\"13.96\",\"RATING_RES\":\"306.51\"}]
	private List<GPRSFluxsMonth> parseMonthFluxs(String str){
		List<GPRSFluxsMonth> monthFluxsList = new ArrayList<GPRSFluxsMonth>();
		GPRSFluxsMonth monthFlux;
		JSONArray array = JSONArray.fromObject(str);
		JSONObject jsonObj;
		for(int i = 0;i<array.size();i++){
			jsonObj = (JSONObject)array.get(i);
			monthFlux = new GPRSFluxsMonth();
			monthFlux.setMonth(jsonObj.getString("MONTH"));
			monthFlux.setOutRes(jsonObj.getString("OUT_RES"));
			monthFlux.setRatingRes(jsonObj.getString("RATING_RES"));
			monthFluxsList.add(monthFlux);
		}
		
		return monthFluxsList;
		
	}
	
}
