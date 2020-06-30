package com.xwtech.xwecp.service.logic.invocation;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.*;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询某用户号码的画像信息
 */
public class QryCustomerPortraitInvocation extends BaseInvocation implements ILogicalService {

	private String REQUEST_BOSSTELETEXT = "cc_181107";

	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
                                                      List<RequestParameter> params) {
		QRY181107Result result = new QRY181107Result();

		Data data= new Data();
		Profile profile= new Profile();

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

			logger.info("bossCode"+bossCode);
			logger.info("bossDesc"+bossDesc);

			JSONObject obj = com.alibaba.fastjson.JSONObject.parseObject(rspXml).getJSONObject("result");
			String errorCode = obj.getString("errorCode");

			JSONObject data1 = obj.getJSONObject("data");

			JSONObject profile1 = data1.getJSONObject("profile");

			JSONArray attrList1 = profile1.getJSONArray("attrList");
			JSONArray tagList1 = profile1.getJSONArray("tagList");


			//遍历
			JSONObject jsonObj;

			List<Attr> attrs=new ArrayList<Attr>();
			Attr attr;
			if (attrList1.size()>0){

				for(int i = 0;i<attrList1.size();i++){
					jsonObj = (JSONObject)attrList1.get(i);
					attr = new Attr();
					attr.setAttrId(jsonObj.getString("attrId"));
					attr.setAttrName(jsonObj.getString("attrName"));
					attr.setValue(jsonObj.getString("value"));
					attr.setUnit(jsonObj.getString("unit"));
					attr.setGroup(jsonObj.getString("group"));

					attrs.add(attr);
				}
			}

			profile.setAttrList(attrs);

			List<Tag> tags=new ArrayList<Tag>();;
			Tag tag;
			if (tagList1.size()>0){

				for(int i = 0;i<tagList1.size();i++){
					jsonObj = (JSONObject)tagList1.get(i);
					tag = new Tag();
					tag.setTagId(jsonObj.getString("tagId"));
					tag.setTagName(jsonObj.getString("tagName"));

					tags.add(tag);
				}
			}
			profile.setTagList(tags);

			data.setProfile(profile);
			data.setErrorCode(errorCode);


//			result.setResultCode(MessageJsonUtil.LOGIC_SUCESS);
			result.setBossCode(bossCode);
			result.setErrorMessage(StringUtils.isNotBlank(bossDesc)?bossDesc:bossJson.getStringResult());
			result.setData(data);


		} catch (Exception e) {
			logger.error("查询某用户号码的画像信息接口异常：", e);
		}
		return result;
	}

}
