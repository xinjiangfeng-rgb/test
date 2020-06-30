package com.xwtech.xwecp.logs;

import com.alibaba.fastjson.JSON;
import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.msg.RequestData;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.msg.ResponseData;
import com.xwtech.xwecp.msg.ServiceMessage;
import org.apache.logging.slf4j.Log4jMarkerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LInterfaceAccessLogger {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LInterfaceAccessLogger.class);
	private static final Log4jMarkerFactory log4jMarkerFactory = new Log4jMarkerFactory();

	private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	public static void log(String xmlRequest, String xmlResponse, String accessId,
			ServiceMessage request, long accessTime, long responseTime,
			ServiceMessage response, String clientIp,
			Throwable stackTrace, String clientAccessId,String userTraceId,String serIp,String serPort,
						   String crmIp,String crmPort) {
		final String f_trace_id = request.getHead().getTranceId();
		final String f_access_id = accessId;
		final String f_logic_number = request.getHead().getCmd();
		final String f_access_time = getDateStr(accessTime);
		final String f_response_time = getDateStr(responseTime);
		long f_total_time = responseTime - accessTime;
		final String f_client_ip = clientIp;
		final String f_channel_num = request.getHead().getChannel();
		final StringBuffer f_channel_user = new StringBuffer();
		RequestData data  = (RequestData)request.getData();
		final String f_oper_id = getParameters(data.getParams(),"context_fixed_oper_id")==null?null:getParameters(data.getParams(),"context_fixed_oper_id").toString();

		if (request.getHead().getUser() != null
				&& request.getHead().getUser().trim().length() > 0) {
			f_channel_user.append(request.getHead().getUser());
		}
		else {
			f_channel_user.append("UNKNOWN");
		}
		final String f_is_error = stackTrace == null ? "0" : "1";
		final String f_result_code = ((ResponseData) response.getData())
				.getServiceResult().getResultCode();
		final String f_error_code = ((ResponseData) response.getData())
				.getServiceResult().getErrorCode();
		final String f_error_msg = ((ResponseData) response.getData())
				.getServiceResult().getErrorMessage();
		final String f_op_type = request.getHead().getOpType();
		final String f_user_mobile = request.getHead().getUserMobile();
		final String f_user_brand = request.getHead().getUserBrand();
		final String f_user_city = request.getHead().getUserCity();
		final String f_biz_code = request.getHead().getBizCode();
		final String f_req_text = xmlRequest;
		final String f_res_text = xmlResponse;
		final StringBuffer f_error_stack = new StringBuffer();

		String clientPort = request.getHead().getClientPort();//
		
		if (stackTrace != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			stackTrace.printStackTrace(pw);
			f_error_stack.append(sw.getBuffer().toString());
			pw.close();
			try
			{
				sw.close();
			}
			catch (IOException e)
			{
				logger.error(e.toString());
			}
		}
		System.out.println(">>>>>>>>>>>"+f_trace_id+"|"+f_access_id+"|"+f_logic_number+"|"+f_access_time+"|"+f_response_time+"|"+f_total_time+"|"+
				f_channel_num+"|"+f_channel_user.toString()+"|"+f_result_code+"|"+f_error_msg+"|"+f_user_mobile+"|"+f_user_brand+"|"+f_user_city+"|"+f_client_ip+"|"+f_op_type+"|"+f_biz_code+"|"+""+"|"+
				f_is_error+"|"+f_error_code+"|"+""+"|"+""+"|"+""+"|"+f_oper_id+"|"+clientPort+"|"+serIp+"|"+serPort+"|"+crmIp+"|"+crmPort);

		//此处ecp日志入kafka
/*		EcpDBLog ecpDBLog = new EcpDBLog(f_trace_id,f_access_id,f_logic_number,f_access_time,f_response_time,f_total_time,
				f_channel_num,f_channel_user.toString(),f_result_code,f_error_msg,f_user_mobile,f_user_brand,f_user_city,f_client_ip,f_op_type,f_biz_code,"",
				f_is_error,f_error_code,"","","",f_oper_id,clientPort,serIp,serPort,crmIp,crmPort);
		org.slf4j.Marker marker = log4jMarkerFactory.getMarker("KF_ECPDB");
		logger.info(marker, JSON.toJSONStringWithDateFormat(ecpDBLog, "yyyy-MM-dd hh:mm:ss"));*/


		EcpLiLog ecpDBLog = new EcpLiLog(f_trace_id, f_access_id, f_logic_number, f_access_time, f_response_time, f_total_time,
				f_channel_num, f_channel_user.toString(), f_result_code, f_error_msg, f_user_mobile, f_user_brand, f_user_city, f_client_ip, "", f_op_type, f_biz_code,
				"", f_is_error, f_error_code, "", "", f_error_stack.toString(), f_oper_id, "", serPort, crmIp, crmPort);
		org.slf4j.Marker marker = log4jMarkerFactory.getMarker("KF_ECPDB");
		logger.info(marker, JSON.toJSONStringWithDateFormat(ecpDBLog, "yyyy-MM-dd hh:mm:ss"));
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		LogsDAOImpl logsDAOImpl = (LogsDAOImpl) (springCtx.getBean("logsDAO"));
		logsDAOImpl.insertLInterfaceAccessLog(ecpDBLog);
	}


	private static String getDateStr(long time) {
		Date d = new Date();
		d.setTime(time);
		return format.format(d);
	}
	
	private String makeOnRow(String s) {
		if(s == null)
		{
			return "";
		}
		s = s.replaceAll("\\u000A", "");
		return s.replaceAll("\\u000D", "");
	}
	
	/**
	 * 从参数列表里获取参数值
	 * 
	 * @param params
	 * @param parameterName
	 * @return
	 */
	public static Object getParameters(final List<RequestParameter> params, String parameterName) {
		Object rtnVal = null;
		for (RequestParameter parameter : params) {
			String pName = parameter.getParameterName();
			if (pName.equals(parameterName)) {
				rtnVal = parameter.getParameterValue();
				break;
			}
		}
		return rtnVal;
	}
	
}
