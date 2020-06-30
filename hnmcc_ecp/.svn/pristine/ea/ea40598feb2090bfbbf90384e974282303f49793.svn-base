package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.Jedis.RedisService;
import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.CommunicateException;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.GroupDetectionDAO;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY170611Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.MessageJsonUtil;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.MessageUtil.Message;
import com.xwtech.xwecp.util.ParseXmlConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;

public class QueryVoiceRealAmoutInvocation extends BaseInvocation implements
        ILogicalService {
    private static final Logger logger = LoggerFactory.getLogger(QueryValidProductIdBussInvocation.class);

    private BossTeletextUtil bossTeletextUtil;

    private IRemote remote;

    private WellFormedDAO wellFormedDAO;
	private RedisService redisService;

    private ParseXmlConfig config;
    protected GroupDetectionDAO groupDetectionDAO;

    public QueryVoiceRealAmoutInvocation() {
        ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
        this.bossTeletextUtil = (BossTeletextUtil) (springCtx.getBean("bossTeletextUtil"));
        this.remote = (IRemote) (springCtx.getBean("bossRemote"));
        this.wellFormedDAO = (WellFormedDAO) (springCtx.getBean("wellFormedDAO"));
        this.config = new ParseXmlConfig();
        this.groupDetectionDAO = (GroupDetectionDAO) springCtx.getBean("groupDetectionDAO");
        this.redisService= (RedisService) springCtx.getBean("redisService");
    }

    private static final String BOSSTELETEXT1 = "cc_queryVoiceRealAmout";
    private static final String BOSSTELETEXT2 = "cc_queryVoiceRealAmout2";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
                                                      List<RequestParameter> params) {

        QRY170611Result result = new QRY170611Result();
        String mobile = "";
        String teleText = "";
        for (RequestParameter p : params) {
            if ("BillId".equals(p.getParameterName())) {
                mobile = (String) p.getParameterValue();
            }
        }

        boolean isGroup = this.groupDetectionDAO.getAllTelNum(mobile);
        if (isGroup) {
            teleText = this.BOSSTELETEXT2;
        } else {
            teleText = this.BOSSTELETEXT1;
        }
        String reqXml = "";
        String rspXml = "";
        try {
            Map<String, Object> remotingMap = this.bossTeletextUtil.mergeRemoteTeletext(teleText, params);
            String sysParam = "";
            String busiParam = "";
            String type = StringTeletext.DEFAULT_REQ_TYPE;
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
                    }else if ("header_pagenum".equals(paramName)) {
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
            //rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT_2, this.generateCity(params)));



            
            //{"respCode":"00000","respDesc":"调用成功!","result":{"SO_MEMBER_DEAL":[{"BALANCE":"1204","SPEBALANCE":"12624","SUMBALANCE":"13828"}]}}
            if (isGroup) {//170806走CRM
            	  reqXml = bossTeletextUtil.mergeTeletext(teleText, params);
            	String key = "CRM_RES_170806_"+mobile;
            	String keyStr = redisService.get(key);
            	if(StringUtils.isBlank(keyStr)){
            		  rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId,
                              teleText, this.generateCity(params)));
            	}else{
            		logger.info("从缓存中取数据");
            		rspXml = keyStr;
            	}
            	
              

                if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120) {
                    result.setBossCode(LOGIC_ERROR);
                    result.setErrorCode(LOGIC_ERROR);
                    return result;
                }
                Message bossMsg = MessageUtil.parse(rspXml);
                result.setResultCode(BOSS_SUCCESS.equals(bossMsg.getHead().getCode()) ? LOGIC_SUCESS : LOGIC_ERROR);
                result.setBossCode(bossMsg.getHead().getCode());
                result.setErrorMessage(bossMsg.getHead().getDesc());

                String resStr = bossMsg.getBody().toString();
                if (rspXml.getBytes().length > 70) {
                    String resCode = new String(rspXml.getBytes(), 66, 4);
                    if ("0000".endsWith(resCode)) {
                        List<String[]> list = bossMsg.getBody().asList();
                        String str[] = list.get(0);
                        result.setBalance(str[0]);
                        result.setSpeBalance(str[1]);
                        result.setSumBalance(str[2]);
                    }
                }
            } else {//170611走能力平台
            	
                rspXml = (String)this.remote.callRemote(new StringTeletext(sysParam,busiParam,type,reqXml, accessId,
                        teleText, this.generateCity(params),headTraceId,headUserMobile,headUserBrand,headUserCity,
                        headPageNum,headRecNum,headSerialNum,headJfserialNum,headProdId));
                logger.info("******** Boss返回数据为*****　" + rspXml);

                if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120) {
                    result.setBossCode(LOGIC_ERROR);
                    result.setErrorCode(LOGIC_ERROR);
                    return result;
                }

                MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) rspXml);
                String bossCode = bossJson.getBossCode();
                String bossDesc = bossJson.getBossDesc();
                if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
                    result.setBossCode(LOGIC_ERROR);
                    result.setErrorCode(LOGIC_ERROR);
                    return result;
                }

                result.setResultCode(MessageJsonUtil.LOGIC_SUCESS);
                result.setBossCode(bossCode);
                result.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());

                JSONObject obj = com.alibaba.fastjson.JSONObject.parseObject(rspXml).getJSONObject("result");
                JSONArray array = obj.getJSONArray("SO_MEMBER_DEAL");
                JSONObject obj2 = array.getJSONObject(0);
                result.setBalance(obj2.getString("BALANCE"));
                result.setSpeBalance(obj2.getString("SPEBALANCE"));
                result.setSumBalance(obj2.getString("SUMBALANCE"));
            }

        } catch (CommunicateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
