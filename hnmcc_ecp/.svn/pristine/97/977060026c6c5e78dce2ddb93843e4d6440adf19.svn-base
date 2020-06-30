package com.xwtech.xwecp.web.action;

import java.io.ByteArrayOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.util.RequestUtils;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.xwtech.xwecp.log.PerformanceTracer;
import com.xwtech.xwecp.service.ServiceExecutor;
import com.xwtech.xwecp.util.SavePortUtil;

/**
 * ECP入口
 * @author Administrator
 *
 */

public class ServiceInvocationAction extends   HttpServlet
//		extends MultiActionController
{
	private static Logger logger = LoggerFactory.getLogger(ServiceInvocationAction.class);

	private ServiceExecutor serviceExecutor ;
	
	private String charset = "UTF-8";

	public   ServiceInvocationAction(){
        System.out.println("122212121212");
    }

    public void init()
    {
        serviceExecutor = XWECPApp.SPRING_CONTEXT.getBean(ServiceExecutor.class);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {

	    doPost(request,response);
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) {


        try
        {
            this.doService(request, response);
        }
        catch(Throwable e)
        {
            this.handleException(e, request, response);
        }
    }

    public void destroy()
    {
    }


	/**
	 * 默认处理方法
	 * @param request
	 * @param response
	 */
	public void defaultHandle(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			this.doService(request, response);
		}
		catch(Throwable e)
		{
			this.handleException(e, request, response);
		}
	}
	
	/**
	 * 异常处理
	 * @param e
	 * @param request
	 * @param response
	 */
	protected void handleException(Throwable e, HttpServletRequest request, HttpServletResponse response)
	{
		logger.error("", e);
		this.responseError(request, response, e);
	}
	
	protected void responseError(HttpServletRequest request, HttpServletResponse response, Throwable cause)
	{
		throw new RuntimeException("not implied!", cause);
	}


	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//获取当前主机ip和端口传到li表的日志记录
		String serIp = request.getLocalAddr();
		String serPort = String.valueOf(request.getLocalPort());

		PerformanceTracer pt = PerformanceTracer.getInstance();
		long beginTime = System.currentTimeMillis();
		pt.init();
		long n = pt.addBreakPoint();
		String xmlRequest = this.readXMLRequest(request);
		pt.trace("请求报文长度[" + xmlRequest.length() + "]", n);
//		logger.debug("request: \n" + xmlRequest);
		//String xmlResponse = this.serviceExecutor.executeService(xmlRequest, this.getClientIP(request));
		String xmlResponse = this.serviceExecutor.executeService(xmlRequest, RequestUtils.getMyIpAddr(request),
				serIp, serPort);
		pt.trace("响应报文长度[" + xmlResponse.length() + "]", n);
		logger.debug("response: \n" + xmlResponse);
		pt.trace("ECP处理总时间...", beginTime);
		pt.log();
		this.writeXMLResponse(response, xmlResponse);
	}
	
	private String getClientIP(HttpServletRequest request)
	{
		String port = request.getLocalPort()+"";//獲取服務器端  端口
		
		SavePortUtil.getInstance().port = port;
		
		return request.getRemoteAddr();
	}
	
	private void writeXMLResponse(HttpServletResponse response, String xmlResponse) throws Exception
	{
		ServletOutputStream sos = response.getOutputStream();
		sos.write(xmlResponse.getBytes(charset));
		sos.flush();
		sos.close();
	}
	
	private String readXMLRequest(HttpServletRequest request) throws Exception
	{
		ServletInputStream sis = request.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int read = 0;
		byte[] buf = new byte[4096];
		while((read = sis.read(buf)) > 0)
		{
			baos.write(buf, 0, read);
			baos.flush();
		}
		String xmlRequest = new String(baos.toByteArray(), charset);
		sis.close();
		baos.close();
		return xmlRequest;
	}

	public ServiceExecutor getServiceExecutor()
	{
		return serviceExecutor;
	}

	public void setServiceExecutor(ServiceExecutor serviceExecutor)
	{
		this.serviceExecutor = serviceExecutor;
	}

	public String getCharset()
	{
		return charset;
	}

	public void setCharset(String charset)
	{
		this.charset = charset;
	}
}