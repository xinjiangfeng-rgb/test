package com.xwtech.xwecp.transport.message;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.transport.util.ConnectStateEnum;
import com.xwtech.xwecp.transport.util.NioClientConfig;

public class NioClientPool {
	
	private static final Logger logger = LoggerFactory.getLogger(NioClientPool.class);
	private NioClientConfig config = NioClientConfig.getInstance();
	private NioSocketClient[] clients = null;
	public List<NioSocketClient> canUse = new ArrayList<NioSocketClient>();//能使用的连接
	public List<NioSocketClient> canNotUse = new ArrayList<NioSocketClient>();//不能使用的连接
	public boolean initFlag = false;

	private static volatile NioClientPool instance = null;
	
	public static NioClientPool getInstance(){
		if(null == instance){
			synchronized (NioClientPool.class) {
				if(null == instance){
					instance = new NioClientPool();
				}
			}
		}
		return instance;
	}
	
	/**
	 * 初始化连接池
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public boolean init() {
		clients = new NioSocketClient[config.getClientPoolSize()];
		for (int i = 0; i < config.getClientPoolSize(); i++) {
			//初始化连接池之后需要每个client鉴权
			clients[i] = new NioSocketClient();
			boolean reBoolean = clients[i].init();
			if(reBoolean){
				canUse.add(clients[i]);
			}else{
				canNotUse.add(clients[i]);
			}
		}
		if(canUse.size() >0){
			initFlag = true;//初始化成功
//			logger.info("+++++++NioClient+++++[ Pool init success ]"+canUse.size());
		}
		return initFlag;
	}
	
	/** 
     * 获得一个可用连接，不保证任何情况都能返回一个连接（比如超过最大连接数的时候返回null） 
     * 1、若空闲连接池不为空，从空闲连接池取出一个连接后检查有效性，正常则返回，失效则重新获取连接 
     * 2、若空闲连接池为空且未超过最大连接数限制，新建一个连接并返回 
     * 3、空闲连接数为空且超过最大连接数限制，返回null 
     * @return  获得的可用连接 
     */
	public synchronized NioSocketClient getConnection2() {
		NioSocketClient s = null;
		for(int i=0;i<canUse.size();i++){
			s = canUse.get(i);
			if(s.constate == ConnectStateEnum.NO_CONNECT){
				continue;
			}
            if(s.sendMsgList.size()>=config.getSendSize()) {  
                continue;
            }
            break;
		}
		return s;
	}
	
	/**
	 * 返回一个连接--废弃
	 * @return a Connection object.
	 * @throws IOException
	 */
	public synchronized NioSocketClient getConnection() {
		NioSocketClient s = new NioSocketClient();
		for(int i=0;i<clients.length-1;i++){
			NioSocketClient temp = new NioSocketClient();
			for(int j=0;j<clients.length-1-i;j++){
				if(clients[j].sendMsgList.size()>clients[j+1].sendMsgList.size()){
					temp=clients[j];
					clients[j]=clients[j+1];
					clients[j+1]=temp;
				}
			}
		}
		s = clients[0];
		return s;
	}
	
	/**
	 * 释放socket连接池--废弃
	 * @return
	 */
	public boolean resetPool(){
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	
	public NioClientConfig getConfig() {
		return config;
	}

	public void setConfig(NioClientConfig config) {
		this.config = config;
	}
}
