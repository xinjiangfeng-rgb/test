package com.xwtech.xwecp.communication;

import com.asiainfo.openplatform.isb.remote.access.interfaces.IRemoteAccess;
import com.xwtech.xwecp.service.server.RemotingClient;
import com.xwtech.xwecp.util.ConfigurationRead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JbossRemotingCommunicator implements ICommunicator
{
    private static final Logger logger = LoggerFactory.getLogger(JbossRemotingCommunicator.class);
    
    private static final String CHK_LOGIN = "MHF0000000000000001100502HNYD00XZT01 20120830200128009    139     FFFF                                          FFFFFFFFYzzzzXBZT\tr48<Q6MNF";
    private static final String REMOTING_URL = ConfigurationRead.getInstance().getValue("remoting_url");
    private static final String HTTPS_URL = ConfigurationRead.getInstance().getValue("https_url");
//    //业务办理，详单查询等类型分类
//    private static final List<String> BUS_TYPE = new ArrayList<String>(){
//    	{
//    		add("940032");//通话详单查询
//    		add("940034");//上网类详单查询
//    		add("940033");//短彩信详单查询
//    		add("912114");//业务查询
//    		add("929206");//业务办理
//    		add("930021");//业务办理
//    		add("400058");//集团业务预约接口
//    		add("200828");//流量日查询
//    		add("200903");//流量月查询
//    		add("912114");//已开业务
//    		add("920048");//充值记录
//    		add("920202");//业务办理记录
//    		add("930018");//业务退订
//    		add("920021");//积分M值兑换记录接口
//    		add("929206");//营销活动接口
//    		add("100802");//梦网退订/订购
//    		add("201503");//流量红包赠送
//    		add("150901");//集团统付
//    		add("200005");//营销活动能用费用余额查询
//    		add("200605");//营销活动预校验接口
//    		add("830014");//免费资源赠送接口
//    	}
//    };
    
//    private String remoteURL;
//
//    // 通过网络与服务器建立连接的超时时间
//    private int connectionTimeout = 5000;
//
//    // Socket读数据的超时时间
//    private int soTimeout = 15000;
//
//    // 全局最多保持的连接数
//    private int maxThreads = 100;
//
//    // 每主机可保持连接的连接数，默认情况不指定主机，所以所有连接都是共用的默认主机
//    private int maxConnectionsPerHost = 100;
//
//    private String encoding = "UTF-8";
//
//    private String keepAlive = "false";
//
//    private String usePool = "false";
    
//    public String getRemoteURL()
//    {
//        return remoteURL;
//    }
//
//    public void setRemoteURL(String remoteURL)
//    {
//        this.remoteURL = remoteURL;
//    }
//
//    public int getConnectionTimeout()
//    {
//        return connectionTimeout;
//    }
//
//    public void setConnectionTimeout(int connectionTimeout)
//    {
//        this.connectionTimeout = connectionTimeout;
//    }
//
//    public int getSoTimeout()
//    {
//        return soTimeout;
//    }
//
//    public void setSoTimeout(int soTimeout)
//    {
//        this.soTimeout = soTimeout;
//    }
//
//    public int getMaxThreads()
//    {
//        return maxThreads;
//    }
//
//    public void setMaxThreads(int maxThreads)
//    {
//        this.maxThreads = maxThreads;
//    }
//
//    public int getMaxConnectionsPerHost()
//    {
//        return maxConnectionsPerHost;
//    }
//
//    public void setMaxConnectionsPerHost(int maxConnectionsPerHost)
//    {
//        this.maxConnectionsPerHost = maxConnectionsPerHost;
//    }
//
//    public String getEncoding()
//    {
//        return encoding;
//    }
//
//    public void setEncoding(String encoding)
//    {
//        this.encoding = encoding;
//    }
//
//    public String getKeepAlive()
//    {
//        return keepAlive;
//    }
//
//    public void setKeepAlive(String keepAlive)
//    {
//        this.keepAlive = keepAlive;
//    }
//
//    public String getUsePool()
//    {
//        return usePool;
//    }
//
//    public void setUsePool(String usePool)
//    {
//        this.usePool = usePool;
//    }
//
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
        throws CommunicateException {
        String rspMsg = null;
        logger.info("******* 调用能开的的请求参数是data ********"+data);
        logger.info("******* 调用能开的的请求参数是channelCode ********"+channelCode);
        try {
            RemotingClient r = RemotingClient.getInstance();

            IRemoteAccess ra = r.getRa();
            if(null == ra){
                logger.info("******* 获取远程调用对象为空 ********");
                return "";
            }else{
                rspMsg = ra.service(data,channelCode);

                logger.info("******* 调用能开的的请求参数是rspMsg ********"+rspMsg);
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("*********　请求CRM接口失败**********", e);
        }
        
        return rspMsg;
    }





}
