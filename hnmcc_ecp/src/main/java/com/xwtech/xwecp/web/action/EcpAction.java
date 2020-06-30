//package com.xwtech.xwecp.web.action;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.net.URLDecoder;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.ServletException;
//import javax.servlet.ServletInputStream;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.xwtech.xwecp.exception.BaseException;
//import org.slf4j.Logger;import org.slf4j.LoggerFactory;
//
//import com.xwtech.xwecp.interfaces.InterfaceBase;
//import com.xwtech.xwecp.log.LInterfaceAccessLogger;
//import com.xwtech.xwecp.log.PerformanceTracer;
//import com.xwtech.xwecp.msg.MessageHead;
//import com.xwtech.xwecp.msg.RequestData;
//import com.xwtech.xwecp.msg.RequestParameter;
//import com.xwtech.xwecp.msg.ResponseData;
//import com.xwtech.xwecp.msg.ServiceMessage;
//import com.xwtech.xwecp.service.BaseServiceInvocationResult;
//import com.xwtech.xwecp.util.StringUtil;
//import com.xwtech.xwecp.util.XMLUtil;
///**
// * ECP对外CRM接口,地址如：http://127.0.0.0.1:8080/obsh_ecp/com.cmcc.js.outer.xwtech.interfaces
// * 提供给CRM，发生应急状态切换时，应急环境地址boss7
// * 格式要求HTTP+XML
// * @author        :  xmchen
// * @Create Date   :  2013-7-16
// */
//@SuppressWarnings("serial")
//public class EcpAction extends BaseAction
//{
//	private static final Logger bossRequestTeletextLogger = LoggerFactory.getLogger("bossRequestTeletextLogger");
//	private static final long serialVersionUID = 10900929329032L;
//	private final Logger logger = LoggerFactory.getLogger(EcpAction.class);
//
//	/**
//	 * 接口对照MAP 根据operationcode对应接口实现类
//	 */
//	private Map<String, String> interfaceMap =  new HashMap<String, String>()
//	{
//		{
//			put("esnotify", "interfaceSysEmerStateImpl");//系统应急环境切换
//			put("esrecnotify", "interfaceBusiEmerStateImpl");//业务应急环境切换
//			put("netinstallfeedorder", "interfaceNetinstallImpl");//订单反馈
//			put("pushFluxRemind", "pushFluxRemindImpl");//流量阀值推送
//			put("interfaceOrderFeedBack", "interfaceOrderFeedBackImpl");//订单状态变更
//
//
//			put("cfmyorderquery", "interfaceCfmyorderqueryImpl");    // 订单查询
//			put("cfmyordercancel", "interfaceCfmyordercancelImpl");  // 订单状取消
//			put("cfmyordersubmit", "interfaceCfmyordersubmitImpl");   // 订单提交
//			put("cfmyorderverify", "interfaceCfmyorderverifyImpl");   // 订单校验
//			put("cendinginfoquery", "interfaceCendinginfoqueryImpl");   // 网络类型和设备信息查询
//		}
//	};
//
//	//响应报文模版
//	private Map<String, String> respMap =  new HashMap<String, String>()
//	{
//		{
//			put("esnotify", "esnotify_resp");//系统应急返回报文
//			put("esrecnotify", "esrecnotify_resp");//业务应急返回报文
//			put("netinstallfeedorder", "cc_netinstall_feedorder");//订单反馈
//			put("pushFluxRemind", "pushFluxRemind");//流量阀值推送
//			put("interfaceOrderFeedBack", "cc_orderFeedBack");//订单状态变更
//
//
//			put("cfmyorderquery", "cc_cfmyorderquery");   // 订单查询
//			put("cfmyordercancel", "cc_cfmyordercancel"); // 订单状取消
//			put("cfmyordersubmit", "cc_cfmyordersubmit"); // 订单提交
//			put("cfmyorderverify", "cc_cfmyorderverify");   // 订单校验
//			put("cendinginfoquery", "cc_cendinginfoquery");   // 网络类型和设备信息查询
//		}
//	};
//
//	protected void service(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException
//	{
//		String msg = "";
//    	String operCode = "";
//		List<RequestParameter> res = new ArrayList<RequestParameter>();
//		String requestStr = "";
//        try {
//        	requestStr = readDataRequest(req);
//            logger.info("请求报文request= "+requestStr);
//            if (!StringUtil.isNull(requestStr))
//			{
//				operCode = XMLUtil.getChildTextEx(requestStr,"operation_code");
//				//根据operationcode对应接口实现类
//				InterfaceBase interfaceBase = (InterfaceBase) springCtx.getBean(interfaceMap.get(operCode).toString());
//				res = interfaceBase.execute(requestStr);
//            	return ;
//            }
//        }
//        catch (BaseException e)
//        {
//        	logger.error(e);
//        	msg = e.getMessage();
//        }
//        catch (Exception e)
//        {
//            logger.error(e);
//           e.printStackTrace();
//            msg = "系统忙，请稍后在试!";
//        }
//        finally
//        {
//        	try
//        	{
//        		String responseStr = toXML(res,msg,operCode);
//
//				writeXMLResponse(resp, responseStr);
//				PerformanceTracer pt = PerformanceTracer.getInstance();
//				// long n = 0;
//				// long accessTime = System.currentTimeMillis();
//				long responseTime = pt.addBreakPoint();
////				ServiceMessage requestMsg = ServiceMessage.fromXML(requestStr);
//				ServiceMessage requestMsg = new ServiceMessage();
//				ServiceMessage responseMsg = new ServiceMessage();
//				ResponseData data2 = new ResponseData();
//				BaseServiceInvocationResult result = new BaseServiceInvocationResult();
//				result.setErrorCode("0000");
//				result.setResultCode("0");
//				result.setResultCode("");
//				data2.setServiceResult(result);
//				responseMsg.setData(data2);
////				ServiceMessage responseMsg = ServiceMessage.fromXML(responseStr);
//				MessageHead ms = new MessageHead();
//				RequestData data = new RequestData();
//				ms.setCmd(operCode);
//				requestMsg.setData(data);
//				requestMsg.setHead(ms);
//				String clientIp = req.getRemoteHost();
//				System.out.println(clientIp);
//				Throwable exception = null;
//				LInterfaceAccessLogger.log(requestStr, responseStr, "", requestMsg, System.currentTimeMillis(), responseTime, responseMsg, clientIp,exception,  "","");
//				Runtime.getRuntime().exec("/weblogic/bin/restart_all_memcached_wxrevision.sh");
//			} catch (Exception e)
//			{
//				logger.error(e);
//				e.printStackTrace();
//			}
//        }
//	}
//
//	private String toXML(List<RequestParameter> params,String errMsg,String operCode)
//	{
//		String reqXml = this.bossTeletextUtil.mergeTeletext(respMap.get(operCode), params);
//		return reqXml;
//	}
//
//	private void writeXMLResponse(HttpServletResponse response, String xmlResponse) throws Exception
//	{
//		ServletOutputStream sos = response.getOutputStream();
//		sos.write(xmlResponse.getBytes(charset));
//		sos.flush();
//		sos.close();
//	}
//
//	private String readDataRequest(HttpServletRequest request) throws Exception
//	{
//		ServletInputStream sis = request.getInputStream();
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		int read = 0;
//		byte[] buf = new byte[4096];
//		while((read = sis.read(buf)) > 0)
//		{
//			baos.write(buf, 0, read);
//			baos.flush();
//		}
//		String xmlRequest = new String(baos.toByteArray(),"UTF-8");
//		sis.close();
//		baos.close();
//		xmlRequest = URLDecoder.decode(xmlRequest.toString(), "UTF-8");
//
//		return xmlRequest;
//	}
//}