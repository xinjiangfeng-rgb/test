package com.xwtech.xwecp.flow.works.chains.nodes;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.XWECPApp;
import com.xwtech.xwecp.dao.WellFormedDAO;
import com.xwtech.xwecp.flow.works.chains.AbstractFlowControl;
import com.xwtech.xwecp.logs.WhiteDaoImpl;
import com.xwtech.xwecp.msg.MessageHelper;
import com.xwtech.xwecp.msg.RequestData;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.msg.ServiceMessage;
import com.xwtech.xwecp.pojo.ChannelInfo;
import com.xwtech.xwecp.service.ServiceExecutor;
import com.xwtech.xwecp.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class CheckWhiteNode extends AbstractFlowControl {
    private WellFormedDAO wellFormedDAO;
    private static final Logger logger = LoggerFactory.getLogger(CheckWhiteNode.class);
    @Override
    public boolean execute(ServiceExecutor serviceExecutor, ServiceMessage inputMessage, String clientIp, ChannelInfo channelInfo, Object o) {
        ApplicationContext springCtx = XWECPApp.SPRING_CONTEXT;
        this.wellFormedDAO = (WellFormedDAO) (springCtx.getBean("wellFormedDAO"));
        WhiteDaoImpl whiteDaoImpl = (WhiteDaoImpl) (springCtx.getBean("whiteDao"));
        RequestData data = (RequestData) inputMessage.getData();

        List<RequestParameter> params = data.getParams();
        for (RequestParameter parameter : params) {
            String parameterName = parameter.getParameterName();
            logger.info("parameterName==="+parameterName);

            Object obj= parameter.getParameterValue();

            if(obj instanceof  String){
                String parameterValue = (String) parameter.getParameterValue();
                logger.info("parameterValue==="+parameterValue);
                boolean isMobile = StringUtil.isMobile(parameterValue);
                if (isMobile) {
                    int count = whiteDaoImpl.getWhitenumber(parameterValue, "0");
                    logger.info("count==="+count);
                    if (count >0) {
                        logger.info("count===true");
                        return true;
                    } else {
                        logger.info("count===false");
                        return false;
                    }
                }
            }else if(obj instanceof JSONObject){
                JSONObject parameterValue = (JSONObject) parameter.getParameterValue();
                logger.info("parameterValue==="+parameterValue.toJSONString());
                String msisdn = parameterValue.getString("msisdn");
                if(msisdn!=null){
                    boolean isMobile = StringUtil.isMobile(msisdn);
                    if (isMobile) {
                        int count = whiteDaoImpl.getWhitenumber(msisdn, "0");
                        if (count >0) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public ServiceMessage failed(MessageHelper messageHelper, ServiceMessage inputMessage, Object o) {
        return messageHelper.checkWhiteResponseMessage(inputMessage);
    }
}
