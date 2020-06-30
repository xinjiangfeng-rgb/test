//package com.xwtech.xwecp.communication.transport;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.util.concurrent.Executors;
//
//import org.apache.commons.lang3.math.NumberUtils;
//import org.jboss.netty.bootstrap.ClientBootstrap;
//import org.jboss.netty.channel.Channel;
//import org.jboss.netty.channel.ChannelFuture;
//import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
//
//import com.xwtech.xwecp.util.ConfigurationRead;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * User: yuanshoujing@gmail.com Date: 12-8-29 Time: 下午4:39
// */
//public class Transport
//{
//    private static final Logger log = LoggerFactory.getLogger(Transport.class);
//
//    private static String host = ConfigurationRead.getInstance().getValue("crm_ip");
//
//    private static int port = NumberUtils.toInt(ConfigurationRead.getInstance().getValue("crm_port"), 38711);
//
//    private static Channel channel = null;
//
//    public static void start()
//        throws IOException, InterruptedException
//    {
//        ResponseMessageQueue.getInstance().clear();
//
//        ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(
//            Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
//
//        bootstrap.setPipelineFactory(new TransportPipelineFactory());
//        ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port));
//
//        // 等待连接成功，否则关闭
//        // todo 断线重连机制
//        channel = future.awaitUninterruptibly().getChannel();
//        if (!future.isSuccess())
//        {
//            future.getCause().printStackTrace();
//            bootstrap.releaseExternalResources();
//            return;
//        }
//
//        // ChannelFuture lastWriteFuture = null;
//        // for (;;)
//        // {
//        // String msg = MessageQueue.getInstance().poll();
//        // if (msg == null)
//        // {
//        // Thread.sleep(500);
//        // continue;
//        // }
//        //
//        // log.info("==> 准备发送消息：" + msg);
//        // // 发送消息到服务器
//        // lastWriteFuture = channel.write(msg + "MNF");
//        // log.info("==> 完成发送消息：" + msg);
//        //
//        // // If user typed the 'bye' command, wait until the server closes
//        // // the connection.
//        // if (msg.toLowerCase().equals("bye"))
//        // {
//        // channel.getCloseFuture().awaitUninterruptibly();
//        // break;
//        // }
//        // }
//
//        // 所有消息均发出后，关闭通道
//        // if (lastWriteFuture != null) {
//        // lastWriteFuture.awaitUninterruptibly();
//        // }
//
//        // Close the connection. Make sure the close operation ends because
//        // all I/O operations are asynchronous in Netty.
//        // channel.close().awaitUninterruptibly();
//
//        // Shut down all thread pools to exit.
//        // bootstrap.releaseExternalResources();
//    }
//
//    public static void send(String massage)
//        throws IOException, InterruptedException
//    {
//        if (null == channel || !channel.isConnected())
//        {
//            start();
//        }
//
//        log.info("==> 准备发送消息：" + massage);
//        // 发送消息到服务器
//        ChannelFuture lastWriteFuture = channel.write(massage);
//        log.info("==> 完成发送消息：" + massage);
//    }
//}
