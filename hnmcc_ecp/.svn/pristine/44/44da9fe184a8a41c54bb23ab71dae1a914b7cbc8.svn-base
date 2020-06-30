package com.xwtech.xwecp.service.logic.invocation;


import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.PAY181114Result;
import com.xwtech.xwecp.service.logic.pojo.UnifiedPay;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 统一支付验签接口
 */
public class UnifiedPaymentInvocation extends BaseInvocation implements ILogicalService {

	private String REQUEST_BOSSTELETEXT = "cc_181114";

	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
                                                      List<RequestParameter> params) {
		PAY181114Result result = new PAY181114Result();

		String rspXml = "";
		try {
			BaseJsonQueryUtils util = new BaseJsonQueryUtils();
			util.setConfig(REQUEST_BOSSTELETEXT);
			rspXml = util.getResponseXML(accessId, config, params);
			util.distroy();
			logger.info("******** Boss返回数据为*****　" + rspXml);

			MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) rspXml);
			String bossCode = bossJson.getBossCode();
			String bossDesc = bossJson.getBossDesc();

			logger.info("bossCode"+bossCode);
			logger.info("bossDesc"+bossDesc);

			if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)){
				result.setRespCode(bossCode);
				result.setRespDesc(bossDesc);
				return result;
			}

			JSONObject obj = JSONObject.parseObject(rspXml).getJSONObject("result");

			UnifiedPay pay = new UnifiedPay();

			pay.setBizCode(obj.getString("BizCode"));
			pay.setBizDesc(obj.getString("BizDesc"));
			pay.setCerID(obj.getString("CerID"));
			pay.setNotifyURL(obj.getString("NotifyURL"));
			pay.setOrderNo(obj.getString("OrderNo"));
			pay.setRetCode(obj.getString("RET_CODE"));
			pay.setRetMsg(obj.getString("RET_MSG"));
			pay.setSignValue(obj.getString("SignValue"));
			pay.setWeiXinAppId(obj.getString("WeiXinAppId"));
			pay.setCashierOrderUrl(obj.getString("cashierOrderUrl"));
			pay.setH5OrderUrl(obj.getString("h5OrderUrl"));

			result.setUnifiedPays(pay);
			result.setBossCode(bossCode);


			result.setErrorMessage(StringUtils.isNotBlank(bossDesc)?bossDesc:bossJson.getStringResult());

		} catch (Exception e) {
			logger.error("统一支付验签接口", e);
		}
		return result;
	}

}
