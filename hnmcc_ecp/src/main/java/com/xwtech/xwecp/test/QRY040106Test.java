package com.xwtech.xwecp.test;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IGroupMemberService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.GroupMemberServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.GroupMemberInfo;
import com.xwtech.xwecp.service.logic.pojo.QRY040106Result;
import com.xwtech.xwecp.util.DESEncrypt;

public class QRY040106Test
{
	private static final Logger logger = LoggerFactory.getLogger(QRY040106Test.class);
	
	/**
	 * 新大陆提供的密钥，需要每两位转成1个字节
	 */
	private static byte[] BOSS_SECRET_KEY = {
		0x0b,0x33,(byte)0xe7,(byte)0xb2,0x51,0x0d,0x75,(byte)0xc3,0x4e,
		(byte)0xdd,(byte)0x3b,(byte)0x51,0x24,0x36,(byte)0xa8,(byte)0x28,
		0x0b,0x33,(byte)0xe7,(byte)0xb2,0x51,0x0d,0x75,(byte)0xc3	
	};
	
	public static void main(String[] args) throws Exception
	{
		//初始化ecp客户端片段
		Properties props = new Properties();
		props.put("client.channel", "hnmcc_channel");
//		props.put("platform.url", "http://192.168.16.226:8080/obsh_ecp/xwecp.do");
        props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
		props.put("platform.user", "hnmcc");
		props.put("platform.password", "hnmcc");
		
		XWECPLIClient.createInstance(props);
		//逻辑接口调用片段
		LIInvocationContext lic = LIInvocationContext.getContext();
		lic.setBizCode("biz_code_19234");
		lic.setOpType("开通/关闭/查询/变更");
		lic.setUserBrand("动感地带");
		lic.setUserCity("14");
		lic.setUserMobile("13815890413");
		InvocationContext ic = new InvocationContext();
		ic.addContextParameter("login_msisdn", "13815890413");
		ic.addContextParameter("route_type", "2");
		ic.addContextParameter("route_value", "13815890413");
		
		lic.setContextParameter(ic);
		System.out.println(DESEncrypt.desString("112233", BOSS_SECRET_KEY));
		IGroupMemberService c = new GroupMemberServiceClientImpl();
		QRY040106Result result = c.groupMember("18803693936", "93713167205", "2015-09-02", "2015-09-28");
		if (result != null)
		{
			logger.info(" ====== getResultCode ======" + result.getResultCode());
			logger.info(" ====== getErrorCode ======" + result.getErrorCode());
			logger.info(" ====== getErrorMessage ======" + result.getErrorMessage());
			List<GroupMemberInfo> memberInfoList=result.getGroupMemberInfo();
			for(GroupMemberInfo info:memberInfoList){
				logger.info(" ====== getGroupMemberName ======" + info.getGroupMemberName());
				logger.info(" ====== getGroupMemberMsidsn ======" + info.getGroupMemberMsidsn());
				logger.info(" ====== getDesc ======" + info.getDesc());
				logger.info(" ====== getGroupMemberStartDate ======" + info.getGroupMemberStartDate());
				logger.info(" ====== getChannelId ======" + info.getChannelId());
			}
				
		}
	}
}
