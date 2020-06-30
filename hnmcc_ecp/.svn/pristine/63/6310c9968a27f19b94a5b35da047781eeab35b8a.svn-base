package com.xwtech.xwecp.communication.pack;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * User: yuanshoujing@gmail.com Date: 12-8-29 Time: 下午5:11
 */
public final class MNFDelimiters
{
    
    /**
     * 以MNF为包结束标记
     * 
     * @return
     */
    public static ChannelBuffer[] MNFDelimiter()
    {
        ChannelBuffer[] result = new ChannelBuffer[] {ChannelBuffers.wrappedBuffer(new byte[] {'M', 'N', 'F'})};
        return result;
    }
    
    private MNFDelimiters()
    {
        // Unused
    }
    
}
