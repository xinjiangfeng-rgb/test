package com.xwtech.xwecp.transport.util;

import com.xwtech.xwecp.XWECPApp;

public class NioClientConfig {
	private int recvBufferLen;//没用暂时没有设置
	private String host;//CRM主机ip
	private int port;//CRM主机端口
	private int clientPoolSize;//默认NioClient连接池大小为20
	private int sendSize;//每个连接允许的发送消息容量
	private int heartInterval;//心跳包发送间隔时长，秒
	private int heartTimeout;//心跳包超时时间，秒
	private long lazyCheck;// 守护线程延迟多少时间后开始检查(毫秒)
	private long periodCheck;// 检查频率(毫秒)

	private static NioClientConfig instance;
	public static synchronized NioClientConfig getInstance(){
		if(instance==null)
			instance=(NioClientConfig)XWECPApp.SPRING_CONTEXT.getBean("nioClientConfig");
		return instance;
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getClientPoolSize() {
		return clientPoolSize;
	}
	public void setClientPoolSize(int clientPoolSize) {
		this.clientPoolSize = clientPoolSize;
	}
	public int getHeartInterval() {
		return heartInterval;
	}
	public void setHeartInterval(int heartInterval) {
		this.heartInterval = heartInterval;
	}
	public int getRecvBufferLen() {
		return recvBufferLen;
	}
	public void setRecvBufferLen(int recvBufferLen) {
		this.recvBufferLen = recvBufferLen;
	}
	public int getSendSize() {
		return sendSize;
	}
	public void setSendSize(int sendSize) {
		this.sendSize = sendSize;
	}
	public long getLazyCheck() {
		return lazyCheck;
	}

	public void setLazyCheck(long lazyCheck) {
		this.lazyCheck = lazyCheck;
	}

	public long getPeriodCheck() {
		return periodCheck;
	}

	public void setPeriodCheck(long periodCheck) {
		this.periodCheck = periodCheck;
	}
	public int getHeartTimeout() {
		return heartTimeout;
	}

	public void setHeartTimeout(int heartTimeout) {
		this.heartTimeout = heartTimeout;
	}
}
