package com.xwtech.xwecp.web.listener;

import com.asiainfo.openplatform.isb.remote.access.interfaces.IRemoteAccess;
import com.xwtech.xwecp.AppConfig;
import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.pojo.NumberSegment;
import com.xwtech.xwecp.service.server.RemotingClient;
import com.xwtech.xwecp.transport.message.NioClientPool;
import com.xwtech.xwecp.transport.message.NioSocketClient;
import com.xwtech.xwecp.transport.util.ConnectStateEnum;
import com.xwtech.xwecp.transport.util.NioClientConfig;
import com.xwtech.xwecp.web.XWECPWebApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ApplicationContextListener implements ServletContextListener
{
    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextListener.class);
    
    public void contextDestroyed(ServletContextEvent arg0)
    {
        logger.info("XWECP关闭!");
    }
    
    public void contextInitialized(ServletContextEvent arg0)
    {
//
//        String sql = "select phone from T_ECP_WHITE where status=0 ";
//
//        System.out.println(""sql);
        String realPath = arg0.getServletContext().getRealPath("");
        if (!realPath.endsWith("/"))
        {
            realPath += "/";
        }
        
        XWECPApp.APP_PATH = realPath;
//        File log4jConfig = new File(realPath, "WEB-INF/classes/log4j_ecp.properties");
////        /* redis的地址配置文件 */
////        File redisConfig = new File(realPath, "WEB-INF/cfg/server.xml");
////        XWECPApp.redisCli = new RedisClient();
////        XWECPApp.redisCli.init(redisConfig);
//
//        URL url = null;
//        try
//        {
//            url = log4jConfig.toURI().toURL();
//            PropertyConfigurator.configure(url);
//        }
//        catch (MalformedURLException e)
//        {
//            e.printStackTrace();
//        }
        
        XWECPWebApp.CONTEXT_NAME = arg0.getServletContext().getServletContextName();
        logger.info("XWECPWebApp.CONTEXT_NAME = " + XWECPWebApp.CONTEXT_NAME);
        XWECPWebApp.SPRING_CONTEXT = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
        // 根据配置确定是否启动日志持久化线程
        XWECPWebApp.CDR_DIRECT2DB = AppConfig.getConfigValue("logConfig", "cdrDirect2DB");
        XWECPWebApp.IS_RECORD_RIGHT = AppConfig.getConfigValue("logConfig", "isRecordRight");
        // 获取号段表数据，接口用户登录、查询用户资料、查询归属地根据地市来请求报文
        XWECPWebApp.NUMBERSEGMENTS = getNumberSegment();
        logger.info("号段记录数：" + XWECPWebApp.NUMBERSEGMENTS.size());
        
        // new Thread(new Runnable()
        // {
        // public void run()
        // {
        // try
        // {
        // new Transport().start();
        // }
        // catch (Exception e)
        // {
        // logger.error("******* 初始化CRM接口失败，系统自动退出*********", e);
        // }
        // }
        // }).start();
        
        // try {
        // 每1分钟执行一次
        // JobDispatchUtil.addJob("释放超时的连接请求", new ReleaseInvalidConnnectJob(), "0 0/1 * * * ?");
        // 每1分钟执行一次
        // JobDispatchUtil.addJob("清空渠道流量统计数据", new RemoveChannelFlowJob(), "0 0/1 * * * ?");
        // 每2秒钟执行一次
        // JobDispatchUtil.addJob("汇总渠道流量统计数据", new SaveChannelFlowJob(), "0/1 * * * * ?");
        // } catch (SchedulerException e) {
        // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (ParseException e) {
        // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        
//        //在此初始化socket连接池，并对每一个连接进行鉴权。如果有连接不成功或者鉴权失败的就另起线程去连接并鉴权
//        NioClientPool pool = NioClientPool.getInstance();
//    	pool.init();
        
        //守护线程定时处理20个socket连接
        NioClientConfig config = NioClientConfig.getInstance();
        new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				NioClientPool pool = NioClientPool.getInstance();
//                logger.info("***********yjqcanuse能用的连接数:"+pool.canUse);
//                logger.info("***********yjqcanuse不能用的连接数:"+pool.canNotUse);
				if(!pool.initFlag){
//                    logger.info("**************yjqone*******初始化socket连接");
					List<NioSocketClient> canNotUse = pool.canNotUse;
                    if(null != canNotUse && canNotUse.size()>0){
                        for(int i=0;i<canNotUse.size();i++){
                            NioSocketClient nsc = canNotUse.get(i);
                            nsc.close();
                        }
                        canNotUse.clear();
                    }
					//做初始化工作
					pool.init();
				}else{
					//如果初始化工作完成，就开始开始监控不可用连接列表，完成重连工作
					List<NioSocketClient> canNotUse = pool.canNotUse;
					for(int i=0;i<canNotUse.size();i++){
						NioSocketClient nsc = canNotUse.get(i);
                        nsc.closeSession();
                        boolean connBo = nsc.reConnect();//重连
                        boolean loginBo = false;
                        if(connBo){
                            loginBo = nsc.getLoginMessage();//登录
                        }
                        if(connBo==true && loginBo==true){
                            pool.canUse.add(nsc);
                            pool.canNotUse.remove(nsc);
                        }
                        //如果连接成功但是登录失败，把连接断掉,mina客户端销毁掉(关掉文件句柄)
                        if(connBo==true && loginBo==false){
                            nsc.close();
                        }
					}
					//如果可用连接中的状态异常，也发起重连
					List<NioSocketClient> canUse = pool.canUse;
					for(int i=0;i<canUse.size();i++){
						NioSocketClient nsc = canUse.get(i);
						if(nsc.constate.equals(ConnectStateEnum.NO_CONNECT)){
                            nsc.closeSession();
							if(nsc.reConnect() == false || nsc.getLoginMessage() == false){
								nsc.constate = ConnectStateEnum.NO_CONNECT;
							}
						}
					}
				}
			}
		}, config.getLazyCheck(), config.getPeriodCheck());


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                RemotingClient r = RemotingClient.getInstance();
                IRemoteAccess ra = r.getRa();
                if(null == ra){
                    logger.info("远程调用对象为空，开始初始化*******");
                    r.init();
                }
                logger.info("远程调用对象不为空。");
            }
        },3000,15000);
    }
    
   private Map<String, List<NumberSegment>> getNumberSegment()
    {
        ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
        WellFormedDAO wellFormedDAO = (WellFormedDAO)(springCtx.getBean("wellFormedDAO"));
        return wellFormedDAO.getNumberSegment();
    }
}