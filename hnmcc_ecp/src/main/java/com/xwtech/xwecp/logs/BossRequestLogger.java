package com.xwtech.xwecp.logs;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.Jedis.RedisServiceForSty;
import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.dao.WellFormedDAO;
import org.apache.logging.slf4j.Log4jMarkerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;


public class BossRequestLogger {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LInterfaceAccessLogger.class);
    private static final Logger log = LoggerFactory.getLogger(LInterfaceAccessLogger.class);
    private static final Log4jMarkerFactory log4jMarkerFactory = new Log4jMarkerFactory();

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    private static RedisServiceForSty redisService = (RedisServiceForSty) XWECPApp.SPRING_CONTEXT.getBean("redisServiceForSty");

    /**
     * bossRequest增加traceId
     *
     * @param traceId 追踪标识
     */
    public static void log(String bossRequestId, String accessId, String identity, long beginTime, long endTime, String requestMsg, String responseMsg, boolean isError, Throwable stackTrace, String resultCode, String errorCode, String errorMsg, String traceId,
                           String headTraceId, String headUserMobile, String headUserBrand, String headUserCity, String headPageNum, String headRecNum,
                           String headSerialNum, String headJfserialNum, String prodId) {
        String rn = "", pn = "", ts = "", jf = "", in = "";
        ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
        WellFormedDAO wellFormedDAO = (WellFormedDAO) (springCtx.getBean("wellFormedDAO"));

        String f_error_msg = errorMsg == null ? "" : errorMsg;
        String f_result_code = resultCode == null ? "" : resultCode;
        long total_time = endTime - beginTime;

        String bossAccId = new String(requestMsg.getBytes(), 3, 16);//请求和相应报文匹配的唯一流水
        String bossId = new String(requestMsg.getBytes(), 19, 6);//crm接口编码
        String retType = "";//1订购2退订(数据库中入此标志)
        String bisCode = "";
        String[] requestTem = requestMsg.split("\\t");
        logger.info("==========requestMsg:" + requestMsg);
        StringBuffer key = new StringBuffer("STY_OPER_SIGN_KEY_");
        System.out.println("=========三统一"+headRecNum+"prodId:"+prodId);
        if ("930021".equals(bossId)) {//基本产品
//			bisCode = new String(requestMsg.getBytes(),132,12);
            bisCode = requestTem[1];
            retType = "1";                                    //基本产品只有变更，算作订购
            try {
                if (null != headUserMobile && !"null".equals(headUserMobile) && !"".equals(headUserMobile)) {
                    //组装三统一沉淀日志key
                    /*key.append(headUserMobile);
					if (!StringUtils.isEmpty(prodId)) {
						key.append(prodId);
					} else {
						key.append("PRO_").append(bisCode);
					}
					String jsonStr = redisService.get(key.toString());
					System.out.println("yjqkey930021:" + key.toString() + "|value:" + jsonStr);
					JSONObject json = JSON.parseObject(jsonStr);
					if (null != json) {
						rn = json.getString("rn");
						pn = json.getString("pn");
						ts = json.getString("ts");
						jf = json.getString("jf");
						in = json.getString("in");
					}*/
                    rn = headRecNum;
                    pn = headPageNum;
                    ts = headSerialNum;
                    jf = headJfserialNum;
                    in = prodId;
                }
            } catch (Exception e) {
                logger.info("获取三统一沉淀数据异常key:" + key.toString());
            }
        } else if ("930018".equals(bossId)) {//增值产品
            //bisCode = new String(requestMsg.getBytes(),132,12);
            bisCode = requestTem[1];
            if (requestTem[2].equals("ADD")) {
                retType = "1";
            } else if (requestTem[2].equals("DEL")) {
                retType = "2";
            }

            try {
                if (null != headUserMobile && !"null".equals(headUserMobile) && !"".equals(headUserMobile)) {
                    //组装三统一沉淀日志key
//					key.append(headUserMobile).append(bisCode);
                    //组装三统一沉淀日志key
					/*key.append(headUserMobile);
					if (!StringUtils.isEmpty(prodId)) {
						key.append(prodId);
					} else {
						key.append("PRO_").append(bisCode);
					}
					String jsonStr = redisService.get(key.toString());
					System.out.println("yjqkey930018:" + key.toString() + "|value:" + jsonStr);
					JSONObject json = JSON.parseObject(jsonStr);
					if (null != json) {
						rn = json.getString("rn");
						pn = json.getString("pn");
						ts = json.getString("ts");
						jf = json.getString("jf");
						in = json.getString("in");
					}*/
                    rn = headRecNum;
                    pn = headPageNum;
                    ts = headSerialNum;
                    jf = headJfserialNum;
                    in = prodId;
                }
            } catch (Exception e) {
                logger.info("获取三统一沉淀数据异常key:" + key.toString());
            }
        } else if ("929206".equals(bossId)) {//营销活动
            //bisCode = new String(requestMsg.getBytes(),138,requestMsg.getBytes().length-138);
            bisCode = requestTem[6];
            retType = "1";                                    //营销活动只有订购
            try {
                if (null != headUserMobile && !"null".equals(headUserMobile) && !"".equals(headUserMobile)) {
                    /*List<String> actNums = wellFormedDAO.getProidByActCode(bisCode);
					for (int i = 0; i < actNums.size(); i++) {
						String actNum = actNums.get(i);*/
                    //组装三统一沉淀日志key
//						key.append(headUserMobile).append(bisCode);
                    //组装三统一沉淀日志key
					/*key.append(headUserMobile);
					if (!StringUtils.isEmpty(prodId)) {
						key.append(prodId);
					} else {
						key.append("PRO_").append(bisCode);
					}
					String jsonStr = redisService.get(key.toString());
					System.out.println("yjqkey929206:" + key.toString() + "|value:" + jsonStr);
					JSONObject json = JSON.parseObject(jsonStr);
					if (null != json) {
						rn = json.getString("rn");
						pn = json.getString("pn");
						ts = json.getString("ts");
						jf = json.getString("jf");
						in = json.getString("in");
					}*/
                    rn = headRecNum;
                    pn = headPageNum;
                    ts = headSerialNum;
                    jf = headJfserialNum;
                    in = prodId;
//					}
                }
            } catch (Exception e) {
                logger.info("获取三统一沉淀数据异常key:" + key.toString());
            }
        } else if ("100802".equals(bossId)) {//梦网产品
            //bisCode = new String(requestMsg.getBytes(),141,requestMsg.getBytes().length-141);
            bisCode = requestTem[7] + "|" + requestTem[8] + "|";
            if (requestTem[4].equals("06")) {
                retType = "1";
            } else if (requestTem[4].equals("07")) {
                retType = "2";
            }

            try {
                if (null != headUserMobile && !"null".equals(headUserMobile) && !"".equals(headUserMobile)) {
                    //组装三统一沉淀日志key
//					key.append(headUserMobile).append(prodId);
                    //组装三统一沉淀日志key
					/*key.append(headUserMobile);
					if (!StringUtils.isEmpty(prodId)) {
						key.append(prodId);
					} else {
						key.append("PRO_").append(bisCode);
					}
					String jsonStr = redisService.get(key.toString());
					System.out.println("yjqkey100802:" + key.toString() + "|value:" + jsonStr);
					JSONObject json = JSON.parseObject(jsonStr);
					if (null != json) {
						rn = json.getString("rn");
						pn = json.getString("pn");
						ts = json.getString("ts");
						jf = json.getString("jf");
						in = json.getString("in");
					}*/
                    rn = headRecNum;
                    pn = headPageNum;
                    ts = headSerialNum;
                    jf = headJfserialNum;
                    in = prodId;
                }
            } catch (Exception e) {
                logger.info("获取三统一沉淀数据异常key:" + key.toString());
            }
        } else if ("930017".equals(bossId)) {//服务包
            //bisCode = new String(requestMsg.getBytes(),132,12);
            bisCode = requestTem[1];
            if (requestTem[2].equals("ADD")) {
                retType = "1";
            } else if (requestTem[2].equals("DEL")) {
                retType = "2";
            }

            try {
                if (null != headUserMobile && !"null".equals(headUserMobile) && !"".equals(headUserMobile)) {
                    //组装三统一沉淀日志key
//					key.append(headUserMobile).append(prodId);
                    //组装三统一沉淀日志key
					/*key.append(headUserMobile);
					if (!StringUtils.isEmpty(prodId)) {
						key.append(prodId);
					} else {
						key.append("PRO_").append(bisCode);
					}
					String jsonStr = redisService.get(key.toString());
					System.out.println("yjqkey930017:" + key.toString() + "|value:" + jsonStr);
					JSONObject json = JSON.parseObject(jsonStr);
					if (null != json) {
						rn = json.getString("rn");
						pn = json.getString("pn");
						ts = json.getString("ts");
						jf = json.getString("jf");
						in = json.getString("in");
					}*/
                    rn = headRecNum;
                    pn = headPageNum;
                    ts = headSerialNum;
                    jf = headJfserialNum;
                    in = prodId;
                }
            } catch (Exception e) {
                System.out.println("获取三统一沉淀数据异常key:" + key.toString());
            }
        }

        //只有当来源于精准营销的才记录
        if (("930021".equals(bossId) || "930018".equals(bossId) || "929206".equals(bossId)
                || "100802".equals(bossId) || "930017".equals(bossId)) && !StringUtils.isEmpty(rn) && !StringUtils.isEmpty(jf)) {
            System.out.println(">>>>三统一沉淀日志>>>" + headTraceId + "|" + accessId + "|" + getDateStr(beginTime) + "|" + getDateStr(endTime) + "|" + total_time + "|" + f_result_code + "|" +
                    f_error_msg + "|" + bossAccId + "|" + bossId + "|" + bisCode + "|" + "" + "|" + "" + "|" + "" + "|" + "" + "|" + headUserMobile + "|" + headUserBrand + "|" + headUserCity + "|" +
                    retType + "|" + "" + "|" + "" + "|" + "" + "|" + "" + "|" + pn + "|" + rn + "|" + ts + "|" + jf + "|" + in);
            EcpStyLog ecpStyLog = new EcpStyLog(headTraceId, accessId, getDateStr(beginTime), getDateStr(endTime), String.valueOf(total_time), f_result_code,
                    f_error_msg, bossAccId, bossId, bisCode, "", "", "", "", headUserMobile, headUserBrand, headUserCity,
                    retType, in, "", "", "", pn, rn, ts, jf);
            org.slf4j.Marker marker1 = log4jMarkerFactory.getMarker("KF_ECPSTY");




            logger.info(marker1, JSON.toJSONStringWithDateFormat(ecpStyLog, "yyyy-MM-dd hh:mm:ss"));
        }
        //此处ecp日志入kafka
/*       EcpCRMLog ecpCrmLog = new EcpCRMLog(headTraceId, accessId, getDateStr(beginTime), getDateStr(endTime), total_time, f_result_code,
                f_error_msg, bossAccId, bossId, bisCode, "", "", "", "", headUserMobile, headUserBrand, headUserCity,
                retType, "", "", "", "", headPageNum, headRecNum, headSerialNum, headJfserialNum);
        org.slf4j.Marker marker = log4jMarkerFactory.getMarker("KF_ECPCRM");
        logger.info(marker, JSON.toJSONStringWithDateFormat(ecpCrmLog, "yyyy-MM-dd hh:mm:ss"));  */


        EcpBossLog ecpCrmLog = new EcpBossLog(headTraceId, accessId, getDateStr(beginTime), getDateStr(endTime), total_time, f_result_code,
                f_error_msg, bossAccId, bossId, bisCode, "", "", "", "", headUserMobile, headUserBrand, headUserCity,
                retType, "", "", "", "", headPageNum, headRecNum, headSerialNum, headJfserialNum);
        org.slf4j.Marker marker = log4jMarkerFactory.getMarker("KF_ECPCRM");
        logger.info(marker, JSON.toJSONStringWithDateFormat(ecpCrmLog, "yyyy-MM-dd hh:mm:ss"));
        LogsDAOImpl logsDAOImpl = (LogsDAOImpl) (springCtx.getBean("logsDAO"));
        logsDAOImpl.insertBossRequestAccessLog(ecpCrmLog);
    }

    /**
     * bossRequest增加traceId
     *
     * @param traceId 追踪标识
     */
    public static void remoteLog(String bossRequestId, String accessId, String identity, long beginTime, long endTime, String requestMsg, String responseMsg, boolean isError, Throwable stackTrace, String resultCode, String errorCode, String errorMsg, String traceId,
                                 String headTraceId, String headUserMobile, String headUserBrand, String headUserCity, String headPageNum, String headRecNum,
                                 String headSerialNum, String headJfserialNum) {
        String f_error_msg = errorMsg == null ? "" : errorMsg;
        String f_result_code = resultCode == null ? "" : resultCode;
        long total_time = endTime - beginTime;

        //从json中获取method
        String sysParam = requestMsg.split("\\|")[0];
        JSONObject jsonObject = JSONObject.parseObject(sysParam);
        Iterator it = jsonObject.keySet().iterator();
        String methodValue = "";
        while (it.hasNext()) {
            String key = it.next().toString();
            if ("method".equals(key)) {
                methodValue = String.valueOf(jsonObject.get(key));
                break;
            }
        }

        System.out.println(">>>>>>>" + headTraceId + "|" + accessId + "|" + getDateStr(beginTime) + "|" + getDateStr(endTime) + "|" + total_time + "|" + f_result_code + "|" +
                f_error_msg + "|" + "" + "|" + methodValue + "|" + "" + "|" + "" + "|" + "" + "|" + "" + "|" + "" + "|" + headUserMobile + "|" + headUserBrand + "|" + headUserCity + "|" +
                "" + "|" + "" + "|" + "" + "|" + "" + "|" + "" + "|" + headPageNum + "|" + headRecNum + "|" + headSerialNum + "|" + headJfserialNum);

        //此处ecp日志入kafka
/*        EcpCRMLog ecpCrmLog = new EcpCRMLog(headTraceId, accessId, getDateStr(beginTime), getDateStr(endTime), total_time, f_result_code,
                f_error_msg, "", methodValue, "", "", "", "", "", headUserMobile, headUserBrand, headUserCity,
                "", "", "", "", "", headPageNum, headRecNum, headSerialNum, headJfserialNum);
        org.slf4j.Marker marker = log4jMarkerFactory.getMarker("KF_ECPCRM");
        logger.info(marker, JSON.toJSONStringWithDateFormat(ecpCrmLog, "yyyy-MM-dd hh:mm:ss"));*/
        EcpBossLog ecpCrmLog = new EcpBossLog(headTraceId, accessId, getDateStr(beginTime), getDateStr(endTime), total_time, f_result_code,
                f_error_msg, "", methodValue, "", "", "", "", "", headUserMobile, headUserBrand, headUserCity,
                "", "", "", "", "", headPageNum, headRecNum, headSerialNum, headJfserialNum);
        org.slf4j.Marker marker = log4jMarkerFactory.getMarker("KF_ECPCRM");
        logger.info(marker, JSON.toJSONStringWithDateFormat(ecpCrmLog, "yyyy-MM-dd hh:mm:ss"));

        ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
        LogsDAOImpl logsDAOImpl = (LogsDAOImpl) (springCtx.getBean("logsDAO"));
        logsDAOImpl.insertBossRequestAccessLog(ecpCrmLog);
    }

    private static String getDateStr(long time) {
        Date d = new Date();
        d.setTime(time);
        return format.format(d);
    }

    public RedisServiceForSty getRedisService() {
        return redisService;
    }

	/*public void setRedisService(RedisServiceForSty redisService) {
		this.redisService = redisService;
	}*/
}
