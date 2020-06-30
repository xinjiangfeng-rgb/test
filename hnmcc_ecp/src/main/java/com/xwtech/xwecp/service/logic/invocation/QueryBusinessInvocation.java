package com.xwtech.xwecp.service.logic.invocation;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY912114List;
import com.xwtech.xwecp.service.logic.pojo.QRY912114Result;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 已开业务升级
 */
public class QueryBusinessInvocation extends BaseInvocation implements ILogicalService {

	private String REQUEST_BOSSTELETEXT = "cc_912114";

	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
                                                      List<RequestParameter> params) {
		QRY912114Result result = new QRY912114Result();

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
			logger.info("已开业务查询：" + bossJson.getStringResult());
			JSONArray array = JSONArray.parseArray(bossJson.getStringResult());
			logger.info("已开业务SIZE: " + array.size());
			JSONObject jsonObj;
			QRY912114List qry912114;
			List<QRY912114List> qry912114List = new ArrayList<QRY912114List>();
			for(int i = 0;i<array.size();i++){
				jsonObj = (JSONObject)array.get(i);
				qry912114 = new QRY912114List();
				qry912114.setProdName(jsonObj.getString("PRODNAME"));
				qry912114.setProdId(jsonObj.getString("PRODID"));
				qry912114.setEffTime(jsonObj.getString("EFFTIME"));
				qry912114.setExpTime(jsonObj.getString("EXPTIME"));
				qry912114.setProdCode(jsonObj.getString("PRODCODE"));
				qry912114.setCreateDate(jsonObj.getString("CREATEDATE"));
				qry912114.setIsDefault(jsonObj.getString("ISDEFAULT"));
				qry912114.setProdDesc(jsonObj.getString("PRODDESC"));
				qry912114.setRtnSvcNum(jsonObj.getString("RTNSVCNUM"));
				qry912114.setFee(jsonObj.getString("FEE"));
				qry912114.setSpId(jsonObj.getString("SPID"));
				qry912114.setSpBizeCode(jsonObj.getString("SPBIZECODE"));
				qry912114List.add(qry912114);
			}
			result.setQry912114List(qry912114List);
			result.setResultCode(MessageJsonUtil.LOGIC_SUCESS);
			result.setBossCode(bossCode);
			result.setErrorMessage(StringUtils.isNotBlank(bossDesc)?bossDesc:bossJson.getStringResult());


		} catch (Exception ee) {
			logger.error("[]", ee);
		}
		return result;
	}

}
