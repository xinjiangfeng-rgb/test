package com.xwtech.xwecp.communication;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.xwtech.xwecp.communication.transport.ResponseMessageQueue;
import com.xwtech.xwecp.transport.message.NioClientPool;
import com.xwtech.xwecp.transport.message.NioSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketCommunicator implements ICommunicator
{
    private static final Logger logger = LoggerFactory.getLogger(SocketCommunicator.class);
    
    private static final String CHK_LOGIN = "MHF0000000000000001100502HNYD00XZT01 20120830200128009    139     FFFF                                          FFFFFFFFYzzzzXBZT\tr48<Q6MNF";
    
    //业务办理，详单查询等类型分类
    private static final List<String> BUS_TYPE = new ArrayList<String>(){
    	{
    		add("940032");//通话详单查询
    		add("940034");//上网类详单查询
    		add("940033");//短彩信详单查询
    		add("912114");//业务查询
    		add("929206");//业务办理
    		add("930021");//业务办理
    		add("400058");//集团业务预约接口
    		add("200828");//流量日查询
    		add("200903");//流量月查询
    		add("912114");//已开业务
    		add("920048");//充值记录
    		add("920202");//业务办理记录
    		add("930018");//业务退订
    		add("920021");//积分M值兑换记录接口
    		add("929206");//营销活动接口
    		add("100802");//梦网退订/订购
    		add("201503");//流量红包赠送
    		add("150901");//集团统付
    		add("200005");//营销活动能用费用余额查询
    		add("200605");//营销活动预校验接口
    		add("830014");//免费资源赠送接口
    	}
    };
    
    private String remoteURL;
    
    // 通过网络与服务器建立连接的超时时间
    private int connectionTimeout = 5000;
    
    // Socket读数据的超时时间
    private int soTimeout = 15000;
    
    // 全局最多保持的连接数
    private int maxThreads = 100;
    
    // 每主机可保持连接的连接数，默认情况不指定主机，所以所有连接都是共用的默认主机
    private int maxConnectionsPerHost = 100;
    
    private String encoding = "UTF-8";
    
    private String keepAlive = "false";
    
    private String usePool = "false";
    
    public String getRemoteURL()
    {
        return remoteURL;
    }
    
    public void setRemoteURL(String remoteURL)
    {
        this.remoteURL = remoteURL;
    }
    
    public int getConnectionTimeout()
    {
        return connectionTimeout;
    }
    
    public void setConnectionTimeout(int connectionTimeout)
    {
        this.connectionTimeout = connectionTimeout;
    }
    
    public int getSoTimeout()
    {
        return soTimeout;
    }
    
    public void setSoTimeout(int soTimeout)
    {
        this.soTimeout = soTimeout;
    }
    
    public int getMaxThreads()
    {
        return maxThreads;
    }
    
    public void setMaxThreads(int maxThreads)
    {
        this.maxThreads = maxThreads;
    }
    
    public int getMaxConnectionsPerHost()
    {
        return maxConnectionsPerHost;
    }
    
    public void setMaxConnectionsPerHost(int maxConnectionsPerHost)
    {
        this.maxConnectionsPerHost = maxConnectionsPerHost;
    }
    
    public String getEncoding()
    {
        return encoding;
    }
    
    public void setEncoding(String encoding)
    {
        this.encoding = encoding;
    }
    
    public String getKeepAlive()
    {
        return keepAlive;
    }
    
    public void setKeepAlive(String keepAlive)
    {
        this.keepAlive = keepAlive;
    }
    
    public String getUsePool()
    {
        return usePool;
    }
    
    public void setUsePool(String usePool)
    {
        this.usePool = usePool;
    }
    
    @Override
    public byte[] send(byte[] data, String channel)
        throws CommunicateException
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public String send(String data, String channel)
        throws CommunicateException
    {
        return null;
    }
    
    public String send2CRM(String data, String channelCode)
        throws CommunicateException
    {
        // MessageQueue.getInstance().add(CHK_LOGIN);
        // MessageQueue.getInstance().add(data);

    	NioSocketClient client = null;
        try
        {
            //Transport.send(CHK_LOGIN);
            //Transport.send(data);
        	NioClientPool pool = NioClientPool.getInstance();
        	if(pool.initFlag){
        		client = pool.getConnection2();
        		if(null == client){
        			return "";
        		}
            	client.sendMsg(data);
        	}else{
        		//未初始化或者初始化失败直接返回超时报文
        		return "";
        	}
        }
        catch (Exception e)
        {
            logger.error("*********　请求CRM接口失败**********", e);
        }
        
//        try
//        {
//        	Thread.sleep(1000);
//        }
//        catch (InterruptedException e)
//        {
//        	logger.error("******等待CRM消息异常********", e);
//        }
        
        String key = new String(data.getBytes(), 0, 25);
//        String[] temp;
        String rspMsg = null;
        String bossPort = new String(key.getBytes(),19,6);
//        StringBuffer sb;
        int count = 0,fp=1;
        int whileCount = 200;//5秒超时
        if(StringUtils.isNotBlank(bossPort)&& BUS_TYPE.contains(bossPort)){
        	whileCount = 300;//详单查询，业务办理之类业务30秒超时间
        }
        System.out.println("time out ------"+whileCount);
        while (count < whileCount)
        {
        	rspMsg = ResponseMessageQueue.getInstance().getMsg(key);
        	//logger.error("***********yjqtest****["+key+"]****yjqvalue*****["+rspMsg+"]****count:"+count);
//        	if(StringUtils.isNotBlank(rspMsg)){
//        		fp = Integer.parseInt(rspMsg.substring(52, 55));
//        		System.out.println("----------------------------------"+fp);
//        	}        	
//        	//处理多个分片响应
//        	//MHF2015042215211886940032XZT01 HNYD00201504221521210001  0204482500000调用成功                                  FFFFFFFF2015-01-14 17:26:23    郑州 主叫     13520306154     5分8秒  省际长途        赠送通话费      赠送通话费:1.14元       0.00
//        	//MHF2015042215211886940032XZT01 HNYD00201504221521210002  0204482500000调用成功                                  FFFFFFFF2015-01-14 17:26:23    郑州 主叫     13520306154     5分8秒  省际长途        赠送通话费      赠送通话费:1.14元       0.00
//        	//MHF2015042215211886940032XZT01 HNYD00201504221521210003  13110 37 0000调用成功                                  FFFFFFFF2015-01-29 17:37:10    郑州 主叫     18300680668     12秒    本地    4G飞享套餐-78元A套餐            0.19
//        	if(fp>1){
//        		temp = new String[fp];
//            	for(int i=fp-2;i>=0;i--){
//            		temp[i]=ResponseMessageQueue.getInstance().getMsg(key);
//            		System.out.println("--------------------1--------------------"+temp[i].length());
//            	}
//            	temp[fp-1] = rspMsg;
//            	sb = new StringBuffer();
//            	for(int i=0 ;i<temp.length;i++){            		
//            		if(StringUtils.isNotBlank(temp[i])){
//            			if(i!=0||StringUtils.isBlank(String.valueOf(sb))){
//            				sb.append(temp[i]);
//            				System.out.println("--------------------4--------------------"+temp[i].length());
//            			}else{
//            				System.out.println("--------------------3--------------------"+temp[i].length());
//            				if(temp[i].length()>120)
//            				sb.append(temp[i].substring(120));
//            			}
//            		}
//            	}
//            	rspMsg = String.valueOf(sb);
//        	}
            if (StringUtils.isEmpty(rspMsg))
            {
            	count++;
            	try
                {
                	Thread.sleep(50);
                }
                catch (InterruptedException e)
                {
                	logger.error("******等待CRM消息异常********", e);
                }
                continue;
            }
            break;
        }
        client.delSendMsg(data);
        
        return rspMsg;
    }
}
