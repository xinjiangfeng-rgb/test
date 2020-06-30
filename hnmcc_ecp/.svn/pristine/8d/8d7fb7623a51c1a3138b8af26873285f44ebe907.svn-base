package com.xwtech.xwecp.service.logic.resolver;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ITeletextResolver;
import com.xwtech.xwecp.service.logic.pojo.QRY040001Result;
import com.xwtech.xwecp.util.ECPConstants;
import com.xwtech.xwecp.util.MessageUtil;
import com.xwtech.xwecp.util.MessageUtil.Message;

public class QueryUserInfoResolver implements ITeletextResolver
{
    private static final Logger logger = LoggerFactory.getLogger(QueryUserInfoResolver.class);

    public QueryUserInfoResolver()
    {
    }

    public void resolve(BaseServiceInvocationResult result, Object bossResponse, List<RequestParameter> param)
        throws Exception
    {
        QRY040001Result res = (QRY040001Result)result;
        try
        {
            String respMsg = (String)bossResponse;

            Message message = MessageUtil.parse(respMsg);

            res.setResultCode("0000".equals(message.getHead().getCode()) ? MessageUtil.LOGIC_SUCESS
                : MessageUtil.LOGIC_ERROR);
            res.setBossCode(message.getHead().getCode());
            res.setErrorMessage(message.getHead().getDesc());
            res.setUser_name("匿名");
            if (StringUtils.isNotEmpty(res.getAreacode()))
            {
                res.setAreacode(ECPConstants.AREA_CODES.get(res.getAreacode()));
            }
        }
        catch (Exception e)
        {
            logger.error("", e);
        }
    }

}