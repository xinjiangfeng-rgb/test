package com.xwtech.xwecp.service.logic.invocation;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.MonthBill;
import com.xwtech.xwecp.service.logic.pojo.QRY161203Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
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

public class QueryMonthBillInvocation extends BaseInvocation implements
		ILogicalService {
	private static final Logger logger = LoggerFactory.getLogger(QueryMonthBillInvocation.class);

	private BossTeletextUtil bossTeletextUtil;

	private IRemote remote;

	private ParseXmlConfig config;

	private String REQUEST_BOSSTELETEXT = "ac_agetmonthbill_161203";

	public QueryMonthBillInvocation() {
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		this.bossTeletextUtil = (BossTeletextUtil) (springCtx.getBean("bossTeletextUtil"));
		this.remote = (IRemote) (springCtx.getBean("bossRemote"));
		this.wellFormedDAO = (WellFormedDAO) (springCtx.getBean("wellFormedDAO"));
		this.config = new ParseXmlConfig();
	}
	
	public BaseServiceInvocationResult executeService(String accessId,
			ServiceConfig config, List<RequestParameter> params) {
		QRY161203Result result = new QRY161203Result();

		String reqXml = "";
		String rspXml = "";
		try {
			reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT, params);
			rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT, this.generateCity(params)));			
		 	logger.info("******** Boss返回数据为*****　" + rspXml);
		 	if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120){
				result.setBossCode(LOGIC_ERROR);
				result.setErrorCode(LOGIC_ERROR);
				return result;
		  	}
		 	Message bossMsg = MessageUtil.parse(rspXml);
			result.setResultCode(BOSS_SUCCESS.equals(bossMsg.getHead().getCode()) ? LOGIC_SUCESS : LOGIC_ERROR);
			result.setBossCode(bossMsg.getHead().getCode());
			result.setErrorMessage(bossMsg.getHead().getDesc());
		       
		   	String resStr = bossMsg.getBody().toString();
			List<MonthBill> monthFluxsList = new ArrayList<MonthBill>();
			if(rspXml.getBytes().length>70){
				String resCode = new String(rspXml.getBytes(),66,4);
				if("0000".endsWith(resCode)){
					//获取月流量json数据
					resStr = resStr.substring(resStr.lastIndexOf("["));
					monthFluxsList = parseMonthFluxs(resStr);
				}
			}

		   	result.setMonthFluxsList(monthFluxsList);
		       
		}
		catch (Exception e) {
			logger.error("", e);
		}
		return result;
	}
	
	private String formatGPRSDetail(String gprsFlux) {
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
	//[{MONTH：月份,FLOW：值,--流量使用量,util:值，--单位}]
	private List<MonthBill> parseMonthFluxs(String str){
		List<MonthBill> monthFluxsList = new ArrayList<MonthBill>();
		MonthBill monthFlux;
		JSONArray array = JSONArray.fromObject(str);
		JSONObject jsonObj;
		for(int i = 0;i<array.size();i++){
			jsonObj = (JSONObject)array.get(i);
			monthFlux = new MonthBill();
			monthFlux.setMonth(jsonObj.getString("MONTH"));
			monthFlux.setFlow(jsonObj.getString("FLOW"));
			monthFlux.setUtil(jsonObj.getString("util"));
			monthFluxsList.add(monthFlux);
		}
		return monthFluxsList;
	}
}
