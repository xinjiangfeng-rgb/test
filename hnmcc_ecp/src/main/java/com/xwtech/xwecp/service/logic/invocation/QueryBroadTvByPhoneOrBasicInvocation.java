package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.BroadPhoneBasic;
import com.xwtech.xwecp.service.logic.pojo.QRY190108Result;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class QueryBroadTvByPhoneOrBasicInvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_190108";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY190108Result result = new QRY190108Result();
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
                List<BroadPhoneBasic> resultList = new ArrayList<>();
                for (int i = 0; i < deal.size(); i++) {
                    JSONObject orderMarketObj = (JSONObject) deal.get(i);
                    BroadPhoneBasic broadPhoneBasic = new BroadPhoneBasic();
                    String gqdsFee = orderMarketObj.getString("GQDSFEE");
                    String kdFee = orderMarketObj.getString("KDFEE");
                    String info = orderMarketObj.getString("INFO");
                    String flag = orderMarketObj.getString("FLAG");
                    broadPhoneBasic.setFlag(flag);
                    broadPhoneBasic.setKdFee(kdFee);
                    broadPhoneBasic.setGqdsFee(gqdsFee);
                    broadPhoneBasic.setInfo(info);
                    resultList.add(broadPhoneBasic);
                }
                result.setRespCode(respCode);
                result.setBroadPhoneBasics(resultList);
                result.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());
            } else {
                result.setErrorMessage(respDesc);
            }

            if ("00000".equals(respCode)) {
                result.setResultCode(LOGIC_SUCESS);
            } else {
                result.setResultCode(LOGIC_ERROR);
            }
            if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            result.setBossCode(bossCode);
        } catch (Exception e) {
            logger.error("根据手机号码和基本套餐查询宽带、高清电视费用", e);
        }
        return result;
    }
}
