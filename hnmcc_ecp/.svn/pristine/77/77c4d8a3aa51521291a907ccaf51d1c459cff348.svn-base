package com.xwtech.xwecp.service.logic.invocation;


import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.MemberHandling;
import com.xwtech.xwecp.service.logic.pojo.QRY181230Result;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 流量统付接口 根据外围渠道订单流水查询运营商订单处理情况
 */
public class QryMemberHandlingInvocation extends BaseInvocation implements ILogicalService {

	private String REQUEST_BOSSTELETEXT = "cc_181230";

	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
                                                      List<RequestParameter> params) {
		QRY181230Result result = new QRY181230Result();

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
			if(obj != null ){
				String billId = obj.getString("BILL_ID");
				String dealResult = obj.getString("DEAL_RESULT");
				String memSrvpkgDesc = obj.getString("MEM_SRVPKG_DESC");
				String state = obj.getString("STATE");
				String validDate = obj.getString("VALID_DATE");

				MemberHandling handling = new MemberHandling();
				handling.setBillId(billId);
				handling.setDealResult(dealResult);
				handling.setMemSrvpkgDesc(memSrvpkgDesc);
				handling.setState(state);
				handling.setValidDate(validDate);

				result.setResult(handling);
			}

			result.setBossCode(bossCode);
			result.setErrorMessage(StringUtils.isNotBlank(bossDesc)?bossDesc:bossJson.getStringResult());

		} catch (Exception e) {
			logger.error("流量统付接口~成员处理情况查询", e);
		}
		return result;
	}

}
