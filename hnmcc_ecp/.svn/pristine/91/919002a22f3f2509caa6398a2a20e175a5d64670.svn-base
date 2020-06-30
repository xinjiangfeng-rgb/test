package com.xwtech.xwecp.service.logic.invocation;


import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.DEL181228Result;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 流量统付接口  主要完成集团成员的添加、变更和删除
 */
public class GroupMemberCRUDInvocation extends BaseInvocation implements ILogicalService {

	private String REQUEST_BOSSTELETEXT = "cc_181228";

	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
                                                      List<RequestParameter> params) {
		DEL181228Result result = new DEL181228Result();

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

			result.setRespCode(bossCode);
			result.setRespDesc(bossDesc);

//			result.setBossCode(bossCode);
//			result.setErrorMessage(StringUtils.isNotBlank(bossDesc)?bossDesc:bossJson.getStringResult());

		} catch (Exception e) {
			logger.error("流量统付接口~集团成员的添加、变更和删除", e);
		}
		return result;
	}

}
