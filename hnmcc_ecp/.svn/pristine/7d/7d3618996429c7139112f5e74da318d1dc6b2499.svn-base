/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.xwtech.xwecp.communication.transport;

import static org.jboss.netty.channel.Channels.pipeline;

import java.nio.charset.Charset;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import com.xwtech.xwecp.communication.pack.MNFDelimiters;

/**
 * Creates a newly configured {@link org.jboss.netty.channel.ChannelPipeline} for a new channel.
 */
public class TransportPipelineFactory implements ChannelPipelineFactory
{
    
    public ChannelPipeline getPipeline()
        throws Exception
    {
        // Create a default pipeline implementation.
        ChannelPipeline pipeline = pipeline();
        
        // Add the text line codec combination first,
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(10000000, MNFDelimiters.MNFDelimiter()));
        
        pipeline.addLast("decoder", new StringDecoder(Charset.forName("GBK")));
        pipeline.addLast("encoder", new StringEncoder(Charset.forName("GBK")));
        
        // and then business logic.
        pipeline.addLast("handler", new TransportHandler());
        
        return pipeline;
    }
}
