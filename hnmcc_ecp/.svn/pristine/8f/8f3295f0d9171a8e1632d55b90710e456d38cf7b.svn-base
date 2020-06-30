package com.xwtech.xwecp.service.logic.invocation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.OperDetail;
import com.xwtech.xwecp.service.logic.pojo.QRY040004Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.MessageUtil.Message;
import com.xwtech.xwecp.util.ParseXmlConfig;

public class QueryOperDetailInvocation extends BaseInvocation implements ILogicalService {

	private static final Logger logger = LoggerFactory.getLogger(QueryOperDetailInvocation.class);

	private BossTeletextUtil bossTeletextUtil;

	private IRemote remote;

	private WellFormedDAO wellFormedDAO;

	private ParseXmlConfig config;
	
	private String REQUEST_BOSSTELETEX = "cc_cquerydetail_309";

	public QueryOperDetailInvocation() {
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		this.bossTeletextUtil = (BossTeletextUtil) (springCtx.getBean("bossTeletextUtil"));
		this.remote = (IRemote) (springCtx.getBean("bossRemote"));
		this.wellFormedDAO = (WellFormedDAO) (springCtx.getBean("wellFormedDAO"));
		this.config = new ParseXmlConfig();
	}

	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
		QRY040004Result res = new QRY040004Result();
		String reqXml = "",rspXml = "";
		try{
			reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEX, params);
			rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEX, this.generateCity(params)));
			if(StringUtils.isEmpty(rspXml) || rspXml.getBytes().length <120){
				res.setBossCode(LOGIC_ERROR);
				res.setErrorCode(LOGIC_ERROR);
				return res;
			}
			Message msg = MessageUtil.parse(rspXml);
			res.setResultCode(BOSS_SUCCESS.equals(msg.getHead().getCode()) ? LOGIC_SUCESS:LOGIC_ERROR);
			res.setBossCode(msg.getHead().getCode());
			res.setErrorMessage(msg.getHead().getDesc());
			//获取BOSS响应报文
//			String resStr = msg.getBody().toString();
			List<OperDetail> operDetailList = parseOperDetail(msg.getBody().asList());
			res.setOperDetail(operDetailList);
			
		}catch(Exception e){
			logger.error("", e);
		}
		return res;
	}
	/**
	 * 解析业务办理返回报文
	 * @param resStr
	 * @return
	 */
	private List<OperDetail> parseOperDetail(List<String[]> resList){
		List<OperDetail> operDetailList = new ArrayList<OperDetail>();
		OperDetail operDetail;
		for(String[] temp:resList){
			if(temp.length>5){
				operDetail = new OperDetail();
				operDetail.setOprTime(temp[5]);//办理时间
				operDetail.setOprChannel(temp[0]);//业务名称
				operDetail.setFormNum(temp[3]);//费用
				operDetailList.add(operDetail);
			}			
		}
		
		return operDetailList;
	}
	/**
	 * 解析业务办理返回报文
	 * @param resStr
	 * @return
	 */
	//办理时间|业务名称|费用:2015-02-28|产品变更之订购增值产品|0.00;2015-02-28|产品变更之订购增值产品|0.00;2015-02-28|补卡|0.00;2015-01-27|产品变更之订购增值产品|0.00;2015-01-27|增值产品转移|0.00;2015-02-27|分摊预存账务分摊|15.00;更多记录请到营业厅查询
//	private List<OperDetail> parseOperDetail(String resStr){
//		List<OperDetail> operDetailList = new ArrayList<OperDetail>();
//		OperDetail operDetail;
//		resStr = resStr.substring(resStr.indexOf(":")+1);
//		String[] resArray = resStr.split(";");
//		for(int i=0;i<resArray.length-1;i++){
//			operDetail = new OperDetail();
//			String[] temp=resArray[i].split("\\|");
//			operDetail.setOprTime(temp[0]);//办理时间
//			operDetail.setOprChannel(temp[1]);//业务名称
//			operDetail.setFormNum(temp[2]);//费用
//			operDetailList.add(operDetail);
//		}
//		
//		return operDetailList;
//	}
	
}
