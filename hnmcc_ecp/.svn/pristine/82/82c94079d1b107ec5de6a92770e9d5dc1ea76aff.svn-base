package com.xwtech.xwecp.service.logic.invocation;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.BossParmDT;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.MessageUtil.Message;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class DelTSYWCLLBussInvocation extends BaseInvocation implements ILogicalService {

	private static final Logger logger = LoggerFactory.getLogger(DelTSYWCLLBussInvocation.class);

	private BossTeletextUtil bossTeletextUtil;

	private IRemote remote;

	private String REQUEST_BOSSTELETEXT_1 = "hnmcc_create_zzcp_02";// 特殊业务处理

	public DelTSYWCLLBussInvocation() {
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		this.bossTeletextUtil = (BossTeletextUtil) (springCtx.getBean("bossTeletextUtil"));
		this.remote = (IRemote) (springCtx.getBean("bossRemote"));
		this.wellFormedDAO = (WellFormedDAO) (springCtx.getBean("wellFormedDAO"));
	}

	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
			List<RequestParameter> params) {

		BaseServiceInvocationResult result = new BaseServiceInvocationResult();
		BossParmDT dt;
		String reqXml = "";
		String rspXml = "";
		try {
			dt = this.wellFormedDAO
					.getBussinessSpecialParm(this.getParameter(params, "id").getParameterValue().toString());
			if (dt != null) {
				params.add(new RequestParameter("customParaType", dt.getParm1()));
				params.add(new RequestParameter("prodPrcId", dt.getParm2()));
				params.add(new RequestParameter("prodPrcPara", dt.getParm3()));
			}
			// 特殊业务办理
			reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT_1, params);
			rspXml = (String) this.remote.callRemote(
					new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT_1, this.generateCity(params)));
			if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120) {
				result.setBossCode(LOGIC_ERROR);
				result.setErrorCode(LOGIC_ERROR);
				return result;
			}
			Message bossMsg = MessageUtil.parse(rspXml);
			result.setResultCode(BOSS_SUCCESS.equals(bossMsg.getHead().getCode()) ? LOGIC_SUCESS : LOGIC_ERROR);
			result.setBossCode(bossMsg.getHead().getCode());
			result.setErrorMessage(bossMsg.getHead().getDesc());

		} catch (Exception e) {
			logger.error("", e);
		}
		return result;
	}

}
