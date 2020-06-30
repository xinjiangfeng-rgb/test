package com.xwtech.xwecp.communication;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.Jedis.RedisClientNew;
import com.xwtech.xwecp.Jedis.RedisService;
import com.xwtech.xwecp.dao.GroupDetectionDAO;
import com.xwtech.xwecp.log.PerformanceTracer;
import com.xwtech.xwecp.logs.BossRequestLogger;
import com.xwtech.xwecp.msg.SequenceGenerator;
import com.xwtech.xwecp.service.server.DefaultServiceImpl;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.util.MessageJsonUtil;
import com.xwtech.xwecp.util.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;


@Component(value = "bossRemote")
public class RemoteImpl implements IRemote {
    private static final Logger logger = LoggerFactory.getLogger(RemoteImpl.class);

    @Resource(name = "communicateAdapter")
    private ICommunicateAdapter adapter;

    @Resource(name = "communicateAdapterForRemoting")
    private ICommunicateAdapter jbossRemotingAdapter;


    @Resource(name = "groupDetectionDAO")
    private GroupDetectionDAO groupDetectionDAO;

    @Resource(name = "redisService")
    private RedisService redisService;


    @Autowired
    private SequenceGenerator bossRequestIdGenerator;

    private static final Map<String, String> statusCode = new HashMap<String, String>() {
        {
            put("400", "错误请求");
            put("401", "未授权");
            put("403", "禁止");
            put("404", "未找到");
            put("408", "请求超时");
            put("500", "服务器内部错误");
            put("501", "尚未实施");
            put("502", "错误网关");
            put("503", "服务不可用");
            put("504", "网关超时");
            put("505", "http版本不受支持");

        }
    };

    // 详单缓存处理
    private List<String> billDetailList = new ArrayList<>();
    // 账单缓存处理
    private List<String> billList = new ArrayList<>();
    // 简单查询接口缓存处理----缓存2H
    private List<String> normalListNew = new ArrayList<>();

    private List<String> otherOne = new ArrayList<>();

    private List<String> otherTwo = new ArrayList<>();

    {
        billDetailList.add("940031");
        billDetailList.add("940032");
        billDetailList.add("940033");
        billDetailList.add("940034");
        billDetailList.add("940035");
        billDetailList.add("940036");
        billDetailList.add("940037");
        billDetailList.add("200828");
        billDetailList.add("201626");

        billList.add("920131");
        billList.add("920132");
        billList.add("920133");
        billList.add("920134");
        billList.add("920135");
        billList.add("920136");
        billList.add("920137");
        billList.add("920138");
        billList.add("920139");
        billList.add("201603");

        normalListNew.add("201051");
        normalListNew.add("920107");
        normalListNew.add("400065");
        normalListNew.add("920500");
        normalListNew.add("210200");
        normalListNew.add("201508");
        normalListNew.add("201607");
        normalListNew.add("201606");
        normalListNew.add("980052");
        normalListNew.add("920018");
        normalListNew.add("200420");
        normalListNew.add("920118");
        normalListNew.add("920012");
        normalListNew.add("200910");
        normalListNew.add("200887");
        normalListNew.add("201605");
        normalListNew.add("920317");
        normalListNew.add("100801");

        otherOne.add("201628");

        otherTwo.add("912114");
    }


    public List<String> getBillDetailList() {
        return billDetailList;
    }

    public void setBillDetailList(List<String> billDetailList) {
        this.billDetailList = billDetailList;
    }

    public List<String> getBillList() {
        return billList;
    }

    public void setBillList(List<String> billList) {
        this.billList = billList;
    }

    public List<String> getNormalListNew() {
        return normalListNew;
    }

    public void setNormalListNew(List<String> normalListNew) {
        this.normalListNew = normalListNew;
    }

    public GroupDetectionDAO getGroupDetectionDAO() {
        return this.groupDetectionDAO;
    }

    public void setGroupDetectionDAO(GroupDetectionDAO groupDetectionDAO) {
        this.groupDetectionDAO = groupDetectionDAO;
    }

    public RedisService getRedisService() {
        return this.redisService;
    }

    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    // private IMemcachedManager cache;
    //
    // public IMemcachedManager getCache()
    // {
    // return cache;
    // }
    //
    // public void setCache(IMemcachedManager cache)
    // {
    // this.cache = cache;
    // }

    @Autowired
    private RedisClientNew cache;

    public RedisClientNew getCache() {
        return cache;
    }

    public void setCache(RedisClientNew cache) {
        this.cache = cache;
    }

    public SequenceGenerator getBossRequestIdGenerator() {
        return bossRequestIdGenerator;
    }

    public void setBossRequestIdGenerator(SequenceGenerator bossRequestIdGenerator) {
        this.bossRequestIdGenerator = bossRequestIdGenerator;
    }

    public ICommunicateAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(ICommunicateAdapter adapter) {
        this.adapter = adapter;
    }

    public ICommunicateAdapter getJbossRemotingAdapter() {
        return jbossRemotingAdapter;
    }

    public void setJbossRemotingAdapter(ICommunicateAdapter jbossRemotingAdapter) {
        this.jbossRemotingAdapter = jbossRemotingAdapter;
    }

