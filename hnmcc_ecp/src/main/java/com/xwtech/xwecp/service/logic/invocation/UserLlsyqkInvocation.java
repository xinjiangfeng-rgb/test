package com.xwtech.xwecp.service.logic.invocation;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.AppRankInfo;
import com.xwtech.xwecp.service.logic.pojo.HourFlowInfo;
import com.xwtech.xwecp.service.logic.pojo.QRY200305Result;
import com.xwtech.xwecp.service.logic.pojo.UserFlowInfo;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户的流量使用情况API接口
 */
public class UserLlsyqkInvocation extends BaseInvocation implements ILogicalService {

	private String REQUEST_BOSSTELETEXT = "cc_200305";

	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
                                                      List<RequestParameter> params) {
		QRY200305Result result = new QRY200305Result();

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
			if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)){
				result.setBossCode(LOGIC_ERROR);
				result.setErrorCode(LOGIC_ERROR);
				return result;
			}
//			String errCode = MessageUtil.parse(rspXml).getHead().getCode();
//			String errDesc = MessageUtil.parse(rspXml).getHead().getDesc();

			logger.info("bossCode"+bossCode);
			logger.info("bossDesc"+bossDesc);
			logger.info("用户的流量使用情况API接口result"+bossJson.getResult());
			logger.info("用户的流量使用情况API接口data1"+bossJson.getResult().getJSONObject("data"));
			logger.info("用户的流量使用情况API接口data2"+bossJson.getResult().getJSONObject("data").getJSONArray("data"));

			JSONArray jsonArray = bossJson.getResult().getJSONObject("data").getJSONArray("data");
			JSONObject jsonObj;
			List<HourFlowInfo> hourFlowInfos = new ArrayList<>();
			List<AppRankInfo> appRankInfos = new ArrayList<>();
			List<UserFlowInfo> userFlowInfos = new ArrayList<>();

			for(int i = 0;i<jsonArray.size();i++){
				jsonObj = (JSONObject)jsonArray.get(i);

				JSONArray hourFlowInfos1 = jsonObj.getJSONArray("HourFlowInfo");
				for(Object aa :hourFlowInfos1){
					JSONObject jsonObject = (JSONObject) aa;
					HourFlowInfo flowInfo = new HourFlowInfo();
					flowInfo.setHourId(jsonObject.getString("HOUR_ID"));
					flowInfo.setPhoneNo(jsonObject.getString("PHONE_NO"));
					flowInfo.setFlow(jsonObject.getString("FLOW"));

					hourFlowInfos.add(flowInfo);
				}

				JSONArray appRankInfos1 = jsonObj.getJSONArray("AppRankInfo");
				for(Object aa :appRankInfos1){
					JSONObject jsonObject = (JSONObject) aa;
					AppRankInfo appRankInfo = new AppRankInfo();
					appRankInfo.setRanks(jsonObject.getString("RANKS"));
					appRankInfo.setProp(jsonObject.getString("PROP"));
					appRankInfo.setPhoneNo(jsonObject.getString("PHONE_NO"));
					appRankInfo.setBusiName(jsonObject.getString("BUSI_NAME"));

					appRankInfos.add(appRankInfo);
				}

				JSONArray userFlowInfos1 = jsonObj.getJSONArray("UserFlowInfo");
				for(Object aa :userFlowInfos1){
					JSONObject jsonObject = (JSONObject) aa;
					UserFlowInfo userFlowInfo = new UserFlowInfo();
					userFlowInfo.setRanks(jsonObject.getString("RANKS"));
					userFlowInfo.setGprsFlow(jsonObject.getString("GPRS_FLOW"));
					userFlowInfo.setProp(jsonObject.getString("PROP"));
					userFlowInfo.setPhoneNo(jsonObject.getString("PHONE_NO"));

					userFlowInfos.add(userFlowInfo);
				}
			}
			result.setHourFlowInfoList(hourFlowInfos);
			result.setAppRankInfoListp(appRankInfos);
			result.setUserFlowInfoList(userFlowInfos);
			result.setResultCode(MessageJsonUtil.LOGIC_SUCESS);
			result.setBossCode(bossCode);
			result.setErrorMessage(StringUtils.isNotBlank(bossDesc)?bossDesc:bossJson.getStringResult());


		} catch (Exception ee) {
			logger.error("[]", ee);
		}
		return result;
	}

}
