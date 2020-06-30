package com.xwtech.xwecp.service.logic.invocation;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY912115List;
import com.xwtech.xwecp.service.logic.pojo.QRY912115Result;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 已开业务升级
 */
public class ProductHasOpenedInvocation extends BaseInvocation implements ILogicalService {

	private String REQUEST_BOSSTELETEXT = "cc_912115";

	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
                                                      List<RequestParameter> params) {
		QRY912115Result result = new QRY912115Result();

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
			QRY912115List qry912115;
			List<QRY912115List> qry912115List = new ArrayList<QRY912115List>();
			for(int i = 0;i<array.size();i++){
				jsonObj = (JSONObject)array.get(i);
				qry912115 = new QRY912115List();
				qry912115.setProdName(jsonObj.getString("PRODNAME"));
				qry912115.setProdId(jsonObj.getString("PRODID"));
				qry912115.setEffTime(jsonObj.getString("EFFTIME"));
				qry912115.setExpTime(jsonObj.getString("EXPTIME"));
				qry912115.setProdCode(jsonObj.getString("PRODCODE"));
				qry912115.setCreateDate(jsonObj.getString("CREATEDATE"));
				qry912115.setIsDefault(jsonObj.getString("ISDEFAULT"));
				qry912115.setProdDesc(jsonObj.getString("PRODDESC"));
				qry912115.setRtnSvcNum(jsonObj.getString("RTNSVCNUM"));
				qry912115.setFee(jsonObj.getString("FEE"));
				qry912115.setSpId(jsonObj.getString("SPID"));
				qry912115.setSpBizeCode(jsonObj.getString("SPBIZECODE"));
				qry912115List.add(qry912115);

			}
			result.setQry912115ListList(qry912115List);
			result.setResultCode(MessageJsonUtil.LOGIC_SUCESS);
			result.setBossCode(bossCode);
			result.setErrorMessage(StringUtils.isNotBlank(bossDesc)?bossDesc:bossJson.getStringResult());


		} catch (Exception ee) {
			logger.error("[]", ee);
		}
		return result;
	}

}
