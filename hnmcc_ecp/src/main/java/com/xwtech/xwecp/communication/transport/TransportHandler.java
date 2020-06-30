package com.xwtech.xwecp.communication.transport;

import org.apache.commons.lang3.StringUtils;
import org.jboss.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Handles a client-side channel.
 */
public class TransportHandler extends SimpleChannelUpstreamHandler
{
    
    private static final Logger logger = LoggerFactory.getLogger(TransportHandler.class.getName());
    private static String bodyMsg = "";
    static String SPLIT_TAB = "\n";
    @Override
    public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e)
        throws Exception
    {
        if (e instanceof ChannelStateEvent)
        {
            logger.info(e.toString());
        }
        super.handleUpstream(ctx, e);
    }
    
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws UnsupportedEncodingException
    {
        logger.info("******* messageReceived ********** " + e.getMessage());
        String msg = e.getMessage().toString();
        //logger.info("==============111222=========================="+msg.length());
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
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
    {
        logger.warn(  "Unexpected exception from downstream.", e.getCause());
        e.getChannel().close();
    }
    
    /**
    *
    * @param text 目标字符串
    * @param length 截取长度
    * @param encode 采用的编码方式
    * @return
    * @throws UnsupportedEncodingException
    */
   public String substring(String text, int length, String encode)
     throws UnsupportedEncodingException {
    if (text == null) {
     return null;
    }
    StringBuilder sb = new StringBuilder();
    int currentLength = 0;
    for (char c : text.toCharArray()) {
     currentLength += String.valueOf(c).getBytes(encode).length;
     if (currentLength <= length) {
      sb.append(c);
     } else {
      break;
     }
    }
    return sb.toString();

   }
}
