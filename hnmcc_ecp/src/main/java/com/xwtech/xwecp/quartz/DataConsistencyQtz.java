package com.xwtech.xwecp.quartz;

import com.xwtech.xwecp.Jedis.RedisService;
import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.CommunicateException;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.communication.RemoteCallContext;
import com.xwtech.xwecp.dao.GroupDetectionDAO;
import com.xwtech.xwecp.msg.InvocationContext;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.logic.LIException;
import com.xwtech.xwecp.service.logic.client.LIInvocationContext;
import com.xwtech.xwecp.service.logic.client.XWECPLIClient;
import com.xwtech.xwecp.service.logic.client_impl.common.IQueryCurrentMonthFeeService;
import com.xwtech.xwecp.service.logic.client_impl.common.ISendSmsMsgService;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.QueryCurrentMonthFeeServiceImpl;
import com.xwtech.xwecp.service.logic.client_impl.common.impl.SendSmsMsgServiceClientImpl;
import com.xwtech.xwecp.service.logic.pojo.QRY080001Result;
import com.xwtech.xwecp.service.logic.pojo.QRY920013Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.DateTimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 数据一致性定时刷缓存
 *
 * @author Administrator
 */
public class DataConsistencyQtz {
    private static Logger logger = Logger.getLogger(DataConsistencyQtz.class);
    private static final String accessId = "S00120160825140624100000000";
    private IRemote remote;
    private RedisService redisService;
    private BossTeletextUtil bossTeletextUtil;
    private GroupDetectionDAO groupDetectionDAO;

    //集团探测最大尝试请求次数5
    private static final Integer MaxReqNum = 5;
    private static Map<String, Integer> logDay = new HashMap<>();


    private static long todayMaxTime;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final String JTTC_SET = "ECP_JTTC_SET";

    //自增
    private void incrMaxReqNum() {
        String key = DateTimeUtil.getTodayChar8();
        Integer max = logDay.get(key);
        if (max == null) {
            logDay.put(key, 1);
        } else {
            logDay.put(key, ++max);
        }
    }

    private void setMaxReqNum(Integer integer) {
        logDay.put(DateTimeUtil.getTodayChar8(), integer);
    }

    private Integer getMaxReqNum() {
        String key = DateTimeUtil.getTodayChar8();
        Integer max = logDay.get(key);
        if (max == null) {
            return 0;
        }
        return max;
    }

