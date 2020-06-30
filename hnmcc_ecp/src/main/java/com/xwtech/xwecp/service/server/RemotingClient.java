package com.xwtech.xwecp.service.server;

import com.asiainfo.openplatform.isb.remote.access.interfaces.IRemoteAccess;
import com.xwtech.xwecp.util.ConfigurationRead;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.jboss.remoting.transporter.TransporterClient;

/**
 * remote调用客户端单例类
 * 项目启动的时候创建客户端对象
 * 初始化方法提供给守护进程使用(项目启动之后每隔10秒钟判断下链接是否可用)
 * Created by Administrator on 2016/11/18.
 */
public class RemotingClient {
    private static final Logger logger = LoggerFactory.getLogger(RemotingClient.class);
    private static final String REMOTING_URL = ConfigurationRead.getInstance().getValue("remoting_url");
    private static volatile RemotingClient instance;
    private IRemoteAccess ra;

    public IRemoteAccess getRa() {
        return ra;
    }

    private RemotingClient(){}

    public static RemotingClient getInstance(){
        if(null == instance){
            synchronized (RemotingClient.class) {
                if(null == instance){
                    instance = new RemotingClient();
                    logger.info("******* 调用能开的的请求地址是********"+REMOTING_URL);
                }
            }
        }
        return instance;
    }

    public void init(){
        try {
            ra = (IRemoteAccess)TransporterClient.createTransporterClient(REMOTING_URL, IRemoteAccess.class);

        } catch (Exception e) {
            logger.error("Remoting初始化客户端异常！");
            e.printStackTrace();
        }
    }
}