    // public Object callRemote(IStreamableMessage request)
    // throws CommunicateException
    // {
    // long starttime=System.currentTimeMillis();
    // PerformanceTracer pt = PerformanceTracer.getInstance();
    // long n = 0;
    // if (this.adapter != null)
    // {
    // String identity = request.getIdentity();
    // n = pt.addBreakPoint();
    // ICommunicator communicator =
    // this.adapter.findCommunicatorForRequest(request);
    // pt.trace("查找communicator...", n);
    // String req = request.toMessage();
    // //解析请求参数，获取CRM接口编码，手机号或者唯一标识
    // String crmRemote=new String(req.getBytes(),19,6);
    // String phoneNum="";
    // String crmCache="";
    // boolean reqflag=false;
    // boolean cacheflag=false;
    // //缓存失效时间
    // long expreInSeconds=15*60*1000;
    // logger.info("crmRemote------------"+crmRemote);
    // long beginTime, endTime;
    // beginTime = endTime = System.currentTimeMillis();
    // if(StringUtils.isNotBlank(crmRemote)&&!"100502".equals(crmRemote)){
    // if(req.getBytes().length>=131){
    // phoneNum=new String(req.getBytes(),120,11);
    // }
    // if(StringUtils.isNotBlank(phoneNum)){
    // //详单缓存
    // if(billDetailList.contains(crmRemote)){
    // crmCache="CRM_RES_"+crmRemote+"_"+phoneNum+"_"+new
    // String(req.getBytes(),132,8)+"_"+new String(req.getBytes(),141,8);
    // reqflag = true;
    // logger.info("cache---type----billDetailList");
    // }else if(billList.contains(crmRemote)){
    // crmCache="CRM_RES_"+crmRemote+"_"+phoneNum+"_"+new
    // String(req.getBytes(),132,6);
    // reqflag = true;
    // logger.info("cache---type----billList");
    // }else if(normalListNew.contains(crmRemote)){
    // crmCache="CRM_RES_"+crmRemote+"_"+phoneNum;
    // reqflag = true;
    // logger.info("cache---type----normalList");
    // }else if(otherOne.contains(crmRemote)){
    // crmCache="CRM_RES_"+crmRemote+"_"+phoneNum+"_"+new
    // String(req.getBytes(),132,10)+"_"+new String(req.getBytes(),142,10);
    // reqflag = true;
    // logger.info("cache---type----otherOne");
    // }else if(otherTwo.contains(crmRemote)){
    // if(identity.equals("hnmcc_find_jbfwb_01")){
    // crmCache = "CRM_RES_" + crmRemote + "_jbfwb_" + phoneNum;
    // reqflag = true;
    // logger.info("cache---type----otherTwo");
    // } else if(identity.equals("hnmcc_find_zzcp_01")){
    // crmCache = "CRM_RES_" + crmRemote + "_zzcp_" + phoneNum;
    // reqflag = true;
    // logger.info("cache---type----otherTwo");
    // }
    // }
    // logger.info("crmRemote------------"+crmCache);
    // }
    // }
    //
    // //*************新增**请求的时候唯一流水做key，请求报文做value，以便把超时的报文记录的缓存中
    // long expreSelf=5*60*1000;
    // String key = new String(req.getBytes(), 0, 25);//请求和相应报文匹配的唯一流水
    // this.cache.add(key, req, expreSelf);
    //
    //
    // String accessId = "UNKNOWN";
    // //String identity = request.getIdentity();
    // Exception stackTrace = null;
    // if (request instanceof DefaultServiceImpl.StringTeletext)
    // {
    // accessId = ((DefaultServiceImpl.StringTeletext)(request)).getAccessId();
    // }
    //
    // String ret = null,res = "";
    //
    // //先判断号码是不是集团探测号码，如果是就先读集团探测单独的缓存，如果集团探测的缓存中不存在再走老的流程
    // //如果是当月的1号，直接走老的流程
    // if(req.getBytes().length>=131){
    // phoneNum=new String(req.getBytes(),120,11);
    // }
    // List<Map<String,Object>> telNums =
    // groupDetectionDAO.getAllTelNum(phoneNum);
    // //List<Map<String,Object>> tems = groupDetectionDAO.getAllTem();
    // if(null != telNums && telNums.size()>0) {
    // if (!(crmRemote.equals("920012") && judgeDate())) {
    // //组织key，从缓存中取数据
    // String deteKey = "";
    // if (identity.equals("cc_cgetscoredetail_556") ||
    // identity.equals("ac_agetacctbkseqjk_515")) {//CRM_RES_接口编码_号码_开始时间_结束时间
    // 类缓存
    // deteKey = "CRM_RES_" + crmRemote + "_" + phoneNum + "_" + new
    // String(req.getBytes(), 132, 8) + "_" + new String(req.getBytes(), 141,
    // 8);
    // } else if (identity.equals("ac_acqryrealtimebill_309") ||
    // identity.equals("ac_acqryrealtimebill_920138") ||
    // identity.equals("ac_acqryrealtimebill_201603")) {//CRM_RES_接口编码_号码_账期 类缓存
    // deteKey = "CRM_RES_" + crmRemote + "_" + phoneNum + "_" + new
    // String(req.getBytes(), 132, 6);
    // } else if (identity.equals("cc_currentdayfee_01") ||
    // identity.equals("cc_currentbalance_01") ||
    // identity.equals("cc_cgetusercust_69") ||
    // identity.equals("cc_QueryUserCreditLevel_69") ||
    // identity.equals("cc_authstate_01") ||
    // identity.equals("cc_cgetuseraccscore_770") ||
    // identity.equals("cc_qrypackageInfo_201605") ||
    // identity.equals("ac_alreadymwcp_100801") ||
    // identity.equals("cc_find_package_62")) {//CRM_RES_接口编码_号码 类缓存
    // deteKey = "CRM_RES_" + crmRemote + "_" + phoneNum;
    // } else if(identity.equals("hnmcc_find_jbfwb_01")){
    // deteKey = "CRM_RES_" + crmRemote + "_jbfwb_" + phoneNum;
    // } else if(identity.equals("hnmcc_find_zzcp_01")){
    // deteKey = "CRM_RES_" + crmRemote + "_zzcp_" + phoneNum;
    // }
    // if(!"".equals(deteKey)){
    // ret = redisService.get(deteKey);
    // logger.info("*********jttc*********phoneNum:"+phoneNum+"|key:"+deteKey+"|deteKey:"+ret);
    // }
    // }
    // }
    //
    // if(StringUtils.isBlank(ret)){
    // try
    // {
    // n = pt.addBreakPoint();
    // //从缓存中取数据，不存则请求CRM
    // if(null != this.getCache().get(crmCache)){
    // ret = JSON.parse(this.getCache().get(crmCache)).toString();
    // }
    // logger.info("cache--get---res---"+ret);
    // if(StringUtils.isBlank(ret))
    // {
    // ret = communicator.send2CRM(req, "");
    // cacheflag=true;
    // }
    // pt.trace("纯BOSS通信...", n);
    // }
    // catch (Exception e)
    // {
    // e.printStackTrace();
    // stackTrace = e;
    // }
    // }
    //
    // /**
    // * 添加用来解决crm空响应的问题
    // */
    // ret = packSocket(ret);
    // //获取CRM执行结果
    // String resCode = new String(ret.getBytes(),66,4);
    // //结果为成功状态添加到缓存中
    // if("0000".endsWith(resCode)&&reqflag&&cacheflag)
    // {
    //// this.cache.add(crmCache, ret, expreInSeconds);
    // this.cache.add(crmCache, JSON.toJSONString(ret), expreInSeconds);
    // logger.info("cache---add----"+crmCache);
    // }
    // logger.info("----CRM response
    // time-------"+(StringUtils.isNotBlank(ret)?ret.substring(0,
    // 25):"")+"----"+(System.currentTimeMillis()-starttime)+"ms");
    // // end
    // endTime = System.currentTimeMillis();
    // n = pt.addBreakPoint();
    // String errorMsg = "";
    // res = ret;
    // if(!"0000".endsWith(resCode)){
    // errorMsg = MessageUtil.parse(ret).getBody().toString();
    // }
    // if("0000".equals(resCode)){
    // //res = new String(ret.getBytes(),1,119);
    // if(ret.getBytes().length>400){
    // res = new String(ret.getBytes(),0,400);
    // }else{
    // res = new String(ret.getBytes(),0,ret.getBytes().length);
    // }
    // }
    // n = pt.addBreakPoint();
    // String bossRequestId = this.bossRequestIdGenerator.next();
    // pt.trace("取BOSS请求sequence...", n);
    // n = pt.addBreakPoint();
    // RemoteCallContext remoteCallContext = request.getContext();
    //
    // // 在context中找traceId
    // String traceId = "";
    // if (null != remoteCallContext)
    // {
    // traceId = remoteCallContext.getTraceId() == null ? "" :
    // remoteCallContext.getTraceId();
    //
    // }
    //
    // if((endTime-starttime)/1000>1){
    // String bossCode = new String(req.getBytes(),19,6);
    // logger.error("请求CRM接口："+bossCode+"],耗时:::["+(endTime-starttime)/1000+"秒],请求信息：accessId["+accessId+"],mobile["+phoneNum
    // +"],resultCode["+resCode+"]");
    // }
    //
    // BossRequestLogger.log(bossRequestId,
    // accessId,
    // identity,
    // beginTime,
    // endTime,
    // req.replaceAll(" ", "_"),
    // res.replaceAll(" ", "_"),
    // stackTrace != null,
    // stackTrace,
    // resCode,
    // resCode,
    // errorMsg,
    // traceId);
    //
    // pt.trace("记录BOSS访问日志...", n);
    // if (stackTrace != null)
    // {
    // throw new CommunicateException(stackTrace);
    // }
    // return ret;
    // }
    // else
    // {
    // throw new CommunicateException("没有配置Communicate Filter, 通讯模块无法适配!");
    // }
    // }

