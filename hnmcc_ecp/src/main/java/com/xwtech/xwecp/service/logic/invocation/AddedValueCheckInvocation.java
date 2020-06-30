package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.AddValueCheckResult;
import com.xwtech.xwecp.service.logic.pojo.DEL200604Result;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AddedValueCheckInvocation extends BaseInvocation implements ILogicalService {

    private String REQUEST_BOSSTELETEXT = "cc_200604";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        DEL200604Result result = new DEL200604Result();
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
            if(resultObj!=null&&!"".equals(resultObj)){
                JSONArray deal = resultObj.getJSONArray("SO_MEMBER_DEAL");
                List<AddValueCheckResult> resultList = new ArrayList<>();
                for (int i = 0; i < deal.size(); i++) {
                    JSONObject addValueCheckObj = (JSONObject) deal.get(i);
                    AddValueCheckResult addValueCheckResult = new AddValueCheckResult();
                    String offerId = addValueCheckObj.getString("OFFER_ID");
                    String canSel = addValueCheckObj.getString("CAN_SEL");
                    String errMsg = addValueCheckObj.getString("ERR_MSG");
                    String selFee = addValueCheckObj.getString("SEL_FEE");
                    String offerCode = addValueCheckObj.getString("OFFER_CODE");
                    addValueCheckResult.setOfferId(offerId);
                    addValueCheckResult.setCanSel(canSel);
                    addValueCheckResult.setErrMsg(errMsg);
                    addValueCheckResult.setSelFee(selFee);
                    addValueCheckResult.setOfferCode(offerCode);
                    resultList.add(addValueCheckResult);
                }
                result.setAddValueCheckResults(resultList);
                result.setRespCode(respCode);
                result.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());
            }else{
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
            logger.error("订购营销活动", e);
        }
        return result;
    }
//    {"accessId":"S00120200529111628100000093","bossCode":"1","errorCode":"1","errorMessage":"调用远程服务失败,编码:88000012,描述:该产品用户已经订购过，不能再次订购"}
}
