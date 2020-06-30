package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.CardFailure;
import com.xwtech.xwecp.service.logic.pojo.QRY191212Result;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CardFailureCheckInvocation extends BaseInvocation implements ILogicalService {

    private String REQUEST_BOSSTELETEXT = "cc_191212";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY191212Result result = new QRY191212Result();
        String rspXml = "";
        try {
            BaseJsonQueryUtils util = new BaseJsonQueryUtils();
            util.setConfig(REQUEST_BOSSTELETEXT);
            rspXml = util.getResponseXML(accessId, config, params);
            util.distroy();
            logger.info("******** Boss返回数据为*****　" + rspXml);
            if (StringUtils.isEmpty(rspXml)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            MessageJsonUtil bossJson = MessageJsonUtil.getInstance((String) rspXml);
            String bossCode = bossJson.getBossCode();
            String bossDesc = bossJson.getBossDesc();
            logger.info("bossCode" + bossCode);
            logger.info("bossDesc" + bossDesc);
            JSONObject resultObj = JSONObject.parseObject(rspXml).getJSONObject("result");
            String respDesc = JSONObject.parseObject(rspXml).getString("respDesc");
            String respCode = JSONObject.parseObject(rspXml).getString("respCode");
            if (resultObj != null && !"".equals(resultObj)) {
                JSONArray deal = resultObj.getJSONArray("SO_MEMBER_DEAL");
                List<CardFailure> resultList = new ArrayList<>();
                for (int i = 0; i < deal.size(); i++) {
                    JSONObject cardFailureObj = (JSONObject) deal.get(i);
                    CardFailure cardFailure = new CardFailure();
                    String retFlag = cardFailureObj.getString("RET_FLAG");
                    String retMessage = cardFailureObj.getString("RET_MESSAGE");
                    cardFailure.setRetFlag(retFlag);
                    cardFailure.setRetMessage(retMessage);
                    resultList.add(cardFailure);
                }
                result.setCardFailures(resultList);
                result.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());
            } else {
                result.setErrorMessage(respDesc);
            }
            if("00000".equals(respCode)){
                result.setResultCode(LOGIC_SUCESS);
            }else {
                result.setResultCode(LOGIC_ERROR);
            }
            if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }

            result.setBossCode(bossCode);

        } catch (Exception e) {
            logger.error("卡券类活动失效校验功能", e);
        }
        return result;
    }
}
