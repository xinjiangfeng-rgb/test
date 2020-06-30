package com.xwtech.xwecp.service.logic.invocation;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY912117List;
import com.xwtech.xwecp.service.logic.pojo.QRY912117Result;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 已开业务升级
 */
public class OpenedProductInvocation extends BaseInvocation implements ILogicalService {

	private String REQUEST_BOSSTELETEXT = "cc_912117";

	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
                                                      List<RequestParameter> params) {
		QRY912117Result result = new QRY912117Result();

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
			QRY912117List qry912117;
			List<QRY912117List> qry912117List = new ArrayList<QRY912117List>();
			for(int i = 0;i<array.size();i++){
				jsonObj = (JSONObject)array.get(i);
				qry912117 = new QRY912117List();
				qry912117.setProdName(jsonObj.getString("PRODNAME"));
				qry912117.setProdId(jsonObj.getString("PRODID"));
				qry912117.setEffTime(jsonObj.getString("EFFTIME"));
				qry912117.setExpTime(jsonObj.getString("EXPTIME"));
				qry912117.setProdCode(jsonObj.getString("PRODCODE"));
				qry912117.setCreateDate(jsonObj.getString("CREATEDATE"));
				qry912117.setIsDefault(jsonObj.getString("ISDEFAULT"));
				qry912117.setProdDesc(jsonObj.getString("PRODDESC"));
				qry912117.setRtnSvcNum(jsonObj.getString("RTNSVCNUM"));
				qry912117.setFee(jsonObj.getString("FEE"));
				qry912117.setSpId(jsonObj.getString("SPID"));
				qry912117.setSpBizeCode(jsonObj.getString("SPBIZECODE"));
				qry912117List.add(qry912117);

			}
			result.setQry912117List(qry912117List);
			result.setResultCode(MessageJsonUtil.LOGIC_SUCESS);
			result.setBossCode(bossCode);
			result.setErrorMessage(StringUtils.isNotBlank(bossDesc)?bossDesc:bossJson.getStringResult());


		} catch (Exception ee) {
			logger.error("[]", ee);
		}
		return result;
	}

}
