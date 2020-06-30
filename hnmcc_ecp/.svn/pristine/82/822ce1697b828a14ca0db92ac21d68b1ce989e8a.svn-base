package com.xwtech.xwecp.service.logic.invocation;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.CommunicateException;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY040094Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * QRY040094_获取随机验证码
 * 
 * @author Tkk
 * 
 */
public class GetCheckCodeInvocation extends BaseInvocation implements ILogicalService
{
    
    private static final Logger logger = LoggerFactory.getLogger(GetCheckCodeInvocation.class);
    
    /**
     * 手机号
     */
    private static final String PHONE_NUM = "phoneNum";
    
    
    /**
     * 请求Boss的接口
     */
    private static final String REQUEST_BOSSTELETEXT = "cc_getCheckCode_400052";
    
    private static final String META_QRY040094 = "QRY040094";
    
    /**
     * 新大陆提供的密钥，需要每两位转成1个字节
     */
    private static byte[] BOSS_SECRET_KEY = {0x0b, 0x33, (byte)0xe7, (byte)0xb2, 0x51, 0x0d, 0x75, (byte)0xc3, 0x4e,
        (byte)0xdd, (byte)0x3b, (byte)0x51, 0x24, 0x36, (byte)0xa8, (byte)0x28, 0x0b, 0x33, (byte)0xe7, (byte)0xb2,
        0x51, 0x0d, 0x75, (byte)0xc3};
    
    private BossTeletextUtil bossTeletextUtil = null;
    
    private IRemote remote = null;
    
    private WellFormedDAO wellFormedDAO = null;
    
    public GetCheckCodeInvocation()
    {
        ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
        this.bossTeletextUtil = (BossTeletextUtil)(springCtx.getBean("bossTeletextUtil"));
        this.remote = (IRemote)(springCtx.getBean("bossRemote"));
        this.wellFormedDAO = (WellFormedDAO)(springCtx.getBean("wellFormedDAO"));
    }
    
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config,
        List<RequestParameter> params)
    {
        QRY040094Result result = new QRY040094Result();
        result.setResultCode(LOGIC_SUCESS);
        result.setErrorMessage("");
        // for (RequestParameter param : params)
        // {
        // // 如果为密码
        // if (PASSWORD.equals(param.getParameterName()))
        // {
        // // 需要加密
        // String password = (String)param.getParameterValue();
        // password = DESEncrypt.desString(password, BOSS_SECRET_KEY);
        // param.setParameterValue(password);
        // }
        // }
        String reqXml = bossTeletextUtil.mergeTeletext(REQUEST_BOSSTELETEXT, params);
        try
        {
            String rspXml = (String)this.remote.callRemote(new StringTeletext(reqXml, accessId, REQUEST_BOSSTELETEXT,
                this.generateCity(params)));
            
            if (StringUtils.isEmpty(rspXml) || rspXml.getBytes().length < 120)
            {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            
            String errCode = new String(rspXml.getBytes(), 66, 4);
            String errDesc = new String(rspXml.getBytes(), 70, 42);
            result.setResultCode(BOSS_SUCCESS.equals(errCode) ? LOGIC_SUCESS : LOGIC_ERROR);
            result.setBossCode(errCode);
            result.setErrorMessage(StringUtils.trim(errDesc));
            if(BOSS_SUCCESS.equals(errCode)){
            	result.setCheckCode(new String(rspXml.getBytes(),120,6));
            }
            
            // //解析boss返回的报文
            // parseResponseXml(rspXml, result);
        }
        catch (CommunicateException e)
        {
            logger.error("", e);
        }
        catch (Exception e)
        {
            logger.error("", e);
        }
        return result;
    }
    
   
}
