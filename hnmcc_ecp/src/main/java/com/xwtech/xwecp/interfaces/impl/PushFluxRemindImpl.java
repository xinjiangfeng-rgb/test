//package com.xwtech.xwecp.interfaces.impl;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import com.xwtech.xwecp.exception.BaseException;
//import org.slf4j.Logger;import org.slf4j.LoggerFactory;
//
//import com.xwtech.xwecp.communication.HttpCommunicator;
//import com.xwtech.xwecp.interfaces.InterfaceBase;
//import com.xwtech.xwecp.msg.RequestParameter;
//import com.xwtech.xwecp.util.ConfigurationRead;
//import com.xwtech.xwecp.util.XMLUtil;
//
///**
// * @流量阀值通知处理
// * @author        :  taogang
// * @Create Date   :  2014-3-20
// */
//public class PushFluxRemindImpl implements InterfaceBase {
//
//	private static final Logger logger = LoggerFactory.getLogger(PushFluxRemindImpl.class);
//
//	private HttpCommunicator platformConnection;
//
//	private SimpleDateFormat format1=new SimpleDateFormat("yyyyMMddHHmmss");
//
//	private SimpleDateFormat format2=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
//
//	private static String remindUrl =  ConfigurationRead.getInstance().getValue("pushFluxRemind.url");
//
//	public List<RequestParameter> execute(String reqXML) throws BaseException {
//		List<RequestParameter> res = new ArrayList<RequestParameter>();
//		String mobile = XMLUtil.getChildTextEx(reqXML,"content","mobile");
//		String total = XMLUtil.getChildTextEx(reqXML,"content","total");
//		String used = XMLUtil.getChildTextEx(reqXML,"content","used");
//		String remindTime = XMLUtil.getChildTextEx(reqXML,"content","remindTime");
//		String reserve1 = XMLUtil.getChildTextEx(reqXML,"content","reserve1");
//		String Reserve2 = XMLUtil.getChildTextEx(reqXML,"content","Reserve2");
//		try {
//			remindTime = format2.format(format1.parse(remindTime));
//		} catch (ParseException e1) {
//			logger.error(e1.getMessage());
//		}
//		String content = "截止" + remindTime + "为止,您本月的移动数据已使用流量为"+ (double)(Math.round(Integer.parseInt(used)/1024)) + "M,总流量为" + (double)(Math.round(Integer.parseInt(total)/1024)) + "M";
//		logger.info("content----------------------" + content);
//		HashMap<String, Object> postMap = new HashMap<String, Object>();
//		postMap.put("mobile", mobile);
//		postMap.put("content", content);
//		postMap.put("hours", "1");
//		this.platformConnection = new HttpCommunicator();
//		try {
//			String str = platformConnection.sendPost(postMap, remindUrl);
//		} catch (Exception e) {
//			logger.error("PushFluxRemindImpl.execute error :" + e);
//		}
//
//		return res;
//	}
//
//}
