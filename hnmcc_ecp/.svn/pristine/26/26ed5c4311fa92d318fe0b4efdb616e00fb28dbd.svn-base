package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY931931Result;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class QueryUserMailBoxPushInvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_931931";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY931931Result result = new QRY931931Result();
        String rspXml = "";
        try {
            BaseJsonQueryUtils util = new BaseJsonQueryUtils();
            util.setConfig(REQUEST_BOSSTELETEXT);
            rspXml = util.getResponseXML(accessId, config, params);
            util.distroy();
            logger.info("******** Boss邮箱推送返回数据为*****　" + rspXml);
            if (StringUtils.isEmpty(rspXml) ) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) rspXml);
            String bossCode = bossJson.getBossCode();
            String bossDesc = bossJson.getBossDesc();
            logger.info("bossCode"+bossCode);
            logger.info("bossDesc"+bossDesc);
            JSONObject obj = JSONObject.parseObject(rspXml).getJSONObject("result");
            String soMemberDeal = obj.getString("SO_MEMBER_DEAL");
            result.setSoMemberDeal(soMemberDeal);
            if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)){
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            result.setBossCode(bossCode);
            result.setErrorMessage(StringUtils.isNotBlank(bossDesc)?bossDesc:bossJson.getStringResult());
        } catch (Exception e) {
            logger.error("查询用户详单并推送到对应邮箱error", e);
        }
        return result;
    }
}
