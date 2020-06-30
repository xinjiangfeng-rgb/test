package com.xwtech.xwecp.transport.keepalive;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.transport.util.MergeTeletext;

public class ClientKeepAliveFactoryImpl implements KeepAliveMessageFactory{
	private static final Logger logger = LoggerFactory.getLogger(ClientKeepAliveFactoryImpl.class);

	// 获取心跳请求包 non-null(发送心跳包)
	@Override
	public Object getRequest(IoSession session) {
		List<RequestParameter> param = new ArrayList<RequestParameter>();
    	String temp = "MHF${request_seq}100601HNYD00XZT01 ${request_time}009    127     FFFF                                          FFFFFFFFHBHBMNF";
    	String message = MergeTeletext.mergeTeletext(temp, param);
    	//String message = "MHF2222222222222222100601HNYD00XZT01 20090716054127009    127     FFFF                                          FFFFFFFFHBHBMNF";
    	session.write(message);
//    	logger.info("+++++++NioClient+++++[ Heart beat request ]"+message);
		return null;
	}

	//服务器不会给客户端发送心跳请求，客户端当然也不用反馈  该方法返回null
	@Override
	public Object getResponse(IoSession session, Object message) {
		// TODO Auto-generated method stub
		return null;
	}

	//服务器不会给客户端发送请求包，因此不关注请求包，直接返回false
	@Override
	public boolean isRequest(IoSession session, Object message) {
		// TODO Auto-generated method stub
		return false;
	}

	//客户端关注请求反馈，因此判断mesaage是否是反馈包(心跳包响应)
	@Override
	public boolean isResponse(IoSession session, Object message) {
		if(null == message || "".equals(message)){
			return false;
		}
		String rspMsg = message.toString();
		String resCode = new String(rspMsg.getBytes(),66,4);
		String resultCode = new String(rspMsg.getBytes(),19,6);
		if("100601".equals(resultCode) && "0000".endsWith(resCode)){
//			logger.info("+++++++NioClient+++++[ Heart beat response ]"+rspMsg);
			return true;
		}
		return false;
	}

}
