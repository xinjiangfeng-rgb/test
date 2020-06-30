package com.xwtech.xwecp.service.logic.invocation;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY180822Result;
import com.xwtech.xwecp.service.logic.pojo.QryClientAnswerSmsBean;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 亲密付接口回复短信查询接口
 */
public class QryClientAnswerSmsInvocation extends BaseInvocation implements ILogicalService {

	private String REQUEST_BOSSTELETEXT = "cc_180822";

	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
                                                      List<RequestParameter> params) {
		QRY180822Result result = new QRY180822Result();

		String rspXml = "";
		try {
			BaseJsonQueryUtils util = new BaseJsonQueryUtils();
			util.setConfig(REQUEST_BOSSTELETEXT);
			rspXml = util.getResponseXML(accessId, config, params);
			util.distroy();
			logger.info("******** Boss返回数据为*****　" + rspXml);
			if (StringUtils.isEmpty(rspXml) ) {
				result.setBossCode(LOGIC_ERROR);
				result.setErrorCode(LOGIC_ERROR);
				return result;
			}
			MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) rspXml);
			String bossCode = bossJson.getBossCode();
			String bossDesc = bossJson.getBossDesc();

			logger.info("bossCode"+bossCode);
			logger.info("bossDesc"+bossDesc);

			JSONObject obj = JSONObject.parseObject(rspXml).getJSONObject("result");
			String returnCode = obj.getString("returnCode");
			String returnMessage = obj.getString("returnMessage");

			result.setReturnCode(returnCode);
			result.setReturnMessage(returnMessage);


			if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)){
				result.setBossCode(LOGIC_ERROR);
				result.setErrorCode(LOGIC_ERROR);
				return result;
			}

			JSONArray recordValue = obj.getJSONArray("recordValue");

			//遍历
			JSONObject jsonObj;

			List<QryClientAnswerSmsBean> recordValues=new ArrayList<QryClientAnswerSmsBean>();
			QryClientAnswerSmsBean qryClientAnswerSmsBean;
			if (recordValue.size()>0){

				for(int i = 0;i<recordValue.size();i++){
					jsonObj = (JSONObject)recordValue.get(i);
					qryClientAnswerSmsBean = new QryClientAnswerSmsBean();
					qryClientAnswerSmsBean.setMessage(jsonObj.getString("MESSAGE"));
					qryClientAnswerSmsBean.setOptcode(jsonObj.getString("OPTCODE"));
					qryClientAnswerSmsBean.setPort(jsonObj.getString("PORT"));
					qryClientAnswerSmsBean.setRectime(jsonObj.getString("RECTIME"));
					qryClientAnswerSmsBean.setSvcnum(jsonObj.getString("SVCNUM"));

					recordValues.add(qryClientAnswerSmsBean);
				}
			}

			result.setRecordValue(recordValues);

			result.setBossCode(bossCode);
			result.setErrorMessage(StringUtils.isNotBlank(bossDesc)?bossDesc:bossJson.getStringResult());

		} catch (Exception e) {
			logger.error("亲密付接口回复短信查询接口", e);
		}
		return result;
	}

}
