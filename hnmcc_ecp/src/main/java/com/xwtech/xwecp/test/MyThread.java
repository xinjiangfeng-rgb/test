package com.xwtech.xwecp.test;

import java.util.Properties;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryUserInfoService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryUserInfoServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY040001Result;

public class MyThread implements Runnable
{
    private static final Logger logger = LoggerFactory.getLogger(MyThread.class);
    
    private static int count = 0;
    /**
     * 查询用户信息
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)
        throws Exception
    {
    	MyThread my = new MyThread();
    	for(int j=0;j<400;j++){
    		new Thread(my, j+"号窗口").start();
    	}
        System.out.println(count);
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++){
			// 初始化ecp客户端片段
	        Properties props = new Properties();
	        props.put("client.channel", "hnmcc_channel");
//	        props.put("platform.url", "http://127.0.0.1:8080/obsh_ecp/xwecp.do");
	         props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
	        // props.put("platform.url", "http://10.32.229.82:10008/sms_ecp/xwecp.do");
	        // props.put("platform.url", "http://10.32.122.166:10009/js_ecp/xwecp.do");
	        // props.put("platform.url", "http://10.32.65.238:8081/sms_ecp/xwecp.do");
	        // props.put("platform.url", "http://10.32.65.238/js_ecp/xwecp.do");
	        props.put("platform.user", "hnmcc");
	        props.put("platform.password", "hnmcc");
	        
	        XWECPLIClient client = XWECPLIClient.createInstance(props);
	        // 逻辑接口调用片段
	        LIInvocationContext lic = LIInvocationContext.getContext();
	        lic.setBizCode("biz_code_19234");
	        lic.setOpType("开通/关闭/查询/变更");
	        lic.setUserBrand("1");
	        lic.setUserCity("12");
	        lic.setUserMobile("13915170950");
	        InvocationContext ic = new InvocationContext();
	        ic.addContextParameter("login_msisdn", "13838268859");
	        ic.addContextParameter("route_type", "1");
	        ic.addContextParameter("route_value", "12");
	        
	        lic.setContextParameter(ic);
	        
	        IQueryUserInfoService co = new QueryUserInfoServiceClientImpl();
	        QRY040001Result re;
			try {
				re = co.queryUserInfo("18803695181");
				//logger.info(" ====== 开始返回参数 ======"+i);
		        if (re != null)
		        {
		        	if("1".equals(re.getResultCode())){
		        		logger.info(" ====== getResultCode ======" + re.getResultCode());
		        		count++;
		        	}		            
//		            logger.info(" ====== getErrorMessage ======" + re.getErrorMessage());
//		            
//		            logger.info(" ====== AreaCode ======" + re.getAreacode());
		        }
			} catch (LIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		}
		
	}
}
