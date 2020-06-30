package com.xwtech.xwecp.service.logic.invocation;

import com.xwtech.xwecp.communication.CommunicateException;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY040010Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.util.ECPConstants;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.MessageUtil.Message;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class QueryUserInfoInvocation extends BaseInvocation implements ILogicalService
{
    private static final Logger logger = LoggerFactory.getLogger(QueryUserInfoInvocation.class);
    
    private static final String REQUEST_BOSSTELETEXT_QRYUSER = "cc_cgetuserinfo_771";
    
    private static final String REQUEST_BOSSTELETEXT_CHKPWD = "cc_chkpass_61";
    
    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
        List<RequestParameter> params)
    {
        QRY040010Result result = new QRY040010Result();
        result.setResultCode(LOGIC_SUCESS);
        result.setErrorMessage("");
//      result.setAreacode("A");
//      result.setUserinfo_svcnum("18803695181");
//      result.setUserinfo_crtdate("20150528");
//      result.setUserinfo_devnum("qq");
//      result.setProdprcname("qq");
//      result.setUserinfo_mainprodprcid("www");
//      result.setBrandname("ff");
//      result.setUserinfo_brandid("ww");
//      result.setAcctinfo_acctname("qq");
//      result.setStoptype_name("eee");
//      result.setUser_name("www");
//      result.setRegion_id("32423523");
        try
        {
            String reqXml = bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT_CHKPWD, params);
            String rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId,
                REQUEST_BOSSTELETEXT_CHKPWD, this.generateCity(params)));
            
            if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120)
            {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            
            Message bossMessage = MessageUtil.parse(rspXml);
            
            result.setResultCode(BOSS_SUCCESS.equals(bossMessage.getHead().getCode()) ? LOGIC_SUCESS : LOGIC_ERROR);
            result.setBossCode(bossMessage.getHead().getCode());
            result.setErrorMessage(bossMessage.getHead().getDesc());
            if (BOSS_SUCCESS.equals(bossMessage.getHead().getCode()))
            {
                reqXml = bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT_QRYUSER, params);
                rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId,
                    REQUEST_BOSSTELETEXT_CHKPWD, this.generateCity(params)));
                logger.info("******** Boss返回数据为*****　" + rspXml);
                
                if (StringUtils.isEmpty(rspXml))
                {
                    result.setBossCode(LOGIC_ERROR);
                    result.setErrorCode(LOGIC_ERROR);
                    return result;
                }
                
                bossMessage = MessageUtil.parse(rspXml);
                
                result.setResultCode(BOSS_SUCCESS.equals(bossMessage.getHead().getCode()) ? LOGIC_SUCESS : LOGIC_ERROR);
                result.setBossCode(bossMessage.getHead().getCode());
                result.setErrorMessage(bossMessage.getHead().getDesc());
                if (BOSS_SUCCESS.equals(bossMessage.getHead().getCode()))
                {
                    String[] values = bossMessage.getBody().asArray();
                    result.setAreacode(ECPConstants.AREA_CODES.get(values[0]));
                    result.setUserinfo_svcnum(values[1]);
                    result.setUserinfo_crtdate(values[2]);
                    result.setUserinfo_devnum(values[3]);
                    result.setProdprcname(values[4]);
                    result.setUserinfo_mainprodprcid(values[5]);
                    result.setBrandname(values[6]);
                    result.setUserinfo_brandid(values[7]);
                    result.setAcctinfo_acctname(values[8]);
                    result.setStoptype_name(values[9]);
                    result.setUser_name(values[10]);
                    result.setRegion_id(values[11]);
                }
            }
        }
        catch (CommunicateException e)
        {
            logger.error("用户登录失败.", e);
        }
        catch (Exception e)
        {
            logger.error("用户登录失败.", e);
        }
        
        return result;
    }
}
