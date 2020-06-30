package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.QRY170805Result;
import com.xwtech.xwecp.service.logic.pojo.TaxpayerIdentification;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class QueryTaxpayeridentificationCodeInvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_170805";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY170805Result result = new QRY170805Result();
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
            if (resultObj != null && !"".equals(resultObj)) {
                JSONArray resultList = resultObj.getJSONArray("SO_MEMBER_DEAL");
                List<TaxpayerIdentification> axpayerIdentificationList = new ArrayList<>();
                for (int i = 0; i < resultList.size(); i++) {
                    JSONObject obj = (JSONObject) resultList.get(i);
                    TaxpayerIdentification taxpayerIdentification = new TaxpayerIdentification();
                    String ghfNsrmc = obj.getString("GHFNSRMC");
                    String ghfNsrsbh = obj.getString("GHFNSRSBH");
                    String ghfDz = obj.getString("GHFDZ");
                    String ghfDhhm = obj.getString("GHFDHHM");
                    String ghfkhh = obj.getString("GHFKHH");
                    String ghfYhzh = obj.getString("GHFYHZH");
                    taxpayerIdentification.setGhfNsrmc(ghfNsrmc);
                    taxpayerIdentification.setGhfNsrsbh(ghfNsrsbh);
                    taxpayerIdentification.setGhfDz(ghfDz);
                    taxpayerIdentification.setGhfDhhm(ghfDhhm);
                    taxpayerIdentification.setGhfkhh(ghfkhh);
                    taxpayerIdentification.setGhfYhzh(ghfYhzh);
                    axpayerIdentificationList.add(taxpayerIdentification);
                }
                result.setTaxpayerIdentificationList(axpayerIdentificationList);
                result.setErrorMessage(StringUtils.isNotBlank(bossDesc) ? bossDesc : bossJson.getStringResult());
            } else {
                result.setErrorMessage(respDesc);
            }
            if (!MessageJsonUtil.BOSS_CODE.equals(bossCode)) {
                result.setBossCode(LOGIC_ERROR);
                result.setErrorCode(LOGIC_ERROR);
                return result;
            }
            result.setBossCode(bossCode);
        } catch (Exception e) {
            logger.error("查询纳税人识别码等信息", e);
        }
        return result;
    }
}