    public void dataMethod() {
        //设置今天日期
        try {
            todayMaxTime = dateFormat.parse(DateTimeUtil.getTodayChar8() + "235959").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }


//        if (getMaxReqNum() > MaxReqNum) {
//            logger.info("集团探测定时任务已经执行结束：" + DateTimeUtil.getTodayChar8());
//            return;
//        }

        long start = System.currentTimeMillis();
        logger.info("--------------数据一致性缓存开刷 start");
        remote = (IRemote) (XWECPApp.SPRING_CONTEXT.getBean("bossRemote"));
        redisService = (RedisService) (XWECPApp.SPRING_CONTEXT.getBean("redisService"));
        bossTeletextUtil = (BossTeletextUtil) (XWECPApp.SPRING_CONTEXT.getBean("bossTeletextUtil"));
        groupDetectionDAO = (GroupDetectionDAO) (XWECPApp.SPRING_CONTEXT.getBean("groupDetectionDAO"));

        RemoteCallContext remoteCallContext = new RemoteCallContext();
        remoteCallContext.setUserCity("12");
        remoteCallContext.setInvokeChannel("hnmcc_channel");
        remoteCallContext.setTraceId("");

        //先清楚探测所有key存储的SET集合
        redisService.del(JTTC_SET);
        //获取所有集团探测号码
        List<Map<String, Object>> telNums = groupDetectionDAO.getAllTelNum();
        //获取所有模板
        List<Map<String, Object>> tems = groupDetectionDAO.getAllTem();

        String feeEndDate = "";
        logger.info("----------------查当月消费接口出来的时间invoke920013：" + feeEndDate);
        logger.info("----------------方法获取昨天时间getYesterday：" + getYesterday());
        logger.info("----------------集团探测号码：" + telNums.size());
        logger.info("----------------所有模板：" + tems.size());

        boolean resultBo = false;
        int count = 0;
        int whileCount = 100;//每次休眠10分钟，循环10次
        while (count < whileCount) {
            feeEndDate = invoke920013();
            logger.info("----------------第" + count + "次：" + feeEndDate.equals(getYesterday()));
            if (!feeEndDate.equals(getYesterday())) {
                count++;
                try {
                    invokeSendSms("集团探测：第" + count + "次等待出账");
                    Thread.currentThread().sleep(300000);//如果还没有出账完成，就休眠10分钟
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            resultBo = true;
            break;
        }

        //判断是不是01号
        String day = DateTimeUtil.getTodayDay();

        if (resultBo) {
            if (null != telNums && telNums.size() > 0 && null != tems && tems.size() > 0) {
                logger.info("----------------进入222");
                //循环所有号码，每个号码都要刷所有的模板缓存
                //失败次数，失败10个接口则重新跑定时任务
                Integer failNum = 0;
                for (int i = 0; i < telNums.size(); i++) {
                    String phoneNum = String.valueOf(telNums.get(i).get("PHONE_NUM"));
                    for (int j = 0; j < tems.size(); j++) {
                        String crmCode = String.valueOf(tems.get(j).get("CRM_CODE"));
                        String ecpToCrmCode = String.valueOf(tems.get(j).get("ECP_TO_CRM_CODE"));
                        String temFlag = String.valueOf(tems.get(j).get("TEM_FLAG"));
                        logger.info("----------------正在执行的接口：" + crmCode + " , "+ecpToCrmCode+" , " + temFlag + " , 探测号码： " + phoneNum);

                        if ("0".equals(temFlag)) {//账单类要查询近6个月的，所以要掉用6次
                            String[] months = getLast6Months();
                            for (int n = 0; n < months.length; n++) {
                                String startcycle = months[n];
                                //不成功加失败次数
                                if (!invokeInter(phoneNum, temFlag, crmCode, ecpToCrmCode, remoteCallContext, startcycle, "", "")) {
                                    failNum++;
                                }
                            }
                        } else if ("1".equals(temFlag)) {//详单类的
                            String[] monthes = getQueryMonth();
                            String bDate = "";
                            String eDate = "";
                            if (crmCode.equals("920021")) {
                                bDate = monthes[0] + "01";
                                eDate = getTodayChar8();
                                //不成功加失败次数
                                if (!invokeInter(phoneNum, temFlag, crmCode, ecpToCrmCode, remoteCallContext, "", bDate, eDate)) {
                                    failNum++;
                                }
                            } else if (crmCode.equals("920048")) {
                                bDate = monthes[0] + "01";
                                eDate = monthes[5] + getMonthDays(DateTimeUtil.getTodayChar6());
                                //不成功加失败次数
                                if (!invokeInter(phoneNum, temFlag, crmCode, ecpToCrmCode, remoteCallContext, "", bDate, eDate)) {
                                    failNum++;
                                }
                            }
                        } else if ("2".equals(temFlag)) {//一般类掉一次
                            //积分查询月初1号不加缓存
                            if (crmCode.equals("920012")) {
                                if (day.equals("01")) {
                                    continue;
                                }
                            }
                            logger.info("----------------进入555temFlag=2");
                            //不成功加失败次数
                            if (!invokeInter(phoneNum, temFlag, crmCode, ecpToCrmCode, remoteCallContext, "", "", "")) {
                                failNum++;
                            }
                        }
                    }
                }
                //TODO如果有失败10个接口则重新请求
                if (failNum > 10) {
                    //如果达到失败临界值，则发失败短信
                    if (getMaxReqNum() == MaxReqNum) {
                        invokeSendSms("集团探测定时任务失败！");
                    }
                    incrMaxReqNum();
                    logger.info("集团探测任务失败接口大于10个，重新开始");
                } else {
                    setMaxReqNum(MaxReqNum + 1);
                    invokeSendSms("集团探测定时任务成功！");
                    logger.info("集团探测任务执行成功");
                }
            } else {
                logger.info("----------------探测号码表或者模板表没有数据");
                //TODO发短息告知
                invokeSendSms("探测号码表或者模板表没有数据");
            }
        } else {
            logger.info("----------------还没有出完账count:" + count);
            //TODO发短息告知
            invokeSendSms("集团探测任务： 出账未完成");

            setMaxReqNum(MaxReqNum + 1);
        }
        long end = System.currentTimeMillis();
        logger.info("--------------数据一致性缓存结束 end------耗时：" + ((end - start) / 1000));
    }


    /**
     * 获取当前时间距离今天结束最大分钟数
     *
     * @return
     */

    private long getMaxMinutes() {
        Long l1 = todayMaxTime - new Date().getTime();
        return l1 / (1000 * 60);
    }
    //组织参数和报文调接口,并入缓存
    private boolean invokeInter(String phoneNum, String temFlag, String crmRemote, String ecpToCrmCode, RemoteCallContext remoteCallContext,
                                String startcycle, String bDate, String eDate) {
        List<RequestParameter> params = createParams(phoneNum, startcycle, bDate, eDate);
        String reqXml = bossTeletextUtil.mergeTeletext(ecpToCrmCode, params);
        logger.info("----------------进入666reqXml:" + reqXml);
        try {
            String rspXml = (String) remote.callRemote(new DefaultServiceImpl.StringTeletext(reqXml, accessId, ecpToCrmCode,
                    remoteCallContext));
            //String rspXml = "MHF214048807j5zOh19200828XZT01 HNYD00201608252140490001  1 170 1  0000调用成功                                  FFFFFFFF20160824\t2400\t0\t2400\t2400\t0\t0\t0\t2400\t207\t6\t2187";
            logger.info("--------------Boss返回数据为*****　" + rspXml);
            if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120) {
                //返回信息异常
                logger.info("--------------数据一致性缓存CRM返回异常");
                return false;
            }
            String returnCode = new String(rspXml.getBytes(), 66, 4);
            if ("0000".equals(returnCode)) {
                logger.info("--------------调用接口成功");
                //调用成功入缓存
                String key = createKey(temFlag, crmRemote, phoneNum, bDate, eDate, startcycle, ecpToCrmCode);
                redisService.set(key, rspXml, getMaxMinutes(), TimeUnit.MINUTES);
                //所有探测的缓存入set
                redisService.sadd(DataConsistencyQtz.JTTC_SET, key);
                return true;
            } else {
                logger.info("--------------invokeInterFail:reqXML[" + reqXml + "]*****rspXml[" + rspXml + "]");
                return true;
            }
        } catch (CommunicateException e) {
            logger.error("--------------调用接口入缓存异常");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            logger.error("--------------调用接口入缓存异常");
            e.printStackTrace();
            return false;
        }
    }

    private List<RequestParameter> createParams(String svcNum, String startcycle, String bDate, String eDate) {
        List<RequestParameter> params = new ArrayList<RequestParameter>();
        RequestParameter context_route_value = new RequestParameter("context_route_value", "12");
        RequestParameter context_route_type = new RequestParameter("context_route_type", "1");
        RequestParameter context_login_msisdn = new RequestParameter("context_login_msisdn", svcNum);
        RequestParameter context_oper_id = new RequestParameter("context_oper_id", "");
        RequestParameter fixed_ddr_city = new RequestParameter("fixed_ddr_city", "12");
        RequestParameter fixed_invoke_channel = new RequestParameter("fixed_invoke_channel", "hnmcc_channel");

        RequestParameter p1 = new RequestParameter("phoneNum", svcNum);
        ;
        RequestParameter st1 = new RequestParameter("startcycle", startcycle);
        RequestParameter b1 = new RequestParameter("beginDate", bDate);
        RequestParameter e1 = new RequestParameter("endDate", eDate);
        RequestParameter sd1 = new RequestParameter("sDate", bDate);
        RequestParameter ed1 = new RequestParameter("eDate", eDate);
        RequestParameter sb1 = new RequestParameter("subsid", svcNum);
        RequestParameter i1 = new RequestParameter("itemId", "");

        params.add(p1);
        params.add(st1);
        params.add(b1);
        params.add(e1);
        params.add(sd1);
        params.add(ed1);
        params.add(sb1);
        params.add(i1);

        params.add(context_route_value);
        params.add(context_route_type);
        params.add(context_login_msisdn);
        params.add(context_oper_id);
        params.add(fixed_ddr_city);
        params.add(fixed_invoke_channel);
        return params;
    }

    /**
     * 生成key
     *
     * @param temFlag    0账单类   1详单类  2一般类
     * @param crmRemote  CRM接口编码
     * @param phoneNum   号码
     * @param beginDate  详单类用到的
     * @param endDate    详单类用到的
     * @param startcycle 账单类用到的
     * @return
     */
    private String createKey(String temFlag, String crmRemote, String phoneNum, String beginDate, String endDate, String startcycle, String ecpToCrmCode) {
        String key = "";
        if ("1".equals(temFlag)) {//CRM_RES_接口编码_号码_开始时间_结束时间 类缓存
            key = "CRM_RES_" + crmRemote + "_" + phoneNum + "_" + beginDate + "_" + endDate;
        } else if ("0".equals(temFlag)) {//CRM_RES_接口编码_号码_账期 类缓存
            key = "CRM_RES_" + crmRemote + "_" + phoneNum + "_" + startcycle;
        } else if ("2".equals(temFlag)) {//CRM_RES_接口编码_号码 类缓存/
            if (ecpToCrmCode.equals("hnmcc_find_jbfwb_01")) {
                key = "CRM_RES_" + crmRemote + "_jbfwb_" + phoneNum;
            } else if (ecpToCrmCode.equals("hnmcc_find_zzcp_01")) {
                key = "CRM_RES_" + crmRemote + "_zzcp_" + phoneNum;
            } else {
                key = "CRM_RES_" + crmRemote + "_" + phoneNum;
            }
        }
        return key;
    }

    //获取当前月的前6个月
    private String[] getLast6Months() {
        String[] months = new String[6];
        Calendar cal = Calendar.getInstance();
        months[0] = getYearMonth(cal, 0);
        months[1] = getYearMonth(cal, -1);
        months[2] = getYearMonth(cal, -1);
        months[3] = getYearMonth(cal, -1);
        months[4] = getYearMonth(cal, -1);
        months[5] = getYearMonth(cal, -1);
        return months;
    }

    private String getYearMonth(Calendar cal, int addMonth) {
        cal.add(Calendar.MONTH, addMonth);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return (year + (month < 10 ? "0" : "") + month);
    }

    /**
     * 查询月份信息
     *
     * @return
     */
    private String[] getQueryMonth() {
        Calendar cal = Calendar.getInstance();
        String[] query_ym = new String[6];
        String yearInfo = String.valueOf(cal.get(Calendar.YEAR));
        int monthTemp = cal.get(Calendar.MONTH) + 1;
        String monthInfo = String.valueOf(monthTemp);
        if (monthTemp < 10) {
            monthInfo = "0" + monthInfo;
        }
        query_ym[5] = yearInfo + monthInfo;
        for (int i = 4; i >= 0; i--) {
            cal.add(Calendar.MONTH, -1);
            int month = cal.get(Calendar.MONTH) + 1;
            String monthStr = String.valueOf(month);
            if (month < 10) {
                monthStr = "0" + monthStr;
            }
            query_ym[i] = String.valueOf(cal.get(Calendar.YEAR)) + monthStr;
        }
        return query_ym;
    }

    /**
     * 返回年月日
     *
     * @return yyyyMMdd
     */
    private String getTodayChar8() {
        return DateFormatUtils.format(new Date(), "yyyyMMdd");
    }

    private int getMonthDays(String yearmonth) {
        int year = Integer.parseInt(yearmonth.substring(0, 4));
        int month = Integer.parseInt(yearmonth.substring(4, 6));
        int day = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = 30;
                break;
            case 2:
                if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
                    day = 29;
                    break;
                } else {
                    day = 28;
                    break;
                }
        }
        return day;
    }

    /**
     * 返回年月
     *
     * @return yyyyMM
     */
    private String getTodayChar6() {
        return DateFormatUtils.format(new Date(), "yyyyMM");
    }

    private String invoke920013() {
        String feeEndDate = "";
        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://10.218.105.3:13002/hnmcc_ecp/xwecp.do");
        //props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
        props.put("platform.user", "hnmcc");
        props.put("platform.password", "hnmcc");

        XWECPLIClient client = XWECPLIClient.createInstance(props);
        //逻辑接口调用片段
        LIInvocationContext lic = LIInvocationContext.getContext();
        lic.setBizCode("biz_code_19234");
        lic.setOpType("开通/关闭/查询/变更");
        lic.setUserBrand("12");
        lic.setUserCity("12");
        lic.setUserMobile("15890158325");
        InvocationContext ic = new InvocationContext();
        ic.addContextParameter("login_msisdn", "15890158325");
        ic.addContextParameter("route_type", "2");
        ic.addContextParameter("route_value", "15890158325");

        lic.setContextParameter(ic);

        IQueryCurrentMonthFeeService co = new QueryCurrentMonthFeeServiceImpl();
        QRY920013Result re = null;
        try {
            re = co.currentMonthFee("15890158325");
            if (null != re && re.getResultCode().equals("0")) {
                feeEndDate = re.getFeeEndDate();
            }
        } catch (LIException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return feeEndDate;
    }

    private void invokeSendSms(String msg) {
        //初始化ecp客户端片段
        Properties props = new Properties();
        props.put("client.channel", "hnmcc_channel");
        props.put("platform.url", "http://10.218.105.3:13002/hnmcc_ecp/xwecp.do");
        //props.put("platform.url", "http://112.53.127.41:32812/hnmcc_ecp/xwecp.do");
        props.put("platform.user", "hnmcc");
        props.put("platform.password", "hnmcc");

        XWECPLIClient client = XWECPLIClient.createInstance(props);
        //逻辑接口调用片段
        LIInvocationContext lic = LIInvocationContext.getContext();
        lic.setBizCode("biz_code_19234");
        lic.setOpType("开通/关闭/查询/变更");
        lic.setUserBrand("12");
        lic.setUserCity("12");
        lic.setUserMobile("15890158325");
        InvocationContext ic = new InvocationContext();
        ic.addContextParameter("login_msisdn", "15890158325");
        ic.addContextParameter("route_type", "2");
        ic.addContextParameter("route_value", "15890158325");

        lic.setContextParameter(ic);

        ISendSmsMsgService sendSmsMsgService = new SendSmsMsgServiceClientImpl();
        QRY080001Result result = null;
        try {
            result = sendSmsMsgService.sendSmsMsg("15837179491", msg, "YXDX", "10086");
            result = sendSmsMsgService.sendSmsMsg("18737108266", msg, "YXDX", "10086");
            result = sendSmsMsgService.sendSmsMsg("18737183066", msg, "YXDX", "10086");
        } catch (LIException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String getYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
    }
}
