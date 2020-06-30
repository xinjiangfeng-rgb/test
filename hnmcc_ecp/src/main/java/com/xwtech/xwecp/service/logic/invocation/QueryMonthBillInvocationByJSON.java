package com.xwtech.xwecp.service.logic.invocation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.MonthBill;
import com.xwtech.xwecp.service.logic.pojo.QRY161203Result;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.MessageUtil.Message;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class QueryMonthBillInvocationByJSON extends BaseInvocation implements ILogicalService {

	private String REQUEST_BOSSTELETEXT = "ac_agetmonthbill_161203";

	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
			List<RequestParameter> params) {
		QRY161203Result result = new QRY161203Result();

		String rspXml = "";
		try {
			BaseJsonQueryUtils util = new BaseJsonQueryUtils();
			util.setConfig(REQUEST_BOSSTELETEXT);
			rspXml = util.getResponseXML(accessId, config, params);
			util.distroy();
			if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120) {
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
			if (rspXml.getBytes().length > 70) {
				String resCode = new String(rspXml.getBytes(), 66, 4);
				if ("0000".endsWith(resCode)) {
					// 获取月流量json数据
					resStr = resStr.substring(resStr.lastIndexOf("["));
					monthFluxsList = parseMonthFluxs(resStr);
				}
			}

			result.setMonthFluxsList(monthFluxsList);

		} catch (Exception e) {
			logger.error("", e);
		}
		return result;
	}

	public String formatGPRSDetail(String gprsFlux) {
		double flux = Double.parseDouble((String) gprsFlux);
		DecimalFormat df = new DecimalFormat("#0.00");
		gprsFlux = df.format(flux / 1024 / 1024);
		return gprsFlux;
	}

	/**
	 * 觖析月流量json字符串
	 * 
	 * @param str
	 * @return
	 */
	// [{\"MONTH\":\"9月\",\"OUT_RES\":\"30.77\",\"RATING_RES\":\"36.83\"},{\"MONTH\":\"10月\",\"OUT_RES\":\"13.96\",\"RATING_RES\":\"306.51\"}]
	// [{MONTH：月份,FLOW：值,--流量使用量,util:值，--单位}]
	private List<MonthBill> parseMonthFluxs(String str) {
		List<MonthBill> monthFluxsList = new ArrayList<MonthBill>();
		MonthBill monthFlux;
		JSONArray array = JSONArray.fromObject(str);
		JSONObject jsonObj;
		for (int i = 0; i < array.size(); i++) {
			jsonObj = (JSONObject) array.get(i);
			monthFlux = new MonthBill();
			monthFlux.setMonth(jsonObj.getString("MONTH"));
			monthFlux.setFlow(jsonObj.getString("FLOW"));
			monthFlux.setUtil(jsonObj.getString("util"));
			monthFluxsList.add(monthFlux);
		}
		return monthFluxsList;
	}
}
