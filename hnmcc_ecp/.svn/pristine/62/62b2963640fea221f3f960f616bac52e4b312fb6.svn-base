package com.xwtech.xwecp.transport.handler;

import com.xwtech.xwecp.communication.transport.ResponseMessageQueue;
import com.xwtech.xwecp.transport.message.NioSocketClient;
import com.xwtech.xwecp.transport.util.ConnectStateEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发送消息的时机：
 * 发送消息应该在sessionOpened()、messageReceived()方法中调用IoSession.write()方法完成。
 * 因为在sessionOpened()方法中，TCP 连接已经真正打开，同样的在messageReceived()方法TCP 连接也是打开状态，只不过两者的时机不同。
 * sessionOpened()方法是在TCP 连接建立之后，接收到数据之前发送；messageReceived()方法是在接收到数据之后发送，你可以完成依据收到的内容是什么样子，决定发送什么样的数据。
 * 因为这个接口中的方法太多，因此通常使用适配器模式IoHandlerAdapter，覆盖你所感兴趣的方法即可
 * @author Administrator
 *
 */
public class NioClientIOHandler extends IoHandlerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(NioClientIOHandler.class);

	private static String bodyMsg = "";
    private static String SPLIT_TAB = "\n";
	
	private NioSocketClient callBack;
	public NioClientIOHandler(NioSocketClient nioClient){
		callBack=nioClient;
	}

	/**
	 * 这个方法在你的程序,Mina 自身出现异常时回调，一般这里是关闭IoSession
	 */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
        logger.info("++++++NioClient++++++[ connect or minaself or net exception ]",cause);
        logger.info("++++++NioClient++++++[ connect or minaself or net exception ]"+Thread.currentThread().getName());
        callBack.setConstate(ConnectStateEnum.LINKERR);
    }

    /**
     * 接收到消息时调用的方法，也就是用于接收消息的方法，一般情况下，message 是一个IoBuffer 类，如果你使用了协议编解码器，那么可以强制转换为你需要的类型。
     * 通常我们都是会使用协议编解码器的， 比如协议编解码器是TextLineCodecFactory，所以我们可以强制转message 为String 类型
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
    	//TODO 通道正常之后处理下无受理权限的情况


		logger.info("++++++NioClient++++++[ messageReceived ]" + message.toString());
        String msg = message.toString();
        if(StringUtils.isNotBlank(msg)){
        	//1-最后一包 0-还有后续包
        	if("1".equals(msg.substring(57,58))){
        		if(StringUtils.isNotBlank(bodyMsg)){
        			if(msg.length()>120){
        				bodyMsg = bodyMsg +SPLIT_TAB+ new String(msg.getBytes("GBK"),120,msg.getBytes("GBK").length-120);
        				ResponseMessageQueue.getInstance().add(bodyMsg);
        				bodyMsg = "";
        			}else{
        				ResponseMessageQueue.getInstance().add(msg);
        			}
        		}else{
        			ResponseMessageQueue.getInstance().add(msg);
        		}
        		
        	}else{
        		if("001".equals(msg.substring(52, 55))){
        			bodyMsg = msg;
        		}else{
        			if(msg.length()>120){
        				bodyMsg= bodyMsg+SPLIT_TAB + new String(msg.getBytes("GBK"),120,msg.getBytes("GBK").length-120);
        			}
        		}
        	}
        }
    }

    /**
     * 当发送消息成功时调用这个方法，注意这里的措辞，发送成功之后，也就是说发送消息是不能用这个方法的
     */
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
    	logger.info("++++++NioClient++++++[ messageSent方法执行 ]");
    }

    /**
     * 对于TCP 来说，连接被关闭时，调用这个方法。
     * 对于UDP 来说，IoSession 的close()方法被调用时才会毁掉这个方法
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
    	logger.info("++++++NioClient++++++[ sessionClosed方法执行 ]");
    	callBack.setConstate(ConnectStateEnum.NO_CONNECT);
    }

    /**
     * 这个方法当一个Session 对象被创建的时候被调用。
     * 对于TCP 连接来说，连接被接受的时候调用，但要注意此时TCP 连接并未建立，此方法仅代表字面含义，也就是连接的对象IoSession 被创建完毕的时候，回调这个方法。
     * 对于UDP 来说，当有数据包收到的时候回调这个方法，因为UDP 是无连接的
     */
    @Override
    public void sessionCreated(IoSession session) throws Exception {
    	logger.info("++++++NioClient++++++[ sessionCreated方法执行 ]");
    }

    /**
     * 这个方法在IoSession 的通道进入空闲状态时调用。
     * 对于UDP 协议来说，这个方法始终不会被调用
     */
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
    	logger.info("++++++NioClient++++++[ sessionIdle send sgip_unbind方法执行 ]");
    }

    /**
     * 这个方法在连接被打开时调用，它总是在sessionCreated()方法之后被调用。
     * 对于TCP 来说，它是在连接被建立之后调用，你可以在这里执行一些认证操作、发送数据等。
     * 对于UDP 来说，这个方法与sessionCreated()没什么区别，但是紧跟其后执行。
     * 如果你每隔一段时间，发送一些数据，那么sessionCreated()方法只会在第一次调用，但是sessionOpened()方法每次都会调用
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
    	logger.info("++++++NioClient++++++[ sessionOpened方法执行 ]");
    	logger.info("++++++NioClient++++++[ 连接已经建立 ]"+Thread.currentThread().getName());
    	
    	callBack.setConstate(ConnectStateEnum.CONNECTED);    	
    }
}
