package com.xwtech.xwecp.service.logic.invocation;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.GPRSFluxsMonth;
import com.xwtech.xwecp.service.logic.pojo.QRY940130Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.MessageJsonUtil;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.ParseXmlConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 54344 on 2018/1/24.
 */
public class BillSendMsgInvocation   extends BaseInvocation implements ILogicalService {

    private String REQUEST_BOSSTELETEXT = "cc_detailbill_sendmsg_940130";

    private static final Logger logger = LoggerFactory.getLogger(BillQueryInvocation.class);

    private BossTeletextUtil bossTeletextUtil;

    private IRemote remote;

    private WellFormedDAO wellFormedDAO;

    private ParseXmlConfig config;

    public BillSendMsgInvocation()
    {
        ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
        this.bossTeletextUtil = (BossTeletextUtil)(springCtx.getBean("bossTeletextUtil"));
        this.remote = (IRemote)(springCtx.getBean("bossRemote"));
        this.wellFormedDAO = (WellFormedDAO)(springCtx.getBean("wellFormedDAO"));
        this.config = new ParseXmlConfig();
    }


    @Override
    public BaseServiceInvocationResult executeService(String accessId,
                                                      ServiceConfig config, List<RequestParameter> params) {
        QRY940130Result result = new QRY940130Result();

        String reqXml = "";
        String rspXml = "";
        try
        {
            reqXml = this.bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT, params);

            Map<String, Object> remotingMap = this.bossTeletextUtil.mergeRemoteTeletext(REQUEST_BOSSTELETEXT, params);
            String sysParam = "";
            String busiParam = "";
            String type = DefaultServiceImpl.StringTeletext.DEFAULT_REQ_TYPE;
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
            //rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT, this.generateCity(params)));

            rspXml = (String)this.remote.callRemote(new DefaultServiceImpl.StringTeletext(sysParam,busiParam,type,reqXml, accessId,
                    REQUEST_BOSSTELETEXT, this.generateCity(params),headTraceId,headUserMobile,headUserBrand,headUserCity,
                    headPageNum,headRecNum,headSerialNum,headJfserialNum,headProdId));

            List<GPRSFluxsMonth> monthFluxsList = new ArrayList<GPRSFluxsMonth>();
            if(DefaultServiceImpl.StringTeletext.DEFAULT_REQ_TYPE.equals(type)){
                if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120)
                {
                    result.setBossCode(LOGIC_ERROR);
                    result.setErrorCode(LOGIC_ERROR);
                    return result;
                }
                MessageUtil.Message bossMsg = MessageUtil.parse(rspXml);
                result.setResultCode(BOSS_SUCCESS.equals(bossMsg.getHead().getCode()) ? LOGIC_SUCESS : LOGIC_ERROR);
                result.setBossCode(bossMsg.getHead().getCode());
                result.setErrorMessage(bossMsg.getHead().getDesc());

                List<String[]> rowList = bossMsg.getBody().asList();
                String s[] = rowList.get(0);

                result.setRandomFlag(s[0]);
            }else if(DefaultServiceImpl.StringTeletext.REMOTING_REQ_TYPE.equals(type)){
                logger.info("******** Boss返回数据为*****　" + rspXml);
                if (StringUtils.isEmpty(rspXml))
                {
                    result.setBossCode(LOGIC_ERROR);
                    result.setErrorCode(LOGIC_ERROR);
                    return result;
                }

                MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) rspXml);
                String bossCode = bossJson.getBossCode();
                String bossDesc = bossJson.getBossDesc();
                if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)){
                    result.setBossCode(LOGIC_ERROR);
                    result.setErrorCode(LOGIC_ERROR);
                    return result;
                }

                String str = bossJson.getStringResult();
                com.alibaba.fastjson.JSONArray array = com.alibaba.fastjson.JSONArray.parseArray(str);
                com.alibaba.fastjson.JSONObject jsonObj = array.getJSONObject(0);

                result.setRandomFlag(jsonObj.getString("RANDOM_FLAG"));
                result.setResultCode(MessageJsonUtil.LOGIC_SUCESS);
                result.setBossCode(bossCode);
                result.setErrorMessage(StringUtils.isNotBlank(bossDesc)?bossDesc:bossJson.getStringResult());


            }
        }
        catch (Exception e)
        {
            logger.error("", e);
        }
        return result;
    }
}
