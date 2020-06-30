package com.xwtech.xwecp.service.logic.client_impl.common.impl;

import java.util.HashMap;
import java.util.Map;

import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.client.BaseClientServiceImpl;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client_impl.common.IMWCPBService;
import com.xwtech.xwecp.service.logic.pojo.DEL010030Result;
import com.xwtech.xwecp.service.RemoteCaller;

/**
 * 梦网业务的退订和取消
 *
 * 1 SvcNum 号码 C 11 Y 号码<br/>
 * 2 inUserPwd 用户密码 C 20 N 用户密码,密码修改时传。其他时候传空 <br/>
 * 3 inCertType 证件类型 C 20 N 证件类型,用户注册/修复时传，其他时候传空<br/>
 * 4 inCertNum 证件号码 C 20 N 证件号码，用户注册/修复时传，其他时候传空。<br/>
 * 4 OprCode 操作代码 C 20 Y 操作代码: 01-用户注册 02-用户注销 03-密码修改 04-业务暂停 05-业务恢复 06-服务定购
 * 07-服务订购取消 08-与业务管理平台（业务平台）相关的用户资料变更 <br/>
 * 5 Biztype 业务类型代码 C 30 Y 业务类型代码,例如： 01-17201 02-WLAN 03-WAP 04-SMS 05-MMS
 * 07-LBS 08-EMAIL 09-PDA 13-手机动画 17-广东通用下载 18-北京通用下载 12-飞信<br/>
 * 
 * 6 Spid SP代码 C 8 Y SP代码， <br/>
 * 7 SpbizCode 子业务代码 C 8 Y 子业务代码 8 Optr 操作员工号 C 8 Y 操作员工号
 *
 * MHF${request_seq}100802HNYD00XZT01 ${request_time}000 ----- FFFF
 * FFFFFFFF${phoneNum}${t}${t}${t}${t}06${t}${bizType}${t}${spId}${t}${spBizCode}${t}MNF
 * 
 */
public class MengWangServiceImpl extends BaseClientServiceImpl implements IMWCPBService {

	@Override
	public DEL010030Result transactMWCP(String phone, String bizType, String spId, String spBizCode, int optType) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("phoneNum", phone);
		mapParam.put("bizType", bizType);
		mapParam.put("spId", spId);
		mapParam.put("spBizCode", spBizCode);
		mapParam.put("__cmd", "DEL010030");
		mapParam.put("optType", optType);

		mapParam.put("Biztype", " ");
		mapParam.put("inUserPwd", " ");
		mapParam.put("inCertType", " ");
		mapParam.put("inCertNum", " ");
		mapParam.put("channelSource", " ");
		BaseServiceInvocationResult rs;
		try {
			rs = RemoteCaller.callRemote(mapParam);
			return (DEL010030Result) rs;
		} catch (LIException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DEL010030Result transactMWCPchannelSource(String phone, String bizType, String spId, String spBizCode, int optType,String channelSource) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("phoneNum", phone);
		mapParam.put("bizType", bizType);
		mapParam.put("spId", spId);
		mapParam.put("spBizCode", spBizCode);
		mapParam.put("__cmd", "DEL010030");
		mapParam.put("optType", optType);

		mapParam.put("Biztype", " ");
		mapParam.put("inUserPwd", " ");
		mapParam.put("inCertType", " ");
		mapParam.put("inCertNum", " ");
		mapParam.put("channelSource", channelSource);

		BaseServiceInvocationResult rs;
		try {
			rs = RemoteCaller.callRemote(mapParam);
			return (DEL010030Result) rs;
		} catch (LIException e) {
			e.printStackTrace();
		}
		return null;
	}
}
