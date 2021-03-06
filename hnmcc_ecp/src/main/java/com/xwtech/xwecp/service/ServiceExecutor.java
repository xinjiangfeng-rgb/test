package com.xwtech.xwecp.service;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.dao.DAOException;
import com.xwtech.xwecp.dao.IServiceCallerPrivilegeDAO;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.flow.works.WorkFlowFacade;
import com.xwtech.xwecp.log.PerformanceTracer;
import com.xwtech.xwecp.logs.LInterfaceAccessLogger;
import com.xwtech.xwecp.msg.*;
import com.xwtech.xwecp.pojo.ChannelInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class ServiceExecutor {
    private static final Logger logger = LoggerFactory.getLogger(ServiceExecutor.class);

    @Autowired
    private ServiceLocator serviceLocator;

    @Autowired
    private MessageHelper messageHelper;

    @Autowired
    private IServiceCallerPrivilegeDAO serviceCallerPrivilegeDAO;

    @Autowired
    private SequenceGenerator accessIdGenerator;

    public static interface ServiceConstants {
        // 地市路由参数
        String USER_CITY = "fixed_ddr_city";

        // 来源渠道参数
        String INVOKE_CHANNEL = "fixed_invoke_channel";
    }

    public SequenceGenerator getAccessIdGenerator() {
        return accessIdGenerator;
    }

    public void setAccessIdGenerator(SequenceGenerator accessIdGenerator) {
        this.accessIdGenerator = accessIdGenerator;
    }

    public MessageHelper getMessageHelper() {
        return messageHelper;
    }

    public void setMessageHelper(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public IServiceCallerPrivilegeDAO getServiceCallerPrivilegeDAO() {
        return serviceCallerPrivilegeDAO;
    }

    public void setServiceCallerPrivilegeDAO(IServiceCallerPrivilegeDAO serviceCallerPrivilegeDAO) {
        this.serviceCallerPrivilegeDAO = serviceCallerPrivilegeDAO;
    }

    /**
     * 接口处理
     *
     * @param xmlRequest
     * @param clientIp
     * @return
     */
    public String executeService(String xmlRequest, String clientIp, String serIp, String serPort) {
        long begin = System.currentTimeMillis();
        PerformanceTracer pt = PerformanceTracer.getInstance();
        // long n = 0;
        // long accessTime = System.currentTimeMillis();
        long n = pt.addBreakPoint();
        long accessTime = n;
        String accessId = this.accessIdGenerator.next();
        pt.trace("取到sequence[" + accessId + "]...", n);

        ServiceMessage requestMsg = null;
        ServiceMessage responseMsg = null;

        String clientAccessId = "";
        long responseTime = accessTime;
        Exception stackTrace = null;
        String traceId = "";
        try {
            n = pt.addBreakPoint();
//            System.out.println(">>>>>>>yjq222>>>>>"+xmlRequest);
            requestMsg = this.xmlMessage2Object(xmlRequest);

            pt.trace("反序列化XML请求报文成JAVA对象...", n);

            clientAccessId = requestMsg.getHead().getSequence();
            requestMsg.getHead().setSequence(accessId);
            RequestData data = (RequestData) requestMsg.getData();
            // traceId=(String) data.getContext().toContextParameter().get("traceId");
            // traceId = traceId==null ? "":traceId;
            if (null != data) {
                InvocationContext context = data.getContext();
                if (null != context) {
                    Map<String, Object> map = context.toContextParameter();
                    if (null != map) {
                        traceId = (String) map.get("traceId");
                    }
                }
            }
            // traceId = (String)data.getContext().toContextParameter().get("traceId");
            traceId = traceId == null ? "" : traceId;
            // 获得渠道信息
            // 接入渠道编码
            String channel = requestMsg.getHead().getChannel();
            ChannelInfo channelInfo = this.queryChannelInfo(channel);

            // 控制限制 获得返回结果
            responseMsg = WorkFlowFacade.getInstance()
                    .startExec(this, messageHelper, requestMsg, clientIp, channelInfo);
            long end = System.currentTimeMillis();
            if ((end - begin) / 1000 > 1) {
                logger.error("Ecp本地接口：[" + requestMsg.getHead().getCmd() + "],耗时[" + (end - begin) / 1000 + "秒],请求信息：accessId[" + accessId
                        + "],mobile[" + requestMsg.getHead().getUserMobile()
                        + "],resultCode[" + ((ResponseData) responseMsg.getData()).getServiceResult().getResultCode() + "]");
            }
        } catch (Exception e) {
            logger.error("", e);
            stackTrace = e;
        }

        if (stackTrace != null) {
            responseMsg = messageHelper.createErrorResponseMessage(requestMsg);
        }

        // write access log
        String xmlResponse = null;
        if (responseMsg != null) {
            n = pt.addBreakPoint();
            xmlResponse = responseMsg.toString();
            pt.trace("序列化成XML报文...", n);
        }
        // responseTime = System.currentTimeMillis();
        n = pt.addBreakPoint();
        responseTime = n;


        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("minaCfg.properties");
        Properties p = new Properties();
        try {
            p.load(inputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String crmIp = p.getProperty("crm.host");
        String crmPort = p.getProperty("crm.port");
        logger.error("CRM的请求地址:" + crmIp);

        LInterfaceAccessLogger.log(xmlRequest,
                xmlResponse,
                accessId,
                requestMsg,
                accessTime,
                responseTime,
                responseMsg,
                clientIp,
                stackTrace,
                clientAccessId,
                traceId,
                serIp,
                serPort,
                crmIp,
                crmPort);
        // LInterfaceAccessLogger.log(xmlRequest, xmlResponse, accessId, requestMsg, accessTime, responseTime,
        // responseMsg, clientIp, stackTrace, clientAccessId);

        pt.trace("记录日志...", n);

        return xmlResponse;
    }

    private ServiceMessage xmlMessage2Object(String xmlRequest)
            throws Exception {
        ServiceMessage msg = ServiceMessage.fromXML(xmlRequest);
        return msg;
    }

    private ServiceInfo findService(String cmd, List<RequestParameter> params)
            throws Exception {
        return this.serviceLocator.locate(cmd, params);
    }

    /**
     * 添加渠道流量信息到队列中
     *
     * @param channel
     * @param cmd
     */
    /*
     * protected void addChannelFlowInfo(ServiceMessage msg) { String channel = msg.getHead().getChannel(); String cmd =
     * msg.getHead().getCmd(); ChannelFlowInfo flowInfo = new ChannelFlowInfo(); flowInfo.setChannel(channel);
     * flowInfo.setCmd(cmd); flowInfo.setRequestTime(DateTimeUtil.getTodayChar17());
     *
     * ChannelFlowQueue.getInstance().push(flowInfo); }
     */

    /**
     * @param msg
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public BaseServiceInvocationResult callService(ServiceMessage msg)
            throws Exception {
        List<RequestParameter> params = ((RequestData) (msg.getData())).getParams();
        InvocationContext context = ((RequestData) (msg.getData())).getContext();
        if (context != null) {
            Map<String, Object> map = context.toContextParameter();
            for (Iterator iterator = map.keySet().iterator(); iterator.hasNext(); ) {
                Object key = iterator.next();
                RequestParameter contextParam = new RequestParameter();
                contextParam.setParameterName("context_" + key.toString());
                contextParam.setParameterValue(map.get(key));
                params.add(contextParam);
            }
        }

        //把请求头中的traceId，userMobile,userBrand,userCity放到params中，以便后面记录日志的时候获取
        RequestParameter contextParam1 = new RequestParameter();
        contextParam1.setParameterName("header_traceId");
        contextParam1.setParameterValue(msg.getHead().getTranceId());
        params.add(contextParam1);
        RequestParameter contextParam2 = new RequestParameter();
        contextParam2.setParameterName("header_usermobile");
        contextParam2.setParameterValue(msg.getHead().getUserMobile());
        params.add(contextParam2);
        RequestParameter contextParam3 = new RequestParameter();
        contextParam3.setParameterName("header_userbrand");
        contextParam3.setParameterValue(msg.getHead().getUserBrand());
        params.add(contextParam3);
        RequestParameter contextParam4 = new RequestParameter();
        contextParam4.setParameterName("header_usercity");
        contextParam4.setParameterValue(msg.getHead().getUserCity());
        params.add(contextParam4);
        RequestParameter contextParam5 = new RequestParameter();
        contextParam5.setParameterName("header_pagenum");
        contextParam5.setParameterValue(msg.getHead().getPageNum());
        params.add(contextParam5);
        RequestParameter contextParam6 = new RequestParameter();
        contextParam6.setParameterName("header_recnum");
        contextParam6.setParameterValue(msg.getHead().getRecNum());
        params.add(contextParam6);
        RequestParameter contextParam7 = new RequestParameter();
        contextParam7.setParameterName("header_serialnum");
        contextParam7.setParameterValue(msg.getHead().getSerialNum());
        params.add(contextParam7);
        RequestParameter contextParam8 = new RequestParameter();
        contextParam8.setParameterName("header_jfserialnum");
        contextParam8.setParameterValue(msg.getHead().getJfserialNum());
        params.add(contextParam8);

        RequestParameter contextParam9 = new RequestParameter();
        contextParam9.setParameterName("header_prodId");
        contextParam9.setParameterValue(msg.getHead().getProdId());
        params.add(contextParam9);
        // 增加一个固有参数fixed_ddr_city
        String userCity = msg.getHead().getUserCity();
        RequestParameter userCityParam = new RequestParameter(ServiceConstants.USER_CITY, userCity == null ? ""
                : userCity);
        params.add(userCityParam);

        // 修改结束(BOSS分地市割接)
        ServiceInfo sInfo = this.findService(msg.getHead().getCmd(), params);

        // 2010年7月28日修改, 掌厅接入, 掌厅向BOSS传递的有几个参数和网厅不一样
        String invokeChannel = msg.getHead() != null ? msg.getHead().getChannel() : "";
        invokeChannel = invokeChannel == null ? "" : invokeChannel;
        RequestParameter invokeChannelParam = new RequestParameter(ServiceConstants.INVOKE_CHANNEL, invokeChannel);
        params.add(invokeChannelParam);
        // 修改结束,掌厅接入的来源渠道传递

        /***********************************************************************
         * 2011-10-10 增加分渠道参数内容organ_id request_source等内容
         */
        // 获得渠道对应的参数配置内容
        Map<String, String> map = this.getServiceCallerPrivilegeDAO().getChannelExtParams(invokeChannel);
        if (map != null && !map.isEmpty()) {
            Iterator<String> iterator = map.keySet().iterator();
            String key = null;
            while (iterator.hasNext()) {
                key = iterator.next();
                RequestParameter parameter = new RequestParameter(key, map.get(key) == null ? "" : map.get(key));
                params.add(parameter);
            }
        }
        /***********************************************************************
         * 修改结束
         **********************************************************************/

        // 2011-11-15 maofw修改 在增加ServiceInfo对象中增加ServiceMessage对象内容
        sInfo.setServiceMessage(msg);
        // 修改结束

        return callService(sInfo);
    }


    public BaseServiceInvocationResult callService(ServiceInfo sInfo)
            throws Exception {
        ServiceMessage msg = sInfo.getServiceMessage();
        RequestData requestDate = (RequestData) msg.getData();
        String mobile = (String) getParameters(requestDate.getParams(), "context_login_msisdn");
        String phoneNum = (String) getParameters(requestDate.getParams(), "phoneNum");
        String cmd = msg.getHead().getCmd();
        String phone = msg.getHead().getUserMobile();
        BaseServiceInvocationResult res = null;
        Map<String, String> redisData = ((WellFormedDAO) XWECPApp.SPRING_CONTEXT.getBean("wellFormedDAO")).getRedisData();

        //如果数据库配置了该接口编号需要缓存，就从缓存里面查询数据
        if (null == mobile) {
            mobile = "";
        }
        if (null == phoneNum) {
            phoneNum = "";
        }
        if (redisData.containsKey(cmd) && mobile.equals(phoneNum) && phoneNum.equals(phone)) {
//			logger.info("null    ====    "+XWECPApp.redisCli.get((phone+"_"+cmd).getBytes()));
            //如果redis服务器中信息为空就按原流程走
            if (null == XWECPApp.redisCli.get((phone + "_" + cmd).getBytes())) {

                res = sInfo.getServiceInstance().execute(msg.getHead().getSequence());
                //返回报文成功时记录
                if ("0".equals(res.getResultCode())) {
                    XWECPApp.redisCli.set((phone + "_" + cmd), res);
                    XWECPApp.redisCli.pExpireAt((phone + "_" + cmd), returnTime((String) redisData.get(cmd)));
                }
            } else {
                res = (BaseServiceInvocationResult) XWECPApp.redisCli.get((phone + "_" + cmd).getBytes());
            }
        } else {
            res = sInfo.getServiceInstance().execute(msg.getHead().getSequence());
        }

        return res;
    }

    /*
     * 根据渠道编码获得渠道对象
     *
     * @param channel
     *
     * @return
     */
    private ChannelInfo queryChannelInfo(String channel) {
        ChannelInfo channelInfo = null;
        try {
            channelInfo = this.getServiceCallerPrivilegeDAO().getChannelInfo(channel);

        } catch (DAOException e) {
            logger.error("获取渠道信息出错！");
            channelInfo = null;
        }
        return channelInfo;
    }

    protected Object getParameters(final List<RequestParameter> params, String parameterName) {
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

    /**
     * 获取到期的时间戳
     *
     * @return
     */
    public long returnTime(String times) {
        Calendar calender = new GregorianCalendar();
        calender.set(calender.get(Calendar.YEAR), calender.get(Calendar.MONTH), calender.get(Calendar.DATE), 0, 0, 0);
        calender.add(Calendar.MONTH, 1);
        calender.set(Calendar.DAY_OF_MONTH, 1);
//		calender.set(Calendar.HOUR, 9);
        calender.add(Calendar.SECOND, -1);
//		calender.getTime();
        long time = calender.getTimeInMillis();
        return time;
    }
}