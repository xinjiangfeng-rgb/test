package com.iman.cbh.bossinterface.transport;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.communication.transport.TransportPipelineFactory;

public class Transport {
	private static final Logger log = LoggerFactory.getLogger(Transport.class);

	  private String host = "localhost";

	  private int port = 8080;

	  public Transport(String host, int port)
	  {
	    this.host = host;
	    this.port = port;
	  }

	  public void start()
	    throws IOException, InterruptedException
	  {
	    ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(
	      Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

	    bootstrap.setPipelineFactory(new TransportPipelineFactory());

	    ChannelFuture future = bootstrap.connect(new InetSocketAddress(this.host, this.port)).sync();

	    Channel channel = future.awaitUninterruptibly().getChannel();
	    if (!future.isSuccess())
	    {
	      future.getCause().printStackTrace();
	      bootstrap.releaseExternalResources();
	      return;
	    }
	ChannelFuture lastWriteFuture = null;
	    String msg;
	    do {
	      while (true) { msg = MessageQueue.getInstance().poll();
	        if (msg != null)
	          break;
	        Thread.sleep(500L);
	      }

	      log.info("==> 准备发送消息：" + msg);

	      lastWriteFuture = channel.write(msg + "MNF");
	      log.info("==> 完成发送消息：" + msg);
	    }

	    while (!msg.toLowerCase().equals("bye"));

	    channel.getCloseFuture().awaitUninterruptibly();

	    log.info("****************messageReceived2 **************** " + channel.getAttachment());
	  }
}