    public Object callRemote(IStreamableMessage request) throws CommunicateException {
        long starttime = System.currentTimeMillis();
        PerformanceTracer pt = PerformanceTracer.getInstance();
        long n = 0;
        if (this.adapter != null && this.jbossRemotingAdapter != null) {
            String reqType = "";
            String sysParam = "";
            String busiParam = "";
            String headTraceId = "";
            String headUserMobile = "";
            String headUserBrand = "";
            String headUserCity = "";
            String headPageNum = "";
            String headRecNum = "";
            String headSerialNum = "";
            String headJfserialNum = "";
            String prodId = "";
            if (request instanceof DefaultServiceImpl.StringTeletext) {
                reqType = ((DefaultServiceImpl.StringTeletext) (request)).getType();
                sysParam = ((DefaultServiceImpl.StringTeletext) (request)).getSysParam();
                busiParam = ((DefaultServiceImpl.StringTeletext) (request)).getBusiParam();
                headTraceId = ((DefaultServiceImpl.StringTeletext) (request)).getTraceId();
                headUserMobile = ((DefaultServiceImpl.StringTeletext) (request)).getUserMobile();
                headUserBrand = ((DefaultServiceImpl.StringTeletext) (request)).getUserBrand();
                headUserCity = ((DefaultServiceImpl.StringTeletext) (request)).getUserCity();
                headPageNum = ((DefaultServiceImpl.StringTeletext) (request)).getPageNum();
                headRecNum = ((DefaultServiceImpl.StringTeletext) (request)).getRecNum();
                headSerialNum = ((DefaultServiceImpl.StringTeletext) (request)).getSerialNum();
                headJfserialNum = ((DefaultServiceImpl.StringTeletext) (request)).getJfserialNum();
                prodId = ((DefaultServiceImpl.StringTeletext) (request)).getProdId();
                System.out.println(">>>>>>>>>>>reqType:" + reqType + "|sysParam:" + sysParam + "|busiParam:" + busiParam + "|headTraceId:" + headTraceId + "|headUserMobile:" + headUserMobile + "|headUserBrand:" + headUserBrand + "|headUserCity:" + headUserCity
                        + "|pageNum:" + headPageNum + "|recNum:" + headRecNum + "|serialNum:" + headSerialNum + "|jfserialNum:" + headJfserialNum + "|prodId：" + prodId);

            }

            String ret = null, res = "";
            if (StringTeletext.DEFAULT_REQ_TYPE.equals(reqType) || null == reqType) {
                String identity = request.getIdentity();
                n = pt.addBreakPoint();
                ICommunicator communicator = this.adapter.findCommunicatorForRequest(request);
                pt.trace("查找communicator...", n);
                String req = request.toMessage();

                // 解析请求参数，获取CRM接口编码，手机号或者唯一标识
                String crmRemote = new String(req.getBytes(), 19, 6);
                String phoneNum = "";
                String crmCache = "";
                boolean reqflag = false;
                boolean cacheflag = false;
                // 缓存失效时间
                long expreInSeconds = 15 * 60 * 1000;
                // logger.info("crmRemote------------"+crmRemote);
                long beginTime, endTime;
                beginTime = endTime = System.currentTimeMillis();
                if (StringUtils.isNotBlank(crmRemote) && !"100502".equals(crmRemote)) {
                    if (req.getBytes().length >= 131) {
                        phoneNum = new String(req.getBytes(), 120, 11);
                    }
                    if (StringUtils.isNotBlank(phoneNum)) {
                        // 详单缓存
                        if (billDetailList.contains(crmRemote)) {
                            crmCache = "CRM_RES_" + crmRemote + "_" + phoneNum + "_"
                                    + new String(req.getBytes(), 132, 8) + "_" + new String(req.getBytes(), 141, 8);
                            reqflag = true;
                        } else if (billList.contains(crmRemote)) {
                            crmCache = "CRM_RES_" + crmRemote + "_" + phoneNum + "_"
                                    + new String(req.getBytes(), 132, 6);
                            reqflag = true;
                        } else if (normalListNew.contains(crmRemote)) {
                            crmCache = "CRM_RES_" + crmRemote + "_" + phoneNum;
                            reqflag = true;
                        } else if (otherOne.contains(crmRemote)) {
                            crmCache = "CRM_RES_" + crmRemote + "_" + phoneNum + "_"
                                    + new String(req.getBytes(), 132, 10) + "_" + new String(req.getBytes(), 142, 10);
                            reqflag = true;
                        } else if (otherTwo.contains(crmRemote)) {
                            if (identity.equals("hnmcc_find_jbfwb_01")) {
                                crmCache = "CRM_RES_" + crmRemote + "_jbfwb_" + phoneNum;
                                reqflag = true;
                            } else if (identity.equals("hnmcc_find_zzcp_01")) {
                                crmCache = "CRM_RES_" + crmRemote + "_zzcp_" + phoneNum;
                                reqflag = true;
                            }
                            //2017 08 28 流量管家，实时话费页面，充值记录缓存
                        } else if ("cc_queryFreeFlowForMonth".equals(identity) || "cc_queryFlowForFreeRes".equals(identity)
                                || "cc_currentdayfee_01".equals(identity) || "cc_qrycurrentmonthfee_01".equals(identity)
                                || "cc_queryVoiceRealAmout".equals(identity) || "cc_querypayhistorynew_01".equals(identity)
                                ) {
                            expreInSeconds = 2 * 60 * 1000;
                            crmCache = "CRM_RES_" + identity + "_" + reqType + "_" + phoneNum;
                            reqflag = true;
                        }
                    }
                }

                // *************新增**请求的时候唯一流水做key，请求报文做value，以便把超时的报文记录的缓存中
                long expreSelf = 5 * 60 * 1000;
                String key = new String(req.getBytes(), 0, 25);// 请求和相应报文匹配的唯一流水
                this.cache.add(key, req, expreSelf);

                String accessId = "UNKNOWN";
                // String identity = request.getIdentity();
                Exception stackTrace = null;
                if (request instanceof DefaultServiceImpl.StringTeletext) {
                    accessId = ((DefaultServiceImpl.StringTeletext) (request)).getAccessId();
                }

                // 先判断号码是不是集团探测号码，如果是就先读集团探测单独的缓存，如果集团探测的缓存中不存在再走老的流程
                // 如果是当月的1号，直接走老的流程
                if (req.getBytes().length >= 131) {
                    phoneNum = new String(req.getBytes(), 120, 11);
                }
                boolean groupDetection = groupDetectionDAO.getAllTelNum(phoneNum);
                // List<Map<String,Object>> tems =
                // groupDetectionDAO.getAllTem();
                if (groupDetection) {
                    if (!(crmRemote.equals("920012") && judgeDate())) {
                        // 组织key，从缓存中取数据
                        String deteKey = "";
                        if (identity.equals("cc_cgetscoredetail_556") || identity.equals("ac_agetacctbkseqjk_515")) {// CRM_RES_接口编码_号码_开始时间_结束时间
                            // 类缓存
                            deteKey = "CRM_RES_" + crmRemote + "_" + phoneNum + "_" + new String(req.getBytes(), 132, 8)
                                    + "_" + new String(req.getBytes(), 141, 8);
                        } else if (identity.equals("ac_acqryrealtimebill_309")
                                || identity.equals("ac_acqryrealtimebill_920138")
                                || identity.equals("ac_acqryrealtimebill_201603")) {// CRM_RES_接口编码_号码_账期
                            // 类缓存
                            deteKey = "CRM_RES_" + crmRemote + "_" + phoneNum + "_"
                                    + new String(req.getBytes(), 132, 6);
                        } else if (identity.equals("cc_currentdayfee_01") || identity.equals("cc_currentbalance_01")
                                || identity.equals("cc_cgetusercust_69")
                                || identity.equals("cc_QueryUserCreditLevel_69") || identity.equals("cc_authstate_01")
                                || identity.equals("cc_cgetuseraccscore_770")
                                || identity.equals("cc_qrypackageInfo_201605")
                                || identity.equals("ac_alreadymwcp_100801") || identity.equals("cc_find_package_62")) {// CRM_RES_接口编码_号码
                            // 类缓存
                            deteKey = "CRM_RES_" + crmRemote + "_" + phoneNum;
                        } else if (identity.equals("hnmcc_find_jbfwb_01")) {
                            deteKey = "CRM_RES_" + crmRemote + "_jbfwb_" + phoneNum;
                        } else if (identity.equals("hnmcc_find_zzcp_01")) {
                            deteKey = "CRM_RES_" + crmRemote + "_zzcp_" + phoneNum;
                        } else if (identity.equals("cc_queryVoiceRealAmout2")) {
                            deteKey = "CRM_RES_" + crmRemote + "_" + phoneNum;
                        }
                        if (!"".equals(deteKey)) {
                            ret = redisService.get(deteKey);
                            logger.info("redis cache data: " + ret);
                        }
                    }
                }


                if (StringUtils.isBlank(ret)) {
                    try {
                        n = pt.addBreakPoint();
                        String cacheStr = this.getCache().get(crmCache);
                        // 从缓存中取数据，不存则请求CRM
                        if (null != cacheStr) {
                            ret = cacheStr;
                            logger.info("CRM__RESPONSE:  responseMsg from cache");
                        }

                        if (StringUtils.isBlank(ret)) {
                            ret = communicator.send2CRM(req, "");
                            cacheflag = true;
                        }
                        pt.trace("BOSS通信...", n);
                        logger.info("CRM__REQUEST: " + req+"-------------CRM__RESPONSE: " + ret);
                        //logger.info("CRM__RESPONSE: " + ret);
                    } catch (Exception e) {
                        e.printStackTrace();
                        stackTrace = e;
                    }
                }
                /**
                 * 添加用来解决crm空响应的问题
                 */
                ret = packSocket(ret);
                // 获取CRM执行结果
                String resCode = new String(ret.getBytes(), 66, 4);
                // 结果为成功状态添加到缓存中
                if ("0000".endsWith(resCode) && reqflag && cacheflag) {
                    // this.cache.add(crmCache, ret, expreInSeconds);

                    this.cache.add(crmCache, ret, expreInSeconds);
                    // logger.info("cache---add----"+crmCache);
                }
                // logger.info("----CRM response
                // time-------"+(StringUtils.isNotBlank(ret)?ret.substring(0,
                // 25):"")+"----"+(System.currentTimeMillis()-starttime)+"ms");
                // end
                endTime = System.currentTimeMillis();
                n = pt.addBreakPoint();
                String errorMsg = "";
                res = ret;
                if (!"0000".endsWith(resCode)) {
                    errorMsg = MessageUtil.parse(ret).getBody().toString();
                }
                if ("0000".equals(resCode)) {
                    // res = new String(ret.getBytes(),1,119);
                    if (ret.getBytes().length > 400) {
                        res = new String(ret.getBytes(), 0, 400);
                    } else {
                        res = new String(ret.getBytes(), 0, ret.getBytes().length);
                    }
                }
                n = pt.addBreakPoint();
                String bossRequestId = this.bossRequestIdGenerator.next();
                pt.trace("取BOSS请求sequence...", n);
                n = pt.addBreakPoint();
                RemoteCallContext remoteCallContext = request.getContext();

                // 在context中找traceId
                String traceId = "";
                if (null != remoteCallContext) {
                    traceId = remoteCallContext.getTraceId() == null ? "" : remoteCallContext.getTraceId();

                }

                if ((endTime - starttime) / 1000 > 1) {
                    String bossCode = new String(req.getBytes(), 19, 6);
                    logger.error("请求CRM接口：" + bossCode + "],耗时:::[" + (endTime - starttime) / 1000 + "秒],请求信息：accessId["
                            + accessId + "],mobile[" + phoneNum + "],resultCode[" + resCode + "]");
                }

                BossRequestLogger.log(bossRequestId,
                        accessId,
                        identity,
                        beginTime,
                        endTime,
                        req.replaceAll(" ", "_"),
                        res.replaceAll(" ", "_"),
                        stackTrace != null,
                        stackTrace,
                        resCode,
                        resCode,
                        errorMsg,
                        traceId,
                        headTraceId,
                        headUserMobile,
                        headUserBrand,
                        headUserCity,
                        headPageNum,
                        headRecNum,
                        headSerialNum,
                        headJfserialNum,
                        prodId);
                System.out.println("记录日志结束。。。。。。");

                pt.trace("记录BOSS访问日志...", n);
                if (stackTrace != null) {
                    throw new CommunicateException(stackTrace);
                }
            } else if (StringTeletext.REMOTING_REQ_TYPE.equals(reqType)) {
                long expreInSeconds = 15 * 60 * 1000;
                boolean putRedis = false;
                String identity = request.getIdentity();
                String mobile = "";
                String cacheKey = "";
                if ("hnmcc_find_yxhd_01".equals(identity)) {
                    try {
                        mobile = (String) JSONObject.parseObject(busiParam).get("SvcNum");
                        cacheKey = "JSON_RES_" + identity + mobile;
                        boolean cache = Boolean.parseBoolean((String) JSONObject.parseObject(busiParam).get("cache"));
                        putRedis = true;//接口出来的数据放缓存
                        //如果使用缓存
                        if (cache) {
                            ret = this.getCache().get(cacheKey);
                            if (StringUtils.isNotBlank(ret)) {
                                logger.info(">>>>>>>>>>>>" + identity + "从缓存取");
                                logger.info("********redis_cache****" + ret);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }//2017 08 28 流量管家，实时话费页面，充值记录缓存
                else if ("cc_queryFreeFlowForMonth".equals(identity) || "cc_queryFlowForFreeRes".equals(identity)
                        || "cc_currentdayfee_01".equals(identity) || "cc_qrycurrentmonthfee_01".equals(identity)
                        || "cc_queryVoiceRealAmout".equals(identity) || "cc_querypayhistorynew_01".equals(identity)
                        ) {
                    JSONObject json = JSONObject.parseObject(busiParam);
                    String phoneNum = (String) json.get("SvcNum");
                    if (phoneNum == null) {
                        phoneNum = (String) json.get("BillId");
                    }
                    if (phoneNum != null) {
                        expreInSeconds = 2 * 60 * 1000;
                        cacheKey = "JSON_RES_" + identity + "_" + reqType + "_" + phoneNum;
                        putRedis = true;//接口出来的数据放缓存
                        ret = this.getCache().get(cacheKey);
                        if (StringUtils.isNotBlank(ret)) {
                            logger.info(">>>>>>>>>>>>" + identity + "从缓存取");
                            logger.info("********redis_cache****" + ret);
                        }
                    }

                }


                n = pt.addBreakPoint();
                ICommunicator communicator = this.jbossRemotingAdapter.findCommunicatorForRequest(request);
                pt.trace("查找communicator...", n);
                String req = request.toMessage();

                long beginTime, endTime;
                beginTime = endTime = System.currentTimeMillis();

                String accessId = "UNKNOWN";
                Exception stackTrace = null;
                if (request instanceof DefaultServiceImpl.StringTeletext) {
                    accessId = ((DefaultServiceImpl.StringTeletext) (request)).getAccessId();
                }

                if (StringUtils.isBlank(ret)) {
                    try {
                        n = pt.addBreakPoint();
                        ret = communicator.send2CRM(sysParam, busiParam);
                        logger.info("********jbossRemoting:req****" + sysParam + "|" + busiParam +"!!!!!!!"+"********jbossRemoting:rsp****" + ret);
                        pt.trace("纯BOSS通信...", n);
                    } catch (Exception e) {
                        e.printStackTrace();
                        stackTrace = e;
                    }
                }

                /**
                 * 添加用来解决crm空响应的问题
                 */
                ret = packJson(ret);

                // end
                endTime = System.currentTimeMillis();
                n = pt.addBreakPoint();
                res = ret;

                MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) ret);
                String resCode = bossJson.getBossCode();
                String bossDesc = bossJson.getBossDesc();

                if ("00000".equals(resCode)) {
                    // res = new String(ret.getBytes(),1,119);
                    if (ret.getBytes().length > 400) {
                        res = new String(ret.getBytes(), 0, 400);
                    } else {
                        res = new String(ret.getBytes(), 0, ret.getBytes().length);
                    }


                    if (putRedis) {
                        this.cache.add(cacheKey, ret, expreInSeconds);
                    }

                }
                n = pt.addBreakPoint();
                String bossRequestId = this.bossRequestIdGenerator.next();
                pt.trace("取BOSS请求sequence...", n);
                n = pt.addBreakPoint();
                RemoteCallContext remoteCallContext = request.getContext();

                // 在context中找traceId
                String traceId = "";
                if (null != remoteCallContext) {
                    traceId = remoteCallContext.getTraceId() == null ? "" : remoteCallContext.getTraceId();
                }

                if ((endTime - starttime) / 1000 > 1) {
                    String bossCode = "";
                    logger.error("请求CRM接口：" + bossCode + "],耗时:::[" + (endTime - starttime) / 1000 + "秒],请求信息：accessId["
                            + accessId + "],resultCode[" + resCode + "]");
                }

                BossRequestLogger.remoteLog(bossRequestId,
                        accessId,
                        identity,
                        beginTime,
                        endTime,
                        sysParam + "|" + busiParam,
                        res.replaceAll(" ", "_"),
                        stackTrace != null,
                        stackTrace,
                        resCode,
                        resCode,
                        bossDesc,
                        traceId,
                        headTraceId,
                        headUserMobile,
                        headUserBrand,
                        headUserCity,
                        headPageNum,
                        headRecNum,
                        headSerialNum,
                        headJfserialNum);

                pt.trace("记录BOSS访问日志...", n);
                if (stackTrace != null) {
                    throw new CommunicateException(stackTrace);
                }

            }
            return ret;
        } else {
            throw new CommunicateException("没有配置Communicate Filter, 通讯模块无法适配!");
        }
    }

    protected String[] getResponseInfo(String responseMsg) {
        // TODO
        return new String[]{responseMsg, new String(responseMsg.getBytes(), 66, 4), ""};
    }

    protected String[] getResponseInfo2(String responseMsg) {
        if (responseMsg == null) {
            return new String[]{"", "", ""};
        }
        int respResultIndexStart = responseMsg.indexOf("<resp_result>");
        int respResultIndexEnd = responseMsg.indexOf("</resp_result>");

        int respCodeIndexStart = responseMsg.indexOf("<resp_code>");
        int respCodeIndexEnd = responseMsg.indexOf("</resp_code>");

        int respDescIndexStart = responseMsg.indexOf("<resp_desc>");
        int respDescIndexEnd = responseMsg.indexOf("</resp_desc>");
        boolean hasCdata = false;
        if (respDescIndexStart > -1 && respDescIndexEnd > -1) {
            String tempString = responseMsg.substring(respDescIndexStart, respDescIndexEnd);
            hasCdata = tempString.indexOf("<![") > -1;
        }
        // 清单查询返回字段与其他报文不一样
        int respTypeIndexStart = responseMsg.indexOf("<resp_type>");
        int respTypeIndexEnd = responseMsg.indexOf("</resp_type>");

        if ((respResultIndexStart == -1 && respTypeIndexStart == -1) || respCodeIndexStart == -1) {
            return new String[]{"", "", ""};
        } else {
            String respResult = "";
            if (respResultIndexStart != -1) {
                respResult = responseMsg.substring(respResultIndexStart + 13, respResultIndexEnd);
            }
            if (respTypeIndexStart != -1) {
                respResult = responseMsg.substring(respTypeIndexStart + 11, respTypeIndexEnd);
            }
            String respCode = responseMsg.substring(respCodeIndexStart + 11, respCodeIndexEnd);
            String respDesc = "";
            if (respDescIndexStart != -1) {
                if (hasCdata) {
                    respDesc = responseMsg.substring(respDescIndexStart + 20, respDescIndexEnd - 3);
                } else {
                    respDesc = responseMsg.substring(respDescIndexStart + 11, respDescIndexEnd);
                }
            }
            return new String[]{respResult, respCode, respDesc};
        }
    }

    /**
     * 用于CRM空响应
     *
     * @param ret
     * @return
     */
    private String packSocket(String ret) {
        if (null == ret || "".equals(ret) || "null".equals(ret)) {
            String des = statusCode.get("408");
            ret = "MHF0000000000000086940032XZT01 HNYD00201504221521210001  13110 37 9999请求超时                                  FFFFFFFF";

        }
        return ret;
    }

    /**
     * 用于CRM空响应
     *
     * @param ret
     * @return
     */
    private String packJson(String ret) {
        if (null == ret || "".equals(ret) || "null".equals(ret)) {
            ret = "{\"respCode\": \"99999\",\"respDesc\": \"请求超时\",\"result\": {\"SO_MEMBER_DEAL\": [{}]}}";
        }
        return ret;
    }

    /**
     * 用于CRM空响应
     *
     * @param ret
     * @return
     */
    private String packXML(String ret) {
        if (null == ret || "".equals(ret) || "null".equals(ret) || null != statusCode.get(ret)) {
            String des = null == statusCode.get(ret) ? "" : statusCode.get(ret);
            ret = "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "<operation_out>" + "<process_code/>" + "<sysfunc_id/>"
                    + "<response_time/>" + "<request_source/>" + "<request_seq/>" + "<request_type/>" + "<content/>"
                    + "<response>" + "<resp_type>1</resp_type>" + "<resp_code>-99999</resp_code>" + "<resp_desc>CRM状态码："
                    + ret + des + "</resp_desc>" + "</response>" + "</operation_out>";

        }
        return ret;
    }

    public static void main(String[] args) {

        logger.info("123123123fadda");


    }

    // 流量银行发送请求
//    public Object callJsonRemote(IStreamableMessage request) throws CommunicateException {
//        PerformanceTracer pt = PerformanceTracer.getInstance();
//        long n = 0;
//        if (this.adapter != null) {
//            n = pt.addBreakPoint();
//            ICommunicator communicator = this.adapter.findCommunicatorForRequest(request);
//            pt.trace("查找communicator...", n);
//            String req = request.toMessage();
//            long beginTime, endTime;
//            beginTime = endTime = System.currentTimeMillis();
//            String accessId = "UNKNOWN";
//            String identity = request.getIdentity();
//            Exception stackTrace = null;
//            if (request instanceof DefaultServiceImpl.StringTeletext) {
//                accessId = ((DefaultServiceImpl.StringTeletext) (request)).getAccessId();
//            }
//
//            String ret = null;
//            try {
//                n = pt.addBreakPoint();
//                // 向流量银行服务器发送请求
//                ret = communicator.send2CRM(req, "");
//                pt.trace("流量银行通信...", n);
//            } catch (Exception e) {
//                stackTrace = e;
//            }
//            /**
//             * 添加用来解决流量银行空响应的问题
//             */
//            ret = packJSON(ret);
//
//            endTime = System.currentTimeMillis();
//            n = pt.addBreakPoint();
//            String[] responseInfo = this.getJsonResponseInfo(ret);
//            pt.trace("getResponseInfo...", n);
//            n = pt.addBreakPoint();
//            String bossRequestId = this.bossRequestIdGenerator.next();
//            pt.trace("取BOSS请求sequence...", n);
//            n = pt.addBreakPoint();
//            RemoteCallContext remoteCallContext = request.getContext();
//            String traceId = "";
//            if (null != remoteCallContext) {
//                traceId = remoteCallContext.getTraceId() == null ? "" : remoteCallContext.getTraceId();
//
//            }
//            com.xwtech.xwecp.log.BossRequestLogger.log(bossRequestId,
//                    accessId,
//                    identity,
//                    beginTime,
//                    endTime,
//                    req,
//                    ret,
//                    stackTrace != null,
//                    stackTrace,
//                    responseInfo[0],
//                    responseInfo[1],
//                    responseInfo[2],
//                    traceId);
//            pt.trace("记录访问流量银行服务日志...", n);
//            if (stackTrace != null) {
//                throw new CommunicateException(stackTrace);
//            }
//            return ret;
//        } else {
//            throw new CommunicateException("没有配置Communicate Filter, 通讯模块无法适配!");
//        }
//    }

    // 处理流量银行返回的结果集
//    protected String[] getJsonResponseInfo(String responseMsg) {
//        if (responseMsg == null) {
//            return new String[]{"", "", ""};
//        }
//        String respResult = "";
//        String respCode = "";
//        String respDesc = "";
//        try {
//            TBankResponseMessage readResponse = TbankJsonUtil.readResponse(TBankResponseMessage.class,
//                    new ByteArrayInputStream(responseMsg.getBytes("UTF-8")));
//            respResult = readResponse.getResponse().getRspType();
//            respCode = readResponse.getResponse().getRspCode();
//            respDesc = readResponse.getResponse().getRspDesc();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new String[]{respResult, respCode, respDesc};
//    }

    /**
     * 用于流量银行空响应
     *
     * @param ret
     * @return
     */
    private String packJSON(String ret) {
        if (null == ret || "".equals(ret) || "null".equals(ret) || null != statusCode.get(ret)) {
            ret = "{\"jsonrpc\":\"2.0\"," + "\"id\":\"-5797640080114934769\"," + "\"result\":{" + "\"actionCode\":0,"
                    + "\"processTime\":1405409366421," + "\"msgSender\":\"ECP\"," + "\"svcContVer\":\"V1.0\","
                    + "\"svcCont\":null," + "\"response\":{"
                    + "\"rspType\":\"-1\",\"rspCode\":\"-99999\",\"rspDesc\":\"流量银行空响应\"}," + "\"bipcode\":null,"
                    + "\"bipcode\":null," + "\"bipver\":\"V1.0\"}}";
        }
        return ret;
    }

    // 判断当前是不是月初第一天
    private boolean judgeDate() {
        boolean resultBo = false;
        // 获得一个日历对象
        Calendar c = Calendar.getInstance();
        // 得到本月的那一天
        int today = c.get(c.DAY_OF_MONTH);
        // 然后判断是不是本月的第一天
        if (today == 1) {
            resultBo = true;
        }
        return resultBo;
    }

    public List<String> getOtherOne() {
        return otherOne;
    }

    public void setOtherOne(List<String> otherOne) {
        this.otherOne = otherOne;
    }

    public List<String> getOtherTwo() {
        return otherTwo;
    }

    public void setOtherTwo(List<String> otherTwo) {
        this.otherTwo = otherTwo;
    }
}
