package com.xwtech.xwecp.service.logic.invocation;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY201513Result;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.MessageUtil.Message;

public class QryBroadbandInvocationByJSON extends BaseInvocation implements ILogicalService {

	private String REQUEST_BOSSTELETEXT_3 = "hnmcc_broadband_01";

	@Override
	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
			List<RequestParameter> params) {

		QRY201513Result result = new QRY201513Result();
		String rspXml = "";
		try {
			BaseJsonQueryUtils util = new BaseJsonQueryUtils();
			util.setConfig(REQUEST_BOSSTELETEXT_3);
			rspXml = util.getResponseXML(accessId, config, params);
			util.distroy();
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
		System.out.println("**11201513返回" + str + str.size());
		QRY201513Result fluxUsed = new QRY201513Result();
		// for(String[] temp:str){
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
		return fluxUsed;

	}

}
