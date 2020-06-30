package com.xwtech.xwecp.service.logic.invocation;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY201513Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.MessageUtil.Message;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class QryBroadbandInvocation extends BaseInvocation implements ILogicalService {
	/**
	 * java.lang.ArrayIndexOutOfBoundsException: 12 at
	 * com.xwtech.xwecp.service.logic.invocation.QryBroadbandInvocation.parseBroadband(QryBroadbandInvocation.java:92)
	 * at
	 * com.xwtech.xwecp.service.logic.invocation.QryBroadbandInvocation.executeService(QryBroadbandInvocation.java:61)
	 * at
	 * com.xwtech.xwecp.service.server.SocketServiceImpl.executeByConfig(SocketServiceImpl.java:195)
	 * at
	 * com.xwtech.xwecp.service.server.SocketServiceImpl.execute(SocketServiceImpl.java:96)
	 * at
	 * com.xwtech.xwecp.Jedis.RedisInformation.handleResultToRedis(RedisInformation.java:60)
	 * at
	 * com.xwtech.xwecp.service.ServiceExecutor.callService(ServiceExecutor.java:286)
	 * at
	 * com.xwtech.xwecp.flow.works.chains.nodes.CallServiceNode.executeSuccess(CallServiceNode.java:47)
	 * at
	 * com.xwtech.xwecp.flow.works.chains.AbstractFlowControl.run(AbstractFlowControl.java:94)
	 * at
	 * com.xwtech.xwecp.flow.works.chains.AbstractFlowControl.run(AbstractFlowControl.java:99)
	 * at
	 * com.xwtech.xwecp.flow.works.chains.AbstractFlowControl.run(AbstractFlowControl.java:99)
	 * at
	 * com.xwtech.xwecp.flow.works.chains.AbstractFlowControl.run(AbstractFlowControl.java:99)
	 * at
	 * com.xwtech.xwecp.flow.works.chains.AbstractFlowControl.run(AbstractFlowControl.java:99)
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(QryBroadbandInvocation.class);

	private BossTeletextUtil bossTeletextUtil;
	private String REQUEST_BOSSTELETEXT_3 = "hnmcc_broadband_01";

	private IRemote remote;

	public QryBroadbandInvocation() {
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		this.bossTeletextUtil = (BossTeletextUtil) (springCtx.getBean("bossTeletextUtil"));
		this.remote = (IRemote) (springCtx.getBean("bossRemote"));
		this.wellFormedDAO = (WellFormedDAO) (springCtx.getBean("wellFormedDAO"));
	}

	@Override
	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
			List<RequestParameter> params) {

		QRY201513Result result = new QRY201513Result();
		String reqXml = "";
		String rspXml = "";
		try {
			// 流量明细
			reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT_3, params);
			rspXml = (String) this.remote.callRemote(
					new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT_3, this.generateCity(params)));
			if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120) {
				result.setBossCode(LOGIC_ERROR);
				result.setErrorCode(LOGIC_ERROR);
				return result;
			}
			Message bossMsg = MessageUtil.parse(rspXml);
			result = parseBroadband(bossMsg.getBody().asList());
			result.setResultCode(BOSS_SUCCESS.equals(bossMsg.getHead().getCode()) ? LOGIC_SUCESS : LOGIC_ERROR);

			String errCode = new String(rspXml.getBytes(), 66, 4);
			String errDesc = new String(rspXml.getBytes(), 70, 42);
			result.setResultCode(BOSS_SUCCESS.equals(errCode) ? LOGIC_SUCESS : LOGIC_ERROR);
			result.setBossCode(errCode);
			result.setErrorMessage(StringUtils.trim(errDesc));
			result.setBossCode(bossMsg.getHead().getCode());
			result.setErrorMessage(bossMsg.getHead().getDesc());
			System.out.println("bossMsg==" + bossMsg.getHead().getCode() + "==" + bossMsg.getBody());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
			result.setErrorCode(LOGIC_EXCEPTION);
		}
		return result;
	}

	private QRY201513Result parseBroadband(List<String[]> str) {
		QRY201513Result fluxUsed = new QRY201513Result();
		// for(String[] temp:str){
		if(str!=null&&str.size()>0){
			String[] temp = str.get(0);
			System.out.println("是否订购宽带**201513" + temp[0] + temp.length);
			fluxUsed.setFlag(temp[0]);
			fluxUsed.setKdName(temp[1]);
			fluxUsed.setKdRate(temp[2]);
			fluxUsed.setKdRentExpireDate(temp[3]);
			fluxUsed.setKdCreateDate(temp[4]);
			fluxUsed.setTypeName(temp[5]);
			fluxUsed.setKdState(temp[6]);
			fluxUsed.setKdType(temp[7]);
			fluxUsed.setIsHlwdsOffer(temp[8]);
			fluxUsed.setIsHlwdsPlan(temp[9]);
			fluxUsed.setKdCode(temp[10]);
			fluxUsed.setAdvFreeTotal(temp[11]);
			if (temp.length > 12) {
				fluxUsed.setMarketings(temp[12]);
			} else {
				fluxUsed.setAdvFreeTotal(temp[11]);
			}
			// }
			System.out.println(fluxUsed.toString());
		}
		return fluxUsed;

	}

}
