package com.xwtech.xwecp.service.logic.invocation;

import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.communication.CommunicateException;
import com.xwtech.xwecp.communication.IRemote;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.BalanceDetail;
import com.xwtech.xwecp.service.logic.pojo.QRY010021Result;
import com.xwtech.xwecp.service.server.DefaultServiceImpl.StringTeletext;
import com.xwtech.xwecp.teletext.BossTeletextUtil;
import com.xwtech.xwecp.util.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询近6个月消费
 * @author Administrator
 */
public class QueryBillBalInvocation extends BaseInvocation implements ILogicalService {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(QueryBillBalInvocation.class);
	private BossTeletextUtil bossTeletextUtil;
	private IRemote remote;
	private WellFormedDAO wellFormedDAO;
	private String REQUEST_BOSSTELETEXT = "ac_acquerybillbal_69";
	
	public QueryBillBalInvocation() {
		ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
		this.bossTeletextUtil = (BossTeletextUtil) (springCtx.getBean("bossTeletextUtil"));
		this.remote = (IRemote) (springCtx.getBean("bossRemote"));
		this.wellFormedDAO = (WellFormedDAO) (springCtx.getBean("wellFormedDAO"));
	}

	@SuppressWarnings("unchecked")
	public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
		QRY010021Result result = new QRY010021Result();
		
		List<BalanceDetail> balanceDetails = new ArrayList<BalanceDetail>();
		BalanceDetail balanceDetail;
		result.setResultCode(LOGIC_SUCESS);
        result.setErrorMessage("");
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
            String errCode = MessageUtil.parse(rspXml).getHead().getCode();
            String errDesc = MessageUtil.parse(rspXml).getHead().getDesc();
            
            List<String[]> resList = MessageUtil.parse(rspXml).getBody().asList();
            for(String[] temp:resList){
            	balanceDetail = new BalanceDetail();
            	balanceDetail.setFeeName(temp[0]);//月份
            	balanceDetail.setFee(temp[1]);//消费金额
            	balanceDetails.add(balanceDetail);
            }
            result.setBalanceDetails(balanceDetails);
            result.setResultCode(BOSS_SUCCESS.equals(errCode) ? LOGIC_SUCESS : LOGIC_ERROR);
            result.setBossCode(errCode);
            result.setErrorMessage(errDesc);
            
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
	
	public BossTeletextUtil getBossTeletextUtil() {
		return bossTeletextUtil;
	}

	public void setBossTeletextUtil(BossTeletextUtil bossTeletextUtil) {
		this.bossTeletextUtil = bossTeletextUtil;
	}

}
