package com.xwtech.xwecp.service.logic.invocation;

import java.util.List;
import java.util.Map;

import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.util.StringUtil;

/**
 * 根据meta文件设置的参数 解析实时账单查询结果 <br/>
 * 根据QueryNewRealTimeBillingInvocation进行修改
 * 
 * @author 宋万涛2017-03-22 19:41:50
 */
public class BaseJsonQueryUtils extends BaseInvocation implements ILogicalService {

	/************ 请求报文模板 **********/
	private String REQUEST_BOSSTELETEXT;

	/************ 调用方法前必须设置配置信息，如果不设置，后果自负 **********/
	public void setConfig(String bossTeletext) {
		this.REQUEST_BOSSTELETEXT = bossTeletext;
	}

	protected String getResponseXML(String accessId, ServiceConfig config, List<RequestParameter> params) {
		String reqXml = "";
		String rspXml = "";
		try {
			if (StringUtil.isNull(REQUEST_BOSSTELETEXT)) {
				return null;
			}

			reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT, params);

			Map<String, Object> remotingMap = this.bossTeletextUtil.mergeRemoteTeletext(REQUEST_BOSSTELETEXT, params);
			String sysParam = "";
			String busiParam = "";
			String type = StringTeletext.DEFAULT_REQ_TYPE;
			if (null != remotingMap) {
				sysParam = remotingMap.get("sysParam") == null ? "" : (String) remotingMap.get("sysParam");
				busiParam = remotingMap.get("busiParam") == null ? "" : (String) remotingMap.get("busiParam");
				type = remotingMap.get("type") == null ? "0" : (String) remotingMap.get("type");
			}

			String headTraceId = "";
			String headUserMobile = "";
			String headUserBrand = "";
			String headUserCity = "";
			String headPageNum = "";
			String headRecNum = "";
			String headSerialNum = "";
			String headJfserialNum = "";
			String headProdId = "";
			if (null != params && 0 < params.size()) {
				for (RequestParameter parameter : params) {
					String paramName = parameter.getParameterName();
					if ("header_traceId".equals(paramName)) {
						headTraceId = (String) parameter.getParameterValue();
					} else if ("header_usermobile".equals(paramName)) {
						headUserMobile = (String) parameter.getParameterValue();
					} else if ("header_userbrand".equals(paramName)) {
						headUserBrand = (String) parameter.getParameterValue();
					} else if ("header_usercity".equals(paramName)) {
						headUserCity = (String) parameter.getParameterValue();
					} else if ("header_pagenum".equals(paramName)) {
						headPageNum = (String) parameter.getParameterValue();
					} else if ("header_recnum".equals(paramName)) {
						headRecNum = (String) parameter.getParameterValue();
					} else if ("header_serialnum".equals(paramName)) {
						headSerialNum = (String) parameter.getParameterValue();
					} else if ("header_jfserialnum".equals(paramName)) {
						headJfserialNum = (String) parameter.getParameterValue();
					} else if ("header_prodId".equals(paramName)) {
						headProdId = (String) parameter.getParameterValue();
					}
				}
			}
			headTraceId = headTraceId == null ? "" : headTraceId;
			headUserMobile = headUserMobile == null ? "" : headUserMobile;
			headUserBrand = headUserBrand == null ? "" : headUserBrand;
			headUserCity = headUserCity == null ? "" : headUserCity;

			headPageNum = headPageNum == null ? "" : headPageNum;
			headRecNum = headRecNum == null ? "" : headRecNum;
			headSerialNum = headSerialNum == null ? "" : headSerialNum;
			headJfserialNum = headJfserialNum == null ? "" : headJfserialNum;
			headProdId = headProdId == null ? "" : headProdId;
			rspXml = (String) this.remote
					.callRemote(new StringTeletext(sysParam, busiParam, type, reqXml, accessId, REQUEST_BOSSTELETEXT,
							this.generateCity(params), headTraceId, headUserMobile, headUserBrand, headUserCity,
							headPageNum,headRecNum,headSerialNum,headJfserialNum,headProdId));
			return rspXml;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	@Override
	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
			List<RequestParameter> params) {
		return null;
	}

	public void distroy() {
		setConfig(null);
	}
}
