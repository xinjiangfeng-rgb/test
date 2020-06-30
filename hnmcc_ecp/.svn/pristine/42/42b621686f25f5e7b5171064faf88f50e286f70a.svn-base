package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.communication.RemoteCallContext;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.*;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.MessageJsonUtil;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.MessageUtil.Message;
import com.xwtech.xwecp.util.ParseXmlConfig;
import com.xwtech.xwecp.util.TeletextParseUtils;
import org.apache.commons.lang3.StringUtils;
import org.jdom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 语音话单
 *
 * @author gongww 2009-12-04
 */
public class BillQueryInvocation extends BaseInvocation implements ILogicalService {
    private static final Logger logger = LoggerFactory.getLogger(BillQueryInvocation.class);

    private BossTeletextUtil bossTeletextUtil;

    private IRemote remote;

    private WellFormedDAO wellFormedDAO;

    private ParseXmlConfig config;

    public BillQueryInvocation() {
        ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
        this.bossTeletextUtil = (BossTeletextUtil) (springCtx.getBean("bossTeletextUtil"));
        this.remote = (IRemote) (springCtx.getBean("bossRemote"));
        this.wellFormedDAO = (WellFormedDAO) (springCtx.getBean("wellFormedDAO"));
        this.config = new ParseXmlConfig();
    }

    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
                                                      List<RequestParameter> params) {
        QRY010001Result res = new QRY010001Result();

        try {
            res.setResultCode("0");
            res.setErrorMessage("");
            String beginDate = "";
            int queryType = 0;
            boolean findBeginDate = false;
            boolean findQueryType = false;
            for (RequestParameter param : params) {
                String paramName = param.getParameterName();
                if (paramName.equals("beginDate")) {
                    beginDate = (String) param.getParameterValue();
                    findBeginDate = true;
                } else if (paramName.equals("queryType")) {
                    queryType = Integer.parseInt(param.getParameterValue().toString());
                    findQueryType = true;
                }
                if (findBeginDate && findQueryType)
                    break;
            }

            // 月份
            String month = beginDate.substring(0, 6);
            RequestParameter paramMonth = new RequestParameter("month", month);
            params.add(paramMonth);

            switch (queryType) {
                // 查询语音话单OK
                case 1:
                    res.setGsmBillDetail(this.getGsmCdrInfo(accessId, config, params, res));
                    break;
                // 套餐详单
                case 2:
                    res.setIpcarBillDetail(this.getIpcarCdrInfo(accessId, config, params, res));
                    break;
                // 自有增值业务扣费详单
                case 3:
                    res.setIspBillDetail(this.getIspCdrInfo(accessId, config, params, res));
                    break;
                // 代扣费业务详单
                case 4:
                    res.setMontnetBillDetail(this.getMontnetCdrInfo(accessId, config, params, res));
                    break;
                // 查询彩信清单
                case 5:
                    res.setMmsBillDetail(this.getMmsCdrInfo(accessId, config, params, res));
                    break;
                // 查询短信清单
                case 6:
                    res.setSmsBillDetail(this.getSmsCdrInfo(accessId, config, params, res));
                    break;
                // 查询国际短信清单
                case 7:
                    res.setIneSmsBillDetail(this.getIneSmsCdrInfo(accessId, config, params, res));
                    break;
                // 查询GPRS清单(上网类详单查询)
                case 8:
                    res.setGprsBillDetail(this.getGprsCdrInfo(accessId, config, params, res));
                    break;
                // 查询短号码清单
                case 9:
                    res.setVpmnBillDetail(this.getVpmnCdrInfo(accessId, config, params, res));
                    break;
                // 查询WLAN清单
                case 10:
                    res.setWlanBillDetail(this.getWlanCdrInfo(accessId, config, params, res));
                    break;
                // 查询96121清单
                case 11:
                    res.setAc121BillDetail(this.getAc121CdrInfo(accessId, config, params, res));
                    break;
                // 查询17202清单
                case 12:
                    res.setIsp2BillDetail(this.getIsp2CdrInfo(accessId, config, params, res));
                    break;
                // 查询USSD清单
                case 13:
                    res.setUssdBillDetail(this.getUssdCdrInfo(accessId, config, params, res));
                    break;
                // 查询无线音乐清单
                case 14:
                    res.setMpmusicBillDetail(this.getMpmusicCdrInfo(accessId, config, params, res));
                    break;
                // 查询LBS清单
                case 15:
                    res.setLbsBillDetail(this.getLbsCdrInfo(accessId, config, params, res));
                    break;
                // 即时群聊清单
                case 16:
                    res.setMeetBillDetail(this.getMeetCdrInfo(accessId, config, params, res));
                    break;
                // 视频通话清单
                case 17:
                    res.setGsmVideoBillDetail(this.getGsmVideoCdrInfo(accessId, config, params, res));
                    break;
                // 随E行上网本清单
                case 18:
                    res.setCmnetBillDetail(this.getCmnetCdrInfo(accessId, config, params, res));
                    break;
                // 自有梦网清单
                case 19:
                    res.setMontnetBillDetail(this.getMontnetDSCdrInfo(accessId, config, params, res));
                    break;
                // 查询语音杂志
                case 20:
                    res.setGsmMagaBillDetail(this.getGsmCdrMagazineInfo(accessId, config, params, res));
                    break;
                // 游戏消费详单
                case 22:
                    res.setCdrsBillDetail(this.getGsmCDRQueryInfo(accessId, config, params, res));
                    break;
                // 游戏点数详单
                case 23:
                    res.setCdrsPointBillDetail(this.getGsmCDRQueryPointInfo(accessId, config, params, res));
                    break;
            }

        } catch (Exception e) {
            logger.error("", e);
        }
        logger.info("---------------------------------" + res.getBossCode());
        return res;
    }

    /**
     * 查询语音话单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<GsmBillDetail> getGsmCdrInfo(String accessId, ServiceConfig config, List<RequestParameter> params,
                                             QRY010001Result res) {
        List<GsmBillDetail> reList = new ArrayList<GsmBillDetail>();
        String type = StringTeletext.DEFAULT_REQ_TYPE;
        String REQUEST_BOSSTELETEXT="ac_gsmcdr_query_501";
        for (int i = 0; i < params.size(); i++) {
            RequestParameter r = params.get(i);
            System.out.print("RequestParameter:" + r.getParameterName() + "|" + String.valueOf(r.getParameterValue()));
        }

        String filterParam = String.valueOf(this.getParameter(params, "filterParam").getParameterValue());
        System.out.print("filterParam:" + filterParam);
        String rspXml = "";
        String reqXml ="";
        ErrorMapping errDt = null;
        try {
			reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT, params);

        	Map<String, Object> remotingMap = this.bossTeletextUtil.mergeRemoteTeletext(REQUEST_BOSSTELETEXT, params);
			String sysParam = "";
			String busiParam = "";
			if(null!=remotingMap){
				sysParam = remotingMap.get("sysParam")==null?"":(String)remotingMap.get("sysParam");
				busiParam = remotingMap.get("busiParam")==null?"":(String)remotingMap.get("busiParam");
				type = remotingMap.get("type")==null?"0":(String)remotingMap.get("type");
			}

			String headTraceId = "";
			String headUserMobile = "";
			String headUserBrand = "";
			String headUserCity = "";
			String headPageNum = "";
			String headRecNum = "";
			String headSerialNum = "";
			String headJfserialNum = "";
            String headProdId = "";
			if (null != params && 0 < params.size()) {
				for (RequestParameter parameter : params) {
					String paramName = parameter.getParameterName();
					if ("header_traceId".equals(paramName)) {
						headTraceId = (String)parameter.getParameterValue();
					}else if ("header_usermobile".equals(paramName)) {
						headUserMobile = (String)parameter.getParameterValue();
					}else if ("header_userbrand".equals(paramName)) {
						headUserBrand = (String)parameter.getParameterValue();
					}else if ("header_usercity".equals(paramName)) {
						headUserCity = (String)parameter.getParameterValue();
					}
					else if ("header_pagenum".equals(paramName)) {
						headPageNum = (String) parameter.getParameterValue();
					} else if ("header_recnum".equals(paramName)) {
						headRecNum = (String) parameter.getParameterValue();
					} else if ("header_serialnum".equals(paramName)) {
						headSerialNum = (String) parameter.getParameterValue();
					} else if ("header_jfserialnum".equals(paramName)) {
						headJfserialNum = (String) parameter.getParameterValue();
					}else if ("header_prodId".equals(paramName)) {
                        headProdId = (String)parameter.getParameterValue();
                    }
				}
			}
			headTraceId = headTraceId == null ? "" : headTraceId;
			headUserMobile = headUserMobile == null ? "" : headUserMobile;
			headUserBrand = headUserBrand == null ? "" : headUserBrand;
			headUserCity = headUserCity == null ? "" : headUserCity;

			headPageNum = headPageNum == null ? "" : headPageNum;
			headRecNum = headRecNum == null ? "" : headRecNum;
			headSerialNum = headSerialNum == null ? "" : headSerialNum;
			headJfserialNum = headJfserialNum == null ? "" : headJfserialNum;
            headProdId = headProdId == null ? "" : headProdId;
			rspXml = (String)this.remote.callRemote(new StringTeletext(sysParam,busiParam,type,reqXml, accessId,
					REQUEST_BOSSTELETEXT, this.generateCity(params),headTraceId,headUserMobile,headUserBrand,headUserCity,
					headPageNum,headRecNum,headSerialNum,headJfserialNum,headProdId));
          

            logger.info(" ====== 查询语音话单返回报文 ======\n" + rspXml);
            if (null != rspXml && !"".equals(rspXml)) {
                //能力开放平台
                if (StringTeletext.REMOTING_REQ_TYPE.equals(type)) {

                    MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) rspXml);
                    String bossCode = bossJson.getBossCode();
                    String bossDesc = bossJson.getBossDesc();
                    if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
                        res.setBossCode(LOGIC_ERROR);
                        res.setErrorCode(LOGIC_ERROR);
                        res.setErrorMessage(bossDesc);
                    } else {
                        JSONArray json = bossJson.getJSONArrayResult();
                        if (json != null && json.size() > 0) {
                            for (int i = 0; i < json.size(); i++) {
                                JSONObject obj = (JSONObject) json.get(i);
                                GsmBillDetail detail = new GsmBillDetail();
                                detail.setCall_type(obj.getString("CALL_TYPE"));
                                detail.setCharge(obj.getString("CHARGE"));
                                detail.setDuration(obj.getString("DURATION"));
                                detail.setFree_res(obj.getString("FREE_RES"));
                                detail.setOpp_number(obj.getString("OPP_NUMBER"));
                                detail.setVplmn(obj.getString("VPLMN"));
                                detail.setToll_type(obj.getString("TOLL_TYPE"));
                                detail.setOffer_name(obj.getString("OFFER_NAME"));
                                detail.setStart_time(obj.getString("START_TIME"));
                                detail.setTotal_info(obj.getString("TOTAL_INFO"));
                                reList.add(detail);
                            }
                        }
                        res.setResultCode(MessageJsonUtil.LOGIC_SUCESS);
                        res.setBossCode(bossCode);
                        res.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());
                    }

                } else {
                	
                	   reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT, params);
                     RemoteCallContext city = this.generateCity(params);
                     StringTeletext teletext =    new StringTeletext(reqXml, accessId, "ac_gsmcdr_query_501", city);
                    Message bossMessage = MessageUtil.parse(rspXml);
                    if (null != bossMessage) {
                        if (MessageUtil.BOSS_CODE.equals(bossMessage.getHead().getCode())) {
                            res.setResultCode(MessageUtil.LOGIC_SUCESS);
                            res.setBossCode(bossMessage.getHead().getCode());
                            res.setErrorMessage(bossMessage.getHead().getDesc());

                            List<String[]> rowList = bossMessage.getBody().asList();

                            //取出第一条数据中的totalinfo
                            String totalInfo = "";
                            String[] tmp = rowList.get(0);
                            if (null != tmp && tmp.length > 0) {
                                totalInfo = tmp[9];
                            }

                            GsmBillDetail billDetail = null;
                            if (StringUtils.isNotBlank(filterParam)) {
                                for (String[] columns : rowList) {
                                    if (filterParam.equals(columns[3])) {
                                        billDetail = new GsmBillDetail();
                                        billDetail.setStart_time(columns[0]);
                                        billDetail.setVplmn(columns[1]);
                                        billDetail.setCall_type(columns[2]);
                                        billDetail.setOpp_number(columns[3]);
                                        billDetail.setDuration(columns[4]);
                                        billDetail.setToll_type(columns[5]);
                                        billDetail.setOffer_name(columns[6]);
                                        billDetail.setFree_res(columns[7]);
                                        billDetail.setCharge(columns[8]);
                                        billDetail.setTotal_info(totalInfo);
                                        reList.add(billDetail);
                                    }
                                }
                            } else {
                                for (String[] columns : rowList) {
                                    billDetail = new GsmBillDetail();
                                    billDetail.setStart_time(columns[0]);
                                    billDetail.setVplmn(columns[1]);
                                    billDetail.setCall_type(columns[2]);
                                    billDetail.setOpp_number(columns[3]);
                                    billDetail.setDuration(columns[4]);
                                    billDetail.setToll_type(columns[5]);
                                    billDetail.setOffer_name(columns[6]);
                                    billDetail.setFree_res(columns[7]);
                                    billDetail.setCharge(columns[8]);
                                    billDetail.setTotal_info(totalInfo);
                                    reList.add(billDetail);
                                }
                            }
                        } else {
                            res.setResultCode(MessageUtil.LOGIC_ERROR);
                            res.setBossCode(bossMessage.getHead().getCode());
                            res.setErrorMessage(bossMessage.getHead().getDesc());
                        }
                    } else {
                        res.setResultCode(MessageUtil.LOGIC_ERROR);
                        res.setErrorMessage("请求CRM接口失败.");
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }

        return reList;
    }

    /**
     * 查询套餐详单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<IpcarBillDetail> getIpcarCdrInfo(String accessId, ServiceConfig config, List<RequestParameter> params,
                                                 QRY010001Result res) {
        List<IpcarBillDetail> reList = new ArrayList<IpcarBillDetail>();
        String rspXml = "";

        try {
            RemoteCallContext city = this.generateCity(params);
            rspXml = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_ipcarcdr_query_504", params), accessId,
                    "ac_ipcarcdr_query_504", city));
            logger.debug(" ====== 查询套餐详单清单返回报文 ======\n" + rspXml);

            if (null != rspXml && !"".equals(rspXml)) {
                Message bossMessage = MessageUtil.parse(rspXml);
                if (null != bossMessage) {
                    if (MessageUtil.BOSS_CODE.equals(bossMessage.getHead().getCode())) {
                        res.setResultCode(MessageUtil.LOGIC_SUCESS);
                        res.setBossCode(bossMessage.getHead().getCode());
                        res.setErrorMessage(bossMessage.getHead().getDesc());

                        List<String[]> gprsbillList = bossMessage.getBody().asList();

                        //取出第一条数据中的totalinfo
                        String totalInfo = "";
                        String[] tmp = gprsbillList.get(0);
                        if (null != tmp && tmp.length > 0) {
                            totalInfo = tmp[4];
                        }

                        IpcarBillDetail ipcarBillDetail = null;
                        for (String[] values : gprsbillList) {
                            ipcarBillDetail = new IpcarBillDetail();
                            ipcarBillDetail.setStart_time(values[0]);
                            ipcarBillDetail.setBill_month(values[1]);
                            ipcarBillDetail.setOffer_name(values[2]);
                            ipcarBillDetail.setCharge(values[3]);
                            ipcarBillDetail.setTotal_info(totalInfo);

                            reList.add(ipcarBillDetail);
                        }
                    } else {
                        res.setResultCode(MessageUtil.LOGIC_ERROR);
                        res.setBossCode(bossMessage.getHead().getCode());
                        res.setErrorMessage(bossMessage.getHead().getDesc());
                    }
                } else {
                    res.setResultCode(MessageUtil.LOGIC_ERROR);
                    res.setErrorMessage("请求CRM接口失败.");
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }
        return reList;
    }

    /**
     * 自有增值业务扣费详单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<IspBillDetail> getIspCdrInfo(String accessId, ServiceConfig config, List<RequestParameter> params,
                                             QRY010001Result res) {
        List<IspBillDetail> reList = new ArrayList<IspBillDetail>();
        String rspXml = "";

        try {
            RemoteCallContext city = this.generateCity(params);
            rspXml = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_ispcdr_query_506", params), accessId, "ac_ispcdr_query_506",
                    city));
            logger.debug(" ====== 查询自有增值业务扣费详单返回报文 ======\n" + rspXml);

            if (null != rspXml && !"".equals(rspXml)) {
                Message bossMessage = MessageUtil.parse(rspXml);
                if (null != bossMessage) {
                    if (MessageUtil.BOSS_CODE.equals(bossMessage.getHead().getCode())) {
                        res.setResultCode(MessageUtil.LOGIC_SUCESS);
                        res.setBossCode(bossMessage.getHead().getCode());
                        res.setErrorMessage(bossMessage.getHead().getDesc());

                        List<String[]> gprsbillList = bossMessage.getBody().asList();

                        //取出第一条数据中的totalinfo
                        String totalInfo = "";
                        String[] tmp = gprsbillList.get(0);
                        if (null != tmp && tmp.length > 0) {
                            totalInfo = tmp[5];
                        }

                        IspBillDetail ispBillDetail = null;
                        for (String[] values : gprsbillList) {
                            ispBillDetail = new IspBillDetail();

                            ispBillDetail.setStart_time(values[0]);
                            ispBillDetail.setService_type(values[1]);
                            ispBillDetail.setBusi_name(values[2]);
                            ispBillDetail.setService_code(values[3]);
                            ispBillDetail.setCharge(values[4]);
                            ispBillDetail.setTotal_info(totalInfo);

                            reList.add(ispBillDetail);
                        }
                    } else {
                        res.setResultCode(MessageUtil.LOGIC_ERROR);
                        res.setBossCode(bossMessage.getHead().getCode());
                        res.setErrorMessage(bossMessage.getHead().getDesc());
                    }
                } else {
                    res.setResultCode(MessageUtil.LOGIC_ERROR);
                    res.setErrorMessage("请求CRM接口失败.");
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }
        return reList;
    }

    /**
     * 代扣费业务详单
     */
    public List<MontnetBillDetail> getMontnetDSCdrInfo(String accessId, ServiceConfig config,
                                                       List<RequestParameter> params, QRY010001Result res) {
        List<MontnetBillDetail> reList = new ArrayList<MontnetBillDetail>();
        String rspXml = "";
        String rspXmll = "";
        ErrorMapping errDt = null;

        try {
            RemoteCallContext city = this.generateCity(params);
            rspXmll = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_montnettcdr_query_511", params), accessId,
                    "ac_montnettcdr_query_511", city));
            logger.debug(" ====== 查询梦网代收业务清单返回报文 ======\n" + rspXml);
            if (null != rspXmll && !"".equals(rspXmll)) {

            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }
        return reList;
    }

    /**
     * 代扣费业务详单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<MontnetBillDetail> getMontnetCdrInfo(String accessId, ServiceConfig config,
                                                     List<RequestParameter> params, QRY010001Result res) {
        List<MontnetBillDetail> reList = new ArrayList<MontnetBillDetail>();
        String rspXml = "";
        ErrorMapping errDt = null;

        try {
            RemoteCallContext city = this.generateCity(params);
            rspXml = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_montnetcdr_query_511", params), accessId,
                    "ac_montnetcdr_query_511", city));
            logger.debug(" ====== 查询代扣费业务详单返回报文 ======\n" + rspXml);
            if (null != rspXml && !"".equals(rspXml)) {
                Message bossMessage = MessageUtil.parse(rspXml);
                if (null != bossMessage) {
                    if (MessageUtil.BOSS_CODE.equals(bossMessage.getHead().getCode())) {
                        res.setResultCode(MessageUtil.LOGIC_SUCESS);
                        res.setBossCode(bossMessage.getHead().getCode());
                        res.setErrorMessage(bossMessage.getHead().getDesc());

                        List<String[]> gprsbillList = bossMessage.getBody().asList();

                        //取出第一条数据中的totalinfo
                        String totalInfo = "";
                        String[] tmp = gprsbillList.get(0);
                        if (null != tmp && tmp.length > 0) {
                            totalInfo = tmp[8];
                        }

                        MontnetBillDetail montnetBillDetail = null;
                        for (String[] values : gprsbillList) {
                            montnetBillDetail = new MontnetBillDetail();
                            montnetBillDetail.setStart_time(values[0]);
                            montnetBillDetail.setService_type(values[1]);
                            montnetBillDetail.setBusi_name(values[2]);
                            montnetBillDetail.setService_code(values[3]);
                            montnetBillDetail.setSp_name(values[4]);
                            montnetBillDetail.setSp_code(values[5]);
                            montnetBillDetail.setBill_flag(values[6]);
                            montnetBillDetail.setCharge(values[7]);
                            montnetBillDetail.setTotal_info(totalInfo);

                            reList.add(montnetBillDetail);
                        }
                    } else {
                        res.setResultCode(MessageUtil.LOGIC_ERROR);
                        res.setBossCode(bossMessage.getHead().getCode());
                        res.setErrorMessage(bossMessage.getHead().getDesc());
                    }
                } else {
                    res.setResultCode(MessageUtil.LOGIC_ERROR);
                    res.setErrorMessage("请求CRM接口失败.");
                }

            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }
        return reList;
    }

    /**
     * 查询彩信清单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<MmsBillDetail> getMmsCdrInfo(String accessId, ServiceConfig config, List<RequestParameter> params,
                                             QRY010001Result res) {
        String filterParam = String.valueOf(this.getParameter(params, "filterParam").getParameterValue());
        List<MmsBillDetail> reList = new ArrayList<MmsBillDetail>();

        String rspXml = "";
        try {
            String reqXml = this.bossTeletextUtil.mergeTeletext("ac_mmscdr_query_507", params);
            RemoteCallContext city = this.generateCity(params);
            rspXml = (String) this.remote.callRemote(new StringTeletext(reqXml, accessId, "ac_mmscdr_query_507", city));

            logger.info(" ====== 查询彩信清单返回报文 ======\n" + rspXml);

            Message bossMessage = MessageUtil.parse(rspXml);
            if (null != bossMessage) {
                if (MessageUtil.BOSS_CODE.equals(bossMessage.getHead().getCode())) {
                    res.setResultCode(MessageUtil.LOGIC_SUCESS);
                    res.setBossCode(bossMessage.getHead().getCode());
                    res.setErrorMessage(bossMessage.getHead().getDesc());

                    List<String[]> rowList = bossMessage.getBody().asList();

                    //取出第一条数据中的totalinfo
                    String totalInfo = "";
                    String[] tmp = rowList.get(0);
                    if (null != tmp && tmp.length > 0) {
                        totalInfo = tmp[9];
                    }

                    MmsBillDetail billDetail = null;
                    if (StringUtils.isNotBlank(filterParam)) {
                        for (String[] columns : rowList) {
                            if (filterParam.equals(columns[3])) {
                                billDetail = new MmsBillDetail();
                                billDetail.setStart_time(columns[0]);
                                billDetail.setVplmn(columns[1]);
                                billDetail.setCall_type(columns[2]);
                                billDetail.setOpp_number(columns[3]);
                                billDetail.setToll_type(columns[4]);
                                billDetail.setBusi_name(columns[5]);
                                billDetail.setOffer_name(columns[6]);
                                billDetail.setFree_res(columns[7]);
                                billDetail.setCharge(columns[8]);
                                billDetail.setTotal_info(totalInfo);
                                reList.add(billDetail);
                            }
                        }
                    } else {
                        for (String[] columns : rowList) {
                            billDetail = new MmsBillDetail();
                            billDetail.setStart_time(columns[0]);
                            billDetail.setVplmn(columns[1]);
                            billDetail.setCall_type(columns[2]);
                            billDetail.setOpp_number(columns[3]);
                            billDetail.setToll_type(columns[4]);
                            billDetail.setBusi_name(columns[5]);
                            billDetail.setOffer_name(columns[6]);
                            billDetail.setFree_res(columns[7]);
                            billDetail.setCharge(columns[8]);
                            billDetail.setTotal_info(totalInfo);
                            reList.add(billDetail);
                        }
                    }

                } else {
                    res.setResultCode(MessageUtil.LOGIC_ERROR);
                    res.setBossCode(bossMessage.getHead().getCode());
                    res.setErrorMessage(bossMessage.getHead().getDesc());
                }
            } else {
                res.setResultCode(MessageUtil.LOGIC_ERROR);
                res.setErrorMessage("请求CRM接口失败.");
            }
            // String rspXml = "";
            // ErrorMapping errDt = null;
            //
            // try
            // {
            // rspXml = (String)this.remote.callRemote(new StringTeletext(
            // this.bossTeletextUtil.mergeTeletext("ac_mmscdr_query_507", params), accessId, "ac_mmscdr_query_507",
            // this.generateCity(params)));
            // logger.debug(" ====== 查询彩信清单返回报文 ======\n" + rspXml);
            //
            // if (null != rspXml && !"".equals(rspXml))
            // {
            // Element root = this.config.getElement(rspXml);
            // String resp_code = this.config.getChildText(this.config.getElement(root, "response"), "resp_code");
            // String resp_desc = this.config.getChildText(this.config.getElement(root, "response"), "resp_desc");
            // res.setResultCode("0000".equals(resp_code) ? "0" : "1");
            // res.setErrorCode(resp_code);
            // res.setErrorMessage(resp_desc);
            // if (!"0000".equals(resp_code))
            // {
            // errDt = this.wellFormedDAO.transBossErrCode("QRY010001", "ac_mmscdr_query_507", resp_code);
            // if (null != errDt)
            // {
            // res.setErrorCode(errDt.getLiErrCode());
            // res.setErrorMessage(errDt.getLiErrMsg());
            // }
            // }
            //
            // if (null != resp_code && ("0000".equals(resp_code)))
            // {
            // res.setResultCode("0");
            // try
            // {
            // List<Map<String, String>> respList = null;
            // String retContent = root.getChild("content").getChildText("xtcdr");
            // respList = TeletextParseUtils.parseXTABLE(retContent);
            // if (respList != null)
            // {
            // for (Map<String, String> m : respList)
            // {
            // MmsBillDetail bd = new MmsBillDetail();
            // bd.setStatusType(m.get("通信方式"));
            // bd.setSpCode(m.get("sp服务"));
            // bd.setMmsLen(Long.parseLong(m.get("彩信长度")));
            // bd.setSendTime(m.get("起始时间"));
            // bd.setVisitArear(m.get("通信地点"));
            // bd.setOtherParty(m.get("对方号码"));
            // bd.setOrgSmFee(Double.parseDouble(m.get("基本费") == null || "".equals(m.get("基本费")) ? "0"
            // : m.get("基本费")));
            // bd.setInfoFee(Double.parseDouble(m.get("信息费") == null || "".equals(m.get("信息费")) ? "0"
            // : m.get("信息费")));
            // bd.setTotalFee(Double.parseDouble(m.get("通信费") == null || "".equals(m.get("通信费")) ? "0"
            // : m.get("通信费")));
            // bd.setFeeItem01(Double.parseDouble(m.get("套餐费") == null || "".equals(m.get("套餐费")) ? "0"
            // : m.get("套餐费")));
            // reList.add(bd);
            // }
            // }
            // }
            // catch (Exception ex)
            // {
            // reList.clear();
            // }
            // }
            // }
            // }
            // catch (Exception e)
            // {
            // logger.error("", e);
            // reList = null;
            // }
        } catch (Exception e) {
            logger.error("查询短彩信详单失败.", e);
            reList = null;
        }
        return reList;
    }

    /**
     * 查询短信清单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<SmsBillDetail> getSmsCdrInfo(String accessId, ServiceConfig config, List<RequestParameter> params,
                                             QRY010001Result res) {
        List<SmsBillDetail> reList = new ArrayList<SmsBillDetail>();
        String filterParam = String.valueOf(this.getParameter(params, "filterParam").getParameterValue());
        String rspXml = "";
        ErrorMapping errDt = null;

        try {
            rspXml = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_smscdr_query_503", params), accessId, "ac_smscdr_query_503",
                    this.generateCity(params)));
            logger.debug(" ====== 查询短信清单返回报文 ======\n" + rspXml);

            if (null != rspXml && !"".equals(rspXml)) {
                Element root = this.config.getElement(rspXml);
                String resp_code = this.config.getChildText(this.config.getElement(root, "response"), "resp_code");
                String resp_desc = this.config.getChildText(this.config.getElement(root, "response"), "resp_desc");
                res.setResultCode("0000".equals(resp_code) ? "0" : "1");
                res.setErrorCode(resp_code);
                res.setErrorMessage(resp_desc);
                if (!"0000".equals(resp_code)) {
                    errDt = this.wellFormedDAO.transBossErrCode("QRY010001", "ac_smscdr_query_503", resp_code);
                    if (null != errDt) {
                        res.setErrorCode(errDt.getLiErrCode());
                        res.setErrorMessage(errDt.getLiErrMsg());
                    }
                }

                if (null != resp_code && ("0000".equals(resp_code))) {
                    res.setResultCode("0");
                    try {
                        List<Map<String, String>> respList = null;
                        String retContent = root.getChild("content").getChildText("xtcdr");
                        respList = TeletextParseUtils.parseXTABLE(retContent);
                        if (respList != null) {
                            for (Map<String, String> m : respList) {
                                SmsBillDetail bd = new SmsBillDetail();
                                bd.setStatusType(m.get("通信方式"));
                                bd.setOtherParty(m.get("对方号码"));
                                bd.setStartTime(m.get("起始时间"));
                                bd.setVisitArear(m.get("通信地点"));
                                bd.setTotalFee(Double.parseDouble(m.get("实收费") == null || "".equals(m.get("实收费")) ? "0"
                                        : m.get("实收费")));
                                bd.setFeeItem01(Double.parseDouble(m.get("套餐费") == null || "".equals(m.get("套餐费")) ? "0"
                                        : m.get("套餐费")));
                                // 修改
                                bd.setInfoLen(Long.parseLong(m.get("信息长度")));
                                reList.add(bd);

                            }
                        }
                    } catch (Exception ex) {
                        reList.clear();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }
        return reList;
    }

    /**
     * 查询国际短信清单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<SmsBillDetail> getIneSmsCdrInfo(String accessId, ServiceConfig config, List<RequestParameter> params,
                                                QRY010001Result res) {
        List<SmsBillDetail> reList = new ArrayList<SmsBillDetail>();
        String rspXml = "";
        ErrorMapping errDt = null;

        try {
            rspXml = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_ineSmscdr_query_503", params), accessId,
                    "ac_ineSmscdr_query_503", this.generateCity(params)));
            logger.debug(" ====== 查询国际短信清单返回报文 ======\n" + rspXml);

            if (null != rspXml && !"".equals(rspXml)) {
                Element root = this.config.getElement(rspXml);
                String resp_code = this.config.getChildText(this.config.getElement(root, "response"), "resp_code");
                String resp_desc = this.config.getChildText(this.config.getElement(root, "response"), "resp_desc");
                res.setResultCode("0000".equals(resp_code) ? "0" : "1");
                res.setErrorCode(resp_code);
                res.setErrorMessage(resp_desc);
                if (!"0000".equals(resp_code)) {
                    errDt = this.wellFormedDAO.transBossErrCode("QRY010001", "ac_ineSmscdr_query_503", resp_code);
                    if (null != errDt) {
                        res.setErrorCode(errDt.getLiErrCode());
                        res.setErrorMessage(errDt.getLiErrMsg());
                    }
                }

                if (null != resp_code && ("0000".equals(resp_code))) {
                    res.setResultCode("0");
                    try {
                        List<Map<String, String>> respList = null;
                        String retContent = root.getChild("content").getChildText("xtcdr");
                        respList = TeletextParseUtils.parseXTABLE(retContent);
                        if (respList != null) {
                            for (Map<String, String> m : respList) {
                                SmsBillDetail bd = new SmsBillDetail();
                                bd.setStatusType(m.get("通信方式"));
                                bd.setOtherParty(m.get("对方号码"));
                                bd.setStartTime(m.get("起始时间"));
                                bd.setVisitArear(m.get("通信地点"));
                                bd.setTotalFee(Double.parseDouble(m.get("通信费") == null || "".equals(m.get("通信费")) ? "0"
                                        : m.get("通信费")));
                                bd.setFeeItem01(Double.parseDouble(m.get("套餐费") == null || "".equals(m.get("套餐费")) ? "0"
                                        : m.get("套餐费")));
                                reList.add(bd);
                            }
                        }
                    } catch (Exception ex) {
                        reList.clear();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }
        return reList;
    }

    /**
     * 查询GPRS清单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<GprsBillDetail> getGprsCdrInfo(String accessId, ServiceConfig config, List<RequestParameter> params,
                                               QRY010001Result res) {
        List<GprsBillDetail> reList = new ArrayList<GprsBillDetail>();
        String rspXml = "";
        String reqXml="";
        String type = StringTeletext.DEFAULT_REQ_TYPE;
        String REQUEST_BOSSTELETEXT="ac_gprscdr_query_502";
        ErrorMapping errDt = null;
        try {

        	reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT, params);

        	Map<String, Object> remotingMap = this.bossTeletextUtil.mergeRemoteTeletext(REQUEST_BOSSTELETEXT, params);
			String sysParam = "";
			String busiParam = "";
			if(null!=remotingMap){
				sysParam = remotingMap.get("sysParam")==null?"":(String)remotingMap.get("sysParam");
				busiParam = remotingMap.get("busiParam")==null?"":(String)remotingMap.get("busiParam");
				type = remotingMap.get("type")==null?"0":(String)remotingMap.get("type");
			}

			String headTraceId = "";
			String headUserMobile = "";
			String headUserBrand = "";
			String headUserCity = "";
			String headPageNum = "";
			String headRecNum = "";
			String headSerialNum = "";
			String headJfserialNum = "";
            String headProdId = "";
			if (null != params && 0 < params.size()) {
				for (RequestParameter parameter : params) {
					String paramName = parameter.getParameterName();
					if ("header_traceId".equals(paramName)) {
						headTraceId = (String)parameter.getParameterValue();
					}else if ("header_usermobile".equals(paramName)) {
						headUserMobile = (String)parameter.getParameterValue();
					}else if ("header_userbrand".equals(paramName)) {
						headUserBrand = (String)parameter.getParameterValue();
					}else if ("header_usercity".equals(paramName)) {
						headUserCity = (String)parameter.getParameterValue();
					}
					else if ("header_pagenum".equals(paramName)) {
						headPageNum = (String) parameter.getParameterValue();
					} else if ("header_recnum".equals(paramName)) {
						headRecNum = (String) parameter.getParameterValue();
					} else if ("header_serialnum".equals(paramName)) {
						headSerialNum = (String) parameter.getParameterValue();
					} else if ("header_jfserialnum".equals(paramName)) {
						headJfserialNum = (String) parameter.getParameterValue();
					}else if ("header_prodId".equals(paramName)) {
                        headProdId = (String)parameter.getParameterValue();
                    }
				}
			}
			headTraceId = headTraceId == null ? "" : headTraceId;
			headUserMobile = headUserMobile == null ? "" : headUserMobile;
			headUserBrand = headUserBrand == null ? "" : headUserBrand;
			headUserCity = headUserCity == null ? "" : headUserCity;

			headPageNum = headPageNum == null ? "" : headPageNum;
			headRecNum = headRecNum == null ? "" : headRecNum;
			headSerialNum = headSerialNum == null ? "" : headSerialNum;
			headJfserialNum = headJfserialNum == null ? "" : headJfserialNum;
            headProdId = headProdId == null ? "" : headProdId;

			rspXml = (String)this.remote.callRemote(new StringTeletext(sysParam,busiParam,type,reqXml, accessId,
					REQUEST_BOSSTELETEXT, this.generateCity(params),headTraceId,headUserMobile,headUserBrand,headUserCity,
					headPageNum,headRecNum,headSerialNum,headJfserialNum,headProdId));

            if (null != rspXml && !"".equals(rspXml)) {

                //能力开放平台
                if (StringTeletext.REMOTING_REQ_TYPE.equals(type)) {

                    MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) rspXml);
                    String bossCode = bossJson.getBossCode();
                    String bossDesc = bossJson.getBossDesc();
                    if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
                        res.setBossCode(LOGIC_ERROR);
                        res.setErrorCode(LOGIC_ERROR);
                        res.setErrorMessage(bossDesc);
                    } else {
                        JSONArray json = bossJson.getJSONArrayResult();
                        if (json != null && json.size() > 0) {
                            for (int i = 0; i < json.size(); i++) {
                                JSONObject obj = (JSONObject) json.get(i);
                                GprsBillDetail detail = new GprsBillDetail();
                                detail.setStart_time( obj.getString("START_TIME"));
                                detail.setVplmn(obj.getString("VPLMN"));
                                detail.setService_type(obj.getString("SERVICE_TYPE"));
                                detail.setDuration(obj.getString("DURATION"));
                                detail.setTotal_flow(obj.getString("TOTAL_FLOW"));
                                detail.setOffer_name(obj.getString("OFFER_NAME"));
                                detail.setFree_res(obj.getString("FREE_RES"));
                                detail.setCharge(obj.getString("CHARGE"));
                                detail.setTotal_info(obj.getString("TOTAL_INFO"));

                                reList.add(detail);
                            }
                        }
                        res.setResultCode(MessageJsonUtil.LOGIC_SUCESS);
                        res.setBossCode(bossCode);
                        res.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());
                    }

                } else {
                	
                	 StringTeletext  teletext =    new StringTeletext(
                             this.bossTeletextUtil.mergeTeletext("ac_gprscdr_query_502", params), accessId, "ac_gprscdr_query_502",
                             this.generateCity(params));
                   
                     rspXml = (String) this.remote.callRemote(teletext);
                    Message bossMessage = MessageUtil.parse(rspXml);
                    if (null != bossMessage) {
                        if (MessageUtil.BOSS_CODE.equals(bossMessage.getHead().getCode())) {
                            res.setResultCode(MessageUtil.LOGIC_SUCESS);
                            res.setBossCode(bossMessage.getHead().getCode());
                            res.setErrorMessage(bossMessage.getHead().getDesc());

                            List<String[]> gprsbillList = bossMessage.getBody().asList();

                            //取出第一条数据中的totalinfo
                            String totalInfo = "";
                            String[] tmp = gprsbillList.get(0);
                            if (null != tmp && tmp.length > 0) {
                                totalInfo = tmp[8];
                            }

                            GprsBillDetail gprsBillDetail = null;
                            for (String[] values : gprsbillList) {
                                gprsBillDetail = new GprsBillDetail();
                                gprsBillDetail.setStart_time(values[0]);
                                gprsBillDetail.setVplmn(values[1]);
                                gprsBillDetail.setService_type(values[2]);
                                gprsBillDetail.setDuration(values[3]);
                                gprsBillDetail.setTotal_flow(values[4]);
                                gprsBillDetail.setOffer_name(values[5]);
                                gprsBillDetail.setFree_res(values[6]);
                                gprsBillDetail.setCharge(values[7]);
                                gprsBillDetail.setTotal_info(totalInfo);

                                reList.add(gprsBillDetail);
                            }
                        } else {
                            res.setResultCode(MessageUtil.LOGIC_ERROR);
                            res.setBossCode(bossMessage.getHead().getCode());
                            res.setErrorMessage(bossMessage.getHead().getDesc());
                        }
                    } else {
                        res.setResultCode(MessageUtil.LOGIC_ERROR);
                        res.setErrorMessage("请求CRM接口失败.");
                    }

                }
                // Element root = this.config.getElement(rspXml);
                // String resp_code = this.config.getChildText(this.config.getElement(root, "response"), "resp_code");
                // String resp_desc = this.config.getChildText(this.config.getElement(root, "response"), "resp_desc");
                // res.setResultCode("0000".equals(resp_code) ? "0" : "1");
                // res.setErrorCode(resp_code);
                // res.setErrorMessage(resp_desc);
                // if (!"0000".equals(resp_code))
                // {
                // errDt = this.wellFormedDAO.transBossErrCode("QRY010001", "ac_gprscdr_query_502", resp_code);
                // if (null != errDt)
                // {
                // res.setErrorCode(errDt.getLiErrCode());
                // res.setErrorMessage(errDt.getLiErrMsg());
                // }
                // }
                //
                // if (null != resp_code && ("0000".equals(resp_code)))
                // {
                // res.setResultCode("0");
                // try
                // {
                // List<Map<String, String>> respList = null;
                // String retContent = root.getChild("content").getChildText("xtcdr");
                // respList = TeletextParseUtils.parseXTABLE(retContent);
                // if (respList != null)
                // {
                // for (Map<String, String> m : respList)
                // {
                // GprsBillDetail bd = new GprsBillDetail();
                // // 初始化业务Bean
                // bd.setStatusType(m.get("状态类型"));
                // bd.setStartTime(m.get("起始时间"));
                // bd.setCdrApnni(m.get("上网方式"));
                // bd.setVisitArear(m.get("通话地点"));
                // bd.setDuration(Double.parseDouble(m.get("总时长")));
                // bd.setTotalFlus(Double.parseDouble(m.get("上传流量")) + Double.parseDouble(m.get("下载流量")));
                // bd.setBusyData(Double.parseDouble(m.get("基本收费流量")));
                // bd.setIdlesseData(Double.parseDouble(m.get("其他收费流量")));
                // bd.setBusyFee(Double.parseDouble(m.get("基本费用")));
                // bd.setTotalFee(Double.parseDouble(m.get("总费用")));
                // bd.setOtherFee(Double.parseDouble(m.get("其他费用")));
                // bd.setPackageFee(Double.parseDouble(m.get("套餐费")));
                // String pckDscTmp = m.get("套餐");
                // if (" ".trim().equals(pckDscTmp))
                // {
                // bd.setMsnc("无");
                // }
                // else
                // {
                // bd.setMsnc(pckDscTmp);
                // }
                // // bd.setGroupFee(Double.parseDouble(null!=m.get("统付流量")?"0":m.get("统付流量")));
                // // 统付流量单位：B转成KB
                // bd.setGroupFee(Math.round(Double.parseDouble((m.get("统付流量") == null || m.get("统付流量")
                // .equals("")) ? "0" : m.get("统付流量")) / 1024));
                // // 明细
                // bd.setDetail((m.get("明细") == null || m.get("明细").equals("")) ? "" : m.get("明细"));
                // reList.add(bd);
                // }
                // }
                // }
                // catch (Exception ex)
                // {
                // reList.clear();
                // }
                // }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }
        return reList;
    }

    /**
     * 查询短号码清单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<VpmnBillDetail> getVpmnCdrInfo(String accessId, ServiceConfig config, List<RequestParameter> params,
                                               QRY010001Result res) {
        List<VpmnBillDetail> reList = new ArrayList<VpmnBillDetail>();
        String rspXml = "";
        ErrorMapping errDt = null;

        try {
            rspXml = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_vpmncdr_query_505", params), accessId, "ac_vpmncdr_query_505",
                    this.generateCity(params)));
            logger.debug(" ====== 查询短号码清单返回报文 ======\n" + rspXml);

            if (null != rspXml && !"".equals(rspXml)) {
                Element root = this.config.getElement(rspXml);
                String resp_code = this.config.getChildText(this.config.getElement(root, "response"), "resp_code");
                String resp_desc = this.config.getChildText(this.config.getElement(root, "response"), "resp_desc");
                res.setResultCode("0000".equals(resp_code) ? "0" : "1");
                res.setErrorCode(resp_code);
                res.setErrorMessage(resp_desc);
                if (!"0000".equals(resp_code)) {
                    errDt = this.wellFormedDAO.transBossErrCode("QRY010001", "ac_vpmncdr_query_505", resp_code);
                    if (null != errDt) {
                        res.setErrorCode(errDt.getLiErrCode());
                        res.setErrorMessage(errDt.getLiErrMsg());
                    }
                }

                if (null != resp_code && ("0000".equals(resp_code))) {
                    res.setResultCode("0");
                    try {
                        List<Map<String, String>> respList = null;
                        String retContent = root.getChild("content").getChildText("xtcdr");
                        respList = TeletextParseUtils.parseXTABLE(retContent);
                        if (respList != null) {
                            for (Map<String, String> m : respList) {
                                VpmnBillDetail bd = new VpmnBillDetail();
                                bd.setStatusType(m.get("通信方式"));
                                bd.setOtherParty(m.get("对方号码"));
                                bd.setCdrStartDate(m.get("起始时间"));
                                bd.setCallDuration(m.get("通话时长"));
                                bd.setRealCfee(Double.parseDouble(m.get("基本话费") == null || "".equals(m.get("基本话费")) ? "0"
                                        : m.get("基本话费")));
                                bd.setRealLfee(Double.parseDouble(m.get("长途话费") == null || "".equals(m.get("长途话费")) ? "0"
                                        : m.get("长途话费")));
                                bd.setOtherFee(Double.parseDouble(m.get("应收其他话费") == null || "".equals(m.get("应收其他话费")) ? "0"
                                        : m.get("应收其他话费")));
                                bd.setTotalFee(Double.parseDouble(m.get("小计") == null || "".equals(m.get("小计")) ? "0"
                                        : m.get("小计")));
                                bd.setFeeItem01(Double.parseDouble(m.get("套餐费") == null || "".equals(m.get("套餐费")) ? "0"
                                        : m.get("套餐费")));
                                bd.setVisitArear(m.get("通信地点"));
                                // 套餐描述
                                String pckDscTmp = m.get("套餐信息");
                                String pckCode = "";
                                if (!"".equals(pckDscTmp)) {
                                    String[] codes = pckDscTmp.split(",");
                                    if (codes != null && codes.length > 0) {
                                        for (int i = 0; i < codes.length; i++) {
                                            String tmpCode = codes[i].split("\\|")[0];
                                            if (!"".equals(tmpCode)) {
                                                pckCode = pckCode + "," + tmpCode;
                                            }
                                        }
                                    }
                                }

                                // 集团短号
                                String shortNum = m.get("集团短号");
                                if (null != shortNum || !"".equals(shortNum)) {
                                    bd.setShortNum(shortNum);
                                }
                                if (!"".equals(pckCode)) {
                                    pckCode = pckCode.substring(1);
                                }
                                bd.setTpRemark(pckCode);
                                reList.add(bd);
                            }
                        }
                    } catch (Exception ex) {
                        reList.clear();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }
        return reList;
    }

    /**
     * 查询WLAN清单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<WlanBillDetail> getWlanCdrInfo(String accessId, ServiceConfig config, List<RequestParameter> params,
                                               QRY010001Result res) {
        List<WlanBillDetail> reList = new ArrayList<WlanBillDetail>();
        String rspXml = "";
        ErrorMapping errDt = null;

        try {
            rspXml = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_wlancdr_query_508", params), accessId, "ac_wlancdr_query_508",
                    this.generateCity(params)));
            logger.debug(" ====== 查询WLAN清单返回报文 ======\n" + rspXml);

            if (null != rspXml && !"".equals(rspXml)) {
                Element root = this.config.getElement(rspXml);
                String resp_code = this.config.getChildText(this.config.getElement(root, "response"), "resp_code");
                String resp_desc = this.config.getChildText(this.config.getElement(root, "response"), "resp_desc");
                res.setResultCode("0000".equals(resp_code) ? "0" : "1");
                res.setErrorCode(resp_code);
                res.setErrorMessage(resp_desc);
                if (!"0000".equals(resp_code)) {
                    errDt = this.wellFormedDAO.transBossErrCode("QRY010001", "ac_wlancdr_query_508", resp_code);
                    if (null != errDt) {
                        res.setErrorCode(errDt.getLiErrCode());
                        res.setErrorMessage(errDt.getLiErrMsg());
                    }
                }

                if (null != resp_code && ("0000".equals(resp_code))) {
                    res.setResultCode("0");
                    try {
                        List<Map<String, String>> respList = null;
                        String retContent = root.getChild("content").getChildText("xtcdr");
                        respList = TeletextParseUtils.parseXTABLE(retContent);
                        if (respList != null) {
                            for (Map<String, String> m : respList) {
                                WlanBillDetail bd = new WlanBillDetail();
                                bd.setStatusType(m.get("状态类型"));
                                bd.setStartTime(m.get("起始时间"));
                                bd.setCallDuration(m.get("总时长"));
                                bd.setDataUp(Long.parseLong(m.get("上行数据流量")));
                                bd.setDataDown(Long.parseLong(m.get("下行数据流量")));
                                bd.setIspCode(m.get("上网方式"));
                                bd.setVisitArear(m.get("通信地点"));
                                bd.setOrgSmFee(Double.parseDouble(m.get("基本费") == null || "".equals(m.get("基本费")) ? "0"
                                        : m.get("基本费")) / 100);
                                bd.setInfoFee(Double.parseDouble(m.get("信息费") == null || "".equals(m.get("信息费")) ? "0"
                                        : m.get("信息费")) / 100);
                                bd.setTotalFee(Double.parseDouble(m.get("小计") == null || "".equals(m.get("小计")) ? "0"
                                        : m.get("小计")) / 100);
                                bd.setAuthType(m.get("认证类型"));
                                bd.setRateTotalflow(m.get("计费总流量") == null || "".equals(m.get("计费总流量")) ? 0
                                        : Long.parseLong((String) m.get("计费总流量")));
                                reList.add(bd);
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        reList.clear();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }
        return reList;
    }

    /**
     * 查询96121清单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<Ac121BillDetail> getAc121CdrInfo(String accessId, ServiceConfig config, List<RequestParameter> params,
                                                 QRY010001Result res) {
        List<Ac121BillDetail> reList = new ArrayList<Ac121BillDetail>();
        String rspXml = "";
        ErrorMapping errDt = null;

        try {
            rspXml = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_121cdr_query_522", params), accessId, "ac_121cdr_query_522",
                    this.generateCity(params)));
            logger.debug(" ====== 查询96121清单返回报文 ======\n" + rspXml);

            if (null != rspXml && !"".equals(rspXml)) {
                Element root = this.config.getElement(rspXml);
                String resp_code = this.config.getChildText(this.config.getElement(root, "response"), "resp_code");
                String resp_desc = this.config.getChildText(this.config.getElement(root, "response"), "resp_desc");
                res.setResultCode("0000".equals(resp_code) ? "0" : "1");
                res.setErrorCode(resp_code);
                res.setErrorMessage(resp_desc);
                if (!"0000".equals(resp_code)) {
                    errDt = this.wellFormedDAO.transBossErrCode("QRY010001", "ac_121cdr_query_522", resp_code);
                    if (null != errDt) {
                        res.setErrorCode(errDt.getLiErrCode());
                        res.setErrorMessage(errDt.getLiErrMsg());
                    }
                }

                if (null != resp_code && ("0000".equals(resp_code))) {
                    res.setResultCode("0");
                    try {
                        List<Map<String, String>> respList = null;
                        String retContent = root.getChild("content").getChildText("xtcdr");
                        respList = TeletextParseUtils.parseXTABLE(retContent);
                        if (respList != null) {
                            for (Map<String, String> m : respList) {
                                Ac121BillDetail bd = new Ac121BillDetail();
                                bd.setBusiName(m.get("业务名称"));
                                bd.setUseType(m.get("使用方式"));
                                bd.setOtherParty(m.get("业务端口"));
                                bd.setStartTime(m.get("时间"));
                                bd.setCallDuration(m.get("时长(时分秒)"));
                                bd.setNetFee(Double.parseDouble(m.get("费用") == null || "".equals(m.get("费用")) ? "0"
                                        : m.get("费用")));
                                reList.add(bd);
                            }
                        }
                    } catch (Exception ex) {
                        reList.clear();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }
        return reList;
    }

    /**
     * 查询17202清单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<Isp2BillDetail> getIsp2CdrInfo(String accessId, ServiceConfig config, List<RequestParameter> params,
                                               QRY010001Result res) {
        List<Isp2BillDetail> reList = new ArrayList<Isp2BillDetail>();
        String rspXml = "";
        ErrorMapping errDt = null;

        try {
            rspXml = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_isp2cdr_query_509", params), accessId, "ac_isp2cdr_query_509",
                    this.generateCity(params)));
            logger.debug(" ====== 查询17202清单返回报文 ======\n" + rspXml);

            if (null != rspXml && !"".equals(rspXml)) {
                Element root = this.config.getElement(rspXml);
                String resp_code = this.config.getChildText(this.config.getElement(root, "response"), "resp_code");
                String resp_desc = this.config.getChildText(this.config.getElement(root, "response"), "resp_desc");
                res.setResultCode("0000".equals(resp_code) ? "0" : "1");
                res.setErrorCode(resp_code);
                res.setErrorMessage(resp_desc);
                if (!"0000".equals(resp_code)) {
                    errDt = this.wellFormedDAO.transBossErrCode("QRY010001", "ac_isp2cdr_query_509", resp_code);
                    if (null != errDt) {
                        res.setErrorCode(errDt.getLiErrCode());
                        res.setErrorMessage(errDt.getLiErrMsg());
                    }
                }

                if (null != resp_code && ("0000".equals(resp_code))) {
                    res.setResultCode("0");
                    try {
                        List<Map<String, String>> respList = null;
                        String retContent = root.getChild("content").getChildText("xtcdr");
                        respList = TeletextParseUtils.parseXTABLE(retContent);
                        if (respList != null) {
                            for (Map<String, String> m : respList) {
                                Isp2BillDetail bd = new Isp2BillDetail();
                                bd.setCallingStationId(m.get("主叫号码"));
                                bd.setVisitArear(m.get("通信地点"));
                                bd.setType(m.get("上网方式"));
                                bd.setPackageFee(Double.parseDouble(m.get("套餐费用") == null || "".equals(m.get("套餐费用")) ? "0"
                                        : m.get("套餐费用")));
                                bd.setCalleeNum(m.get("被叫号码"));
                                bd.setStartTime(m.get("起始时间"));
                                bd.setCallDuration(m.get("时长"));
                                bd.setTotalFee(Double.parseDouble(m.get("通信费") == null
                                        || " ".trim().equals(m.get("通信费")) ? "0" : m.get("通信费")));
                                reList.add(bd);
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        reList.clear();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }
        return reList;
    }

    /**
     * 查询USSD清单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<UssdBillDetail> getUssdCdrInfo(String accessId, ServiceConfig config, List<RequestParameter> params,
                                               QRY010001Result res) {
        List<UssdBillDetail> reList = new ArrayList<UssdBillDetail>();
        String rspXml = "";
        ErrorMapping errDt = null;

        try {
            rspXml = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_ussdcdr_query_510", params), accessId, "ac_ussdcdr_query_510",
                    this.generateCity(params)));
            logger.debug(" ====== 查询USSD清单返回报文 ======\n" + rspXml);

            if (null != rspXml && !"".equals(rspXml)) {
                Element root = this.config.getElement(rspXml);
                String resp_code = this.config.getChildText(this.config.getElement(root, "response"), "resp_code");
                String resp_desc = this.config.getChildText(this.config.getElement(root, "response"), "resp_desc");
                res.setResultCode("0000".equals(resp_code) ? "0" : "1");
                res.setErrorCode(resp_code);
                res.setErrorMessage(resp_desc);
                if (!"0000".equals(resp_code)) {
                    errDt = this.wellFormedDAO.transBossErrCode("QRY010001", "ac_ussdcdr_query_510", resp_code);
                    if (null != errDt) {
                        res.setErrorCode(errDt.getLiErrCode());
                        res.setErrorMessage(errDt.getLiErrMsg());
                    }
                }

                if (null != resp_code && ("0000".equals(resp_code))) {
                    res.setResultCode("0");
                    try {
                        List<Map<String, String>> respList = null;
                        String retContent = root.getChild("content").getChildText("XTABLE_USSD");
                        respList = TeletextParseUtils.parseXTABLE(retContent);
                        if (respList != null) {
                            for (Map<String, String> m : respList) {
                                UssdBillDetail bd = new UssdBillDetail();
                                bd.setCdrMsisdn(m.get("计费号码"));
                                bd.setUssdId(m.get("业务接入号"));
                                // USSD清单页面上的开始时间
                                bd.setStartTime(m.get("开始时间"));
                                bd.setCallDuration(m.get("通话时长"));
                                bd.setCallFee(Double.parseDouble(m.get("对话通信费") == null || "".equals(m.get("对话通信费")) ? "0"
                                        : m.get("对话通信费")));
                                reList.add(bd);
                            }
                        }
                    } catch (Exception ex) {
                        reList.clear();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }
        return reList;
    }

    /**
     * 查询无线音乐清单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<MpmusicBillDetail> getMpmusicCdrInfo(String accessId, ServiceConfig config,
                                                     List<RequestParameter> params, QRY010001Result res) {
        List<MpmusicBillDetail> reList = new ArrayList<MpmusicBillDetail>();
        String rspXml = "";
        ErrorMapping errDt = null;

        try {
            rspXml = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_mpmusiccdr_query_520", params), accessId,
                    "ac_mpmusiccdr_query_520", this.generateCity(params)));
            logger.debug(" ====== 查询无线音乐清单返回报文 ======\n" + rspXml);

            if (null != rspXml && !"".equals(rspXml)) {
                Element root = this.config.getElement(rspXml);
                String resp_code = this.config.getChildText(this.config.getElement(root, "response"), "resp_code");
                String resp_desc = this.config.getChildText(this.config.getElement(root, "response"), "resp_desc");
                res.setResultCode("0000".equals(resp_code) ? "0" : "1");
                res.setErrorCode(resp_code);
                res.setErrorMessage(resp_desc);
                if (!"0000".equals(resp_code)) {
                    errDt = this.wellFormedDAO.transBossErrCode("QRY010001", "ac_mpmusiccdr_query_520", resp_code);
                    if (null != errDt) {
                        res.setErrorCode(errDt.getLiErrCode());
                        res.setErrorMessage(errDt.getLiErrMsg());
                    }
                }

                if (null != resp_code && ("0000".equals(resp_code))) {
                    res.setResultCode("0");
                    try {
                        List<Map<String, String>> respList = null;
                        String retContent = root.getChild("content").getChildText("xtcdr");
                        respList = TeletextParseUtils.parseXTABLE(retContent);
                        if (respList != null) {
                            for (Map<String, String> m : respList) {
                                MpmusicBillDetail bd = new MpmusicBillDetail();
                                bd.setSourceTypeName(m.get("业务类型"));
                                bd.setSpCode(m.get("全网SP代码"));
                                bd.setContentId(m.get("业务名称"));
                                bd.setStartTime(m.get("时间"));
                                bd.setRealInfoFee(Double.parseDouble(m.get("费用") == null || "".equals(m.get("费用")) ? "0"
                                        : m.get("费用")));
                                bd.setFreetimeItem(m.get("赠送项目"));
                                bd.setFreetime(Long.parseLong(m.get("赠送数")));
                                bd.setChargeTypeName(m.get("计费类型(名称）"));
                                reList.add(bd);
                            }
                        }
                    } catch (Exception ex) {
                        reList.clear();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }
        return reList;
    }

    /**
     * 查询LBS清单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<LbsBillDetail> getLbsCdrInfo(String accessId, ServiceConfig config, List<RequestParameter> params,
                                             QRY010001Result res) {
        List<LbsBillDetail> reList = new ArrayList<LbsBillDetail>();
        String rspXml = "";
        ErrorMapping errDt = null;

        try {
            rspXml = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_lbscdr_query_521", params), accessId, "ac_lbscdr_query_521",
                    this.generateCity(params)));
            logger.debug(" ====== 查询LBS清单返回报文 ======\n" + rspXml);

            if (null != rspXml && !"".equals(rspXml)) {
                Element root = this.config.getElement(rspXml);
                String resp_code = this.config.getChildText(this.config.getElement(root, "response"), "resp_code");
                String resp_desc = this.config.getChildText(this.config.getElement(root, "response"), "resp_desc");
                res.setResultCode("0000".equals(resp_code) ? "0" : "1");
                res.setErrorCode(resp_code);
                res.setErrorMessage(resp_desc);
                if (!"0000".equals(resp_code)) {
                    errDt = this.wellFormedDAO.transBossErrCode("QRY010001", "ac_lbscdr_query_521", resp_code);
                    if (null != errDt) {
                        res.setErrorCode(errDt.getLiErrCode());
                        res.setErrorMessage(errDt.getLiErrMsg());
                    }
                }

                if (null != resp_code && ("0000".equals(resp_code))) {
                    res.setResultCode("0");
                    try {
                        List<Map<String, String>> respList = null;
                        String retContent = root.getChild("content").getChildText("xtcdr");
                        respList = TeletextParseUtils.parseXTABLE(retContent);
                        if (respList != null) {
                            for (Map<String, String> m : respList) {
                                LbsBillDetail bd = new LbsBillDetail();
                                bd.setTypeName(m.get("类型"));
                                bd.setMsisdn(m.get("发起定位方号码"));
                                bd.setOtherParty(m.get("被定位方号码"));
                                bd.setRealInfoFee(Double.parseDouble("".equals((String) m.get("费用")) ? "0"
                                        : (String) m.get("费用")));
                                bd.setStartTime(m.get("时间"));
                                bd.setXInfo(m.get("定位结果（X信息）"));
                                bd.setYInfo(m.get("定位结果（Y信息）"));
                                bd.setBusiName(m.get("业务名称"));
                                bd.setUseType(m.get("使用方式"));
                                reList.add(bd);
                            }
                        }
                    } catch (Exception ex) {
                        reList.clear();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }
        return reList;
    }

    /**
     * 即时群聊清单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<MeetBillDetail> getMeetCdrInfo(String accessId, ServiceConfig config, List<RequestParameter> params,
                                               QRY010001Result res) {
        List<MeetBillDetail> reList = new ArrayList<MeetBillDetail>();
        String rspXml = "";
        ErrorMapping errDt = null;

        try {
            rspXml = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_meet_query_523", params), accessId, "ac_meet_query_523",
                    this.generateCity(params)));
            logger.debug(" ====== 即时群聊清单返回报文 ======\n" + rspXml);

            if (null != rspXml && !"".equals(rspXml)) {
                Element root = this.config.getElement(rspXml);
                String resp_code = this.config.getChildText(this.config.getElement(root, "response"), "resp_code");
                String resp_desc = this.config.getChildText(this.config.getElement(root, "response"), "resp_desc");
                res.setResultCode("0000".equals(resp_code) ? "0" : "1");
                res.setErrorCode(resp_code);
                res.setErrorMessage(resp_desc);
                if (!"0000".equals(resp_code)) {
                    errDt = this.wellFormedDAO.transBossErrCode("QRY010001", "ac_meet_query_523", resp_code);
                    if (null != errDt) {
                        res.setErrorCode(errDt.getLiErrCode());
                        res.setErrorMessage(errDt.getLiErrMsg());
                    }
                }

                if (null != resp_code && ("0000".equals(resp_code))) {
                    res.setResultCode("0");
                    try {
                        List<Map<String, String>> respList = null;
                        String retContent = root.getChild("content").getChildText("xtcdr");
                        respList = TeletextParseUtils.parseXTABLE(retContent);
                        if (respList != null) {
                            for (Map<String, String> m : respList) {
                                MeetBillDetail bd = new MeetBillDetail();
                                bd.setToStationId(m.get("对方号码"));
                                bd.setBusiName(m.get("业务名称"));
                                bd.setStartTime(m.get("时间"));
                                bd.setCallDuration(m.get("时长(时分秒)"));
                                bd.setNetFee(Double.parseDouble(m.get("实收长途费") == null || "".equals(m.get("实收长途费")) ? "0"
                                        : m.get("实收长途费")));
                                bd.setOtherFee(Double.parseDouble(m.get("实收其它费") == null || "".equals(m.get("实收其它费")) ? "0"
                                        : m.get("实收其它费")));
                                bd.setBaseFee(Double.parseDouble(m.get("实收基本费") == null || "".equals(m.get("实收基本费")) ? "0"
                                        : m.get("实收基本费")));
                                bd.setTotalFee(Double.parseDouble(m.get("费用") == null || "".equals(m.get("费用")) ? "0"
                                        : m.get("费用")));
                                reList.add(bd);
                            }
                        }
                    } catch (Exception ex) {
                        reList.clear();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }
        return reList;
    }

    /**
     * 视频通话清单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<GsmVideoBillDetail> getGsmVideoCdrInfo(String accessId, ServiceConfig config,
                                                       List<RequestParameter> params, QRY010001Result res) {
        List<GsmVideoBillDetail> reList = new ArrayList<GsmVideoBillDetail>();
        String rspXml = "";
        ErrorMapping errDt = null;

        try {
            rspXml = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_gsmcdr_video_query_539", params), accessId,
                    "ac_gsmcdr_video_query_539", this.generateCity(params)));
            logger.debug(" ====== 查询视频通话清单返回报文 ======\n" + rspXml);

            if (null != rspXml && !"".equals(rspXml)) {
                Element root = this.config.getElement(rspXml);
                String resp_code = this.config.getChildText(this.config.getElement(root, "response"), "resp_code");
                String resp_desc = this.config.getChildText(this.config.getElement(root, "response"), "resp_desc");
                res.setResultCode("0000".equals(resp_code) ? "0" : "1");
                res.setErrorCode(resp_code);
                res.setErrorMessage(resp_desc);
                if (!"0000".equals(resp_code)) {
                    errDt = this.wellFormedDAO.transBossErrCode("QRY010001", "ac_gsmcdr_video_query_539", resp_code);
                    if (null != errDt) {
                        res.setErrorCode(errDt.getLiErrCode());
                        res.setErrorMessage(errDt.getLiErrMsg());
                    }
                }

                if (null != resp_code && ("0000".equals(resp_code))) {
                    res.setResultCode("0");
                    try {
                        List<Map<String, String>> respList = null;
                        String retContent = root.getChild("content").getChildText("xtcdr");
                        respList = TeletextParseUtils.parseXTABLE(retContent);
                        if (respList != null) {
                            for (Map<String, String> m : respList) {
                                GsmVideoBillDetail bd = new GsmVideoBillDetail();
                                bd.setVisitArear(m.get("通信地点"));
                                bd.setStatusType(m.get("通信类型"));
                                bd.setOtherParty(m.get("对方号码"));
                                bd.setStartTime(m.get("开始时间"));
                                // 处理时长，BOSS传过来的值的类型是long型。（格式：hh:mm:ss）
                                bd.setCallDuration(m.get("通信时长"));
                                bd.setRealCfee(Double.parseDouble(m.get("基本费") == null || "".equals(m.get("基本费")) ? "0"
                                        : m.get("基本费")));//
                                bd.setRealLfee(Double.parseDouble(m.get("长途费") == null || "".equals(m.get("长途费")) ? "0"
                                        : m.get("长途费")));
                                bd.setTotalFee(Double.parseDouble(m.get("实收通信费") == null || "".equals(m.get("实收通信费")) ? "0"
                                        : m.get("实收通信费")));
                                bd.setFeeItem01(Double.parseDouble(m.get("套餐费") == null || "".equals(m.get("套餐费")) ? "0"
                                        : m.get("套餐费")));
                                bd.setServiceCode(m.get("网络"));
                                // 套餐描述
                                String pckDscTmp = m.get("套餐信息");
                                String pckCode = "";
                                if (pckDscTmp != null && !"".equals(pckDscTmp)) {
                                    String[] codes = pckDscTmp.split(",");
                                    if (codes != null && codes.length > 0) {
                                        for (int i = 0; i < codes.length; i++) {
                                            String tmpCode = codes[i].split("\\|")[0];
                                            if (!"".equals(tmpCode)) {
                                                pckCode = pckCode + "," + tmpCode;
                                            }
                                        }
                                    }
                                }
                                if (!"".equals(pckCode)) {
                                    pckCode = pckCode.substring(1);
                                }
                                bd.setTpRemark(pckCode);
                                reList.add(bd);
                            }
                        }
                    } catch (Exception ex) {
                        reList.clear();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }
        return reList;
    }

    /**
     * 随E行上网本清单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<CmnetBillDetail> getCmnetCdrInfo(String accessId, ServiceConfig config, List<RequestParameter> params,
                                                 QRY010001Result res) {
        List<CmnetBillDetail> reList = new ArrayList<CmnetBillDetail>();
        String rspXml = "";
        ErrorMapping errDt = null;

        try {
            rspXml = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_cmnetcdr_query_540", params), accessId,
                    "ac_cmnetcdr_query_540", this.generateCity(params)));
            logger.debug(" ====== 随E行上网本清单返回报文 ======\n" + rspXml);

            if (null != rspXml && !"".equals(rspXml)) {
                Element root = this.config.getElement(rspXml);
                String resp_code = this.config.getChildText(this.config.getElement(root, "response"), "resp_code");
                String resp_desc = this.config.getChildText(this.config.getElement(root, "response"), "resp_desc");
                res.setResultCode("0000".equals(resp_code) ? "0" : "1");
                res.setErrorCode(resp_code);
                res.setErrorMessage(resp_desc);
                if (!"0000".equals(resp_code)) {
                    errDt = this.wellFormedDAO.transBossErrCode("QRY010001", "ac_cmnetcdr_query_540", resp_code);
                    if (null != errDt) {
                        res.setErrorCode(errDt.getLiErrCode());
                        res.setErrorMessage(errDt.getLiErrMsg());
                    }
                }

                if (null != resp_code && ("0000".equals(resp_code))) {
                    res.setResultCode("0");
                    try {
                        List<Map<String, String>> respList = null;
                        List<?> children = root.getChild("content").getChildren();
                        for (int j = 0; j < children.size(); j++) {
                            Element e = (Element) children.get(j);
                            if (e.getName().startsWith("xtcdr")) {
                                String retContent = e.getText();
                                respList = TeletextParseUtils.parseXTABLE(retContent);
                                if (respList != null) {
                                    for (Map<String, String> m : respList) {
                                        CmnetBillDetail bd = new CmnetBillDetail();
                                        bd.setCdrApnni(m.get("上网方式"));
                                        bd.setVisitArear(m.get("通话地点"));
                                        bd.setDataUpAndDown(Double.parseDouble(m.get("总流量")));
                                        bd.setOtherFee(Double.parseDouble(m.get("其他费用") == null
                                                || "".equals(m.get("其他费用")) ? "0" : m.get("其他费用")));
                                        bd.setPkgFee(Double.parseDouble(m.get("套餐费") == null || "".equals(m.get("套餐费")) ? "0"
                                                : m.get("套餐费")));
                                        bd.setStartTime(m.get("起始时间"));
                                        bd.setStatusType(m.get("状态类型"));
                                        bd.setFullTime(m.get("总时长"));
                                        bd.setAllTotalFee(Double.parseDouble(m.get("基本费用") == null
                                                || "".equals(m.get("基本费用")) ? "0" : m.get("基本费用")));
                                        bd.setCallDuration(Double.parseDouble(m.get("基本收费流量") == null
                                                || "".equals(m.get("基本收费流量")) ? "0" : m.get("基本收费流量")));
                                        bd.setTotalFee(Double.parseDouble(m.get("总费用") == null
                                                || "".equals(m.get("总费用")) ? "0" : m.get("总费用")));
                                        bd.setRealLfee(Double.parseDouble(m.get("其他收费流量") == null
                                                || "".equals(m.get("其他收费流量")) ? "0" : m.get("其他收费流量")));
                                        // 套餐描述
                                        String pckDscTmp = m.get("套餐编码");
                                        String pckCode = "";
                                        if (!"".equals(pckDscTmp)) {
                                            String[] codes = pckDscTmp.split(",");
                                            if (codes != null && codes.length > 0) {
                                                for (int i = 0; i < codes.length; i++) {
                                                    String tmpCode = codes[i].split("\\|")[0];
                                                    if (!"".equals(tmpCode)) {
                                                        pckCode = pckCode + "," + tmpCode;
                                                    }
                                                }
                                            }
                                        }
                                        if (!"".equals(pckCode)) {
                                            pckCode = pckCode.substring(1);
                                        }

                                        bd.setTpremark(pckCode);
                                        reList.add(bd);
                                    }
                                }
                                break;
                            }
                        }
                    } catch (Exception ex) {
                        reList.clear();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }
        return reList;
    }

    /**
     * 查询语音杂志
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<GsmMagaBillDetail> getGsmCdrMagazineInfo(String accessId, ServiceConfig config,
                                                         List<RequestParameter> params, QRY010001Result res) {
        List<GsmMagaBillDetail> reList = new ArrayList<GsmMagaBillDetail>();
        String rspXml = "";
        ErrorMapping errDt = null;

        try {
            RemoteCallContext city = this.generateCity(params);
            rspXml = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_gsmcdr_query_520", params), accessId, "ac_gsmcdr_query_520",
                    city));
            logger.debug(" ====== 查询语音话单返回报文 ======\n" + rspXml);

            if (null != rspXml && !"".equals(rspXml)) {
                Element root = this.config.getElement(rspXml);
                String resp_code = this.config.getChildText(this.config.getElement(root, "response"), "resp_code");
                String resp_desc = this.config.getChildText(this.config.getElement(root, "response"), "resp_desc");
                res.setResultCode("0000".equals(resp_code) ? "0" : "1");
                res.setErrorCode(resp_code);
                res.setErrorMessage(resp_desc);

                if (!"0000".equals(resp_code)) {
                    errDt = this.wellFormedDAO.transBossErrCode("QRY010001", "ac_gsmcdr_query_501", resp_code);
                    if (null != errDt) {
                        res.setErrorCode(errDt.getLiErrCode());
                        res.setErrorMessage(errDt.getLiErrMsg());
                    }
                }

                if (null != resp_code && ("0000".equals(resp_code))) {
                    res.setResultCode("0");
                    try {
                        List<Map<String, String>> respList = null;
                        String retContent = root.getChild("content").getChildText("xtcdr");
                        respList = TeletextParseUtils.parseXTABLE(retContent);
                        if (respList != null) {
                            for (Map<String, String> m : respList) {
                                GsmMagaBillDetail bd = new GsmMagaBillDetail();
                                bd.setStatusType(m.get("状态类型"));
                                bd.setOtherParty(m.get("对方号码"));
                                bd.setStartTime(m.get("通话时间"));
                                bd.setCallDuration(m.get("通话时长"));
                                bd.setFirstCfee(Double.parseDouble((m.get("应收基本通话费") == null || m.get("应收基本通话费")
                                        .equals("")) ? "0" : m.get("应收基本通话费")));
                                bd.setRealLfeeAndFirstOfee(Double.parseDouble((m.get("应收长途话费") == null || m.get("应收长途话费")
                                        .equals("")) ? "0" : m.get("应收长途话费"))
                                        + Double.parseDouble((m.get("应收其他话费") == null || m.get("应收其他话费").equals("")) ? "0"
                                        : m.get("应收其他话费")));
                                bd.setRealCfee(Double.parseDouble((m.get("基本话费") == null || m.get("基本话费").equals("")) ? "0"
                                        : m.get("基本话费")));
                                bd.setRealLfee(Double.parseDouble((m.get("长途话费") == null || m.get("长途话费").equals("")) ? "0"
                                        : m.get("长途话费")));
                                bd.setTotalFee(Double.parseDouble((m.get("小计") == null || m.get("小计").equals("")) ? "0"
                                        : m.get("小计")));
                                bd.setFeeItem01(Double.parseDouble((m.get("套餐费") == null || m.get("套餐费").equals("")) ? "0"
                                        : m.get("套餐费")));
                                bd.setVisitArear(m.get("通话地点"));
                                bd.setCallType(m.get("通信方式"));
                                bd.setRoamType(m.get("通信类型"));
                                bd.setFreeCode(m.get("产品编码"));
                                // 套餐描述
                                String pckDscTmp = m.get("套餐");
                                if (" ".trim().equals(pckDscTmp)) {
                                    bd.setPkgCode("无");
                                } else {
                                    bd.setPkgCode(pckDscTmp);
                                }
                                reList.add(bd);
                            }
                        }
                    } catch (Exception ex) {
                        reList.clear();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }

        return reList;
    }

    /**
     * 查询游戏详单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<CdrsBillDetail> getGsmCDRQueryInfo(String accessId, ServiceConfig config,
                                                   List<RequestParameter> params, QRY010001Result res) {
        List<CdrsBillDetail> reList = new ArrayList<CdrsBillDetail>();
        String rspXml = "";

        try {
            RemoteCallContext city = this.generateCity(params);
            rspXml = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_cdr_query_542", params), accessId, "ac_cdr_query_541", city));
            logger.debug(" ====== 查询游戏详单返回报文 ======\n" + rspXml);

            if (null != rspXml && !"".equals(rspXml)) {
                Message bossMessage = MessageUtil.parse(rspXml);
                if (null != bossMessage) {
                    if (MessageUtil.BOSS_CODE.equals(bossMessage.getHead().getCode())) {
                        res.setResultCode(MessageUtil.LOGIC_SUCESS);
                        res.setBossCode(bossMessage.getHead().getCode());
                        res.setErrorMessage(bossMessage.getHead().getDesc());

                        List<String[]> gprsbillList = bossMessage.getBody().asList();

                        //取出第一条数据中的totalinfo
                        String totalInfo = "";
                        String[] tmp = gprsbillList.get(0);
                        if (null != tmp && tmp.length > 0) {
                            totalInfo = tmp[3];
                        }

                        CdrsBillDetail cdrsBillDetail = null;
                        for (String[] values : gprsbillList) {
                            cdrsBillDetail = new CdrsBillDetail();
                            cdrsBillDetail.setStart_time(values[0]);
                            cdrsBillDetail.setBill_flag(values[1]);
                            cdrsBillDetail.setCharge(values[2]);
                            cdrsBillDetail.setTotal_info(totalInfo);

                            reList.add(cdrsBillDetail);
                        }
                    } else {
                        res.setResultCode(MessageUtil.LOGIC_ERROR);
                        res.setBossCode(bossMessage.getHead().getCode());
                        res.setErrorMessage(bossMessage.getHead().getDesc());
                    }
                } else {
                    res.setResultCode(MessageUtil.LOGIC_ERROR);
                    res.setErrorMessage("请求CRM接口失败.");
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }

        return reList;
    }

    /**
     * 查询游戏点数详单
     *
     * @param accessId
     * @param config
     * @param params
     * @param res
     */
    public List<CdrsPointBillDetail> getGsmCDRQueryPointInfo(String accessId, ServiceConfig config,
                                                             List<RequestParameter> params, QRY010001Result res) {
        List<CdrsPointBillDetail> reList = new ArrayList<CdrsPointBillDetail>();
        String rspXml = "";
        ErrorMapping errDt = null;

        try {
            RemoteCallContext city = this.generateCity(params);
            rspXml = (String) this.remote.callRemote(new StringTeletext(
                    this.bossTeletextUtil.mergeTeletext("ac_cdr_query_541", params), accessId, "ac_cdr_query_541", city));
            logger.debug(" ====== 查询游戏详单返回报文 ======\n" + rspXml);

            if (null != rspXml && !"".equals(rspXml)) {
                Element root = this.config.getElement(rspXml);
                String resp_code = this.config.getChildText(this.config.getElement(root, "response"), "resp_code");
                String resp_desc = this.config.getChildText(this.config.getElement(root, "response"), "resp_desc");
                res.setResultCode("0000".equals(resp_code) ? "0" : "1");
                res.setErrorCode(resp_code);
                res.setErrorMessage(resp_desc);

                if (!"0000".equals(resp_code)) {
                    errDt = this.wellFormedDAO.transBossErrCode("QRY010001", "ac_cdr_query_541", resp_code);
                    if (null != errDt) {
                        res.setErrorCode(errDt.getLiErrCode());
                        res.setErrorMessage(errDt.getLiErrMsg());
                    }
                }

                if (null != resp_code && ("0000".equals(resp_code))) {
                    res.setResultCode("0");
                    try {
                        List<Map<String, String>> respList = null;
                        String retContent = root.getChild("content").getChildText("xtcdr");
                        respList = TeletextParseUtils.parseXTABLE(retContent);
                        if (respList != null) {
                            for (Map<String, String> m : respList) {
                                CdrsPointBillDetail cqb = new CdrsPointBillDetail();
                                cqb.setBusinessName(m.get("业务名称"));
                                cqb.setServiceCode(m.get("业务代码"));
                                cqb.setSpendingMoney(String.valueOf((m.get("消费金额") == null || m.get("消费金额").equals("")) ? "0"
                                        : m.get("消费金额")));
                                cqb.setSpendingPoint(String.valueOf((m.get("消费点数(100点=1元)") == null || m.get("消费点数(100点=1元)")
                                        .equals("")) ? "0" : m.get("消费点数(100点=1元)")));
                                cqb.setSpendingTime(String.valueOf(m.get("消费时间")));
                                reList.add(cqb);
                            }
                        }
                    } catch (Exception ex) {
                        reList.clear();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            reList = null;
        }

        return reList;
    }
}