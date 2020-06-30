package com.xwtech.xwecp.service.logic.invocation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xwtech.xwecp.msg.RequestParameter;
import com.xwtech.xwecp.service.BaseServiceInvocationResult;
import com.xwtech.xwecp.service.ILogicalService;
import com.xwtech.xwecp.service.config.ServiceConfig;
import com.xwtech.xwecp.service.logic.pojo.BillResult;
import com.xwtech.xwecp.service.logic.pojo.PayResultList;
import com.xwtech.xwecp.service.logic.pojo.QRY420031Result;
import com.xwtech.xwecp.util.MessageJsonUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class QueryAccountStatementInvocation extends BaseInvocation implements ILogicalService {
    private String REQUEST_BOSSTELETEXT = "cc_420031";

    @Override
    public BaseServiceInvocationResult executeService(String accessId, ServiceConfig config, List<RequestParameter> params) {
        QRY420031Result result = new QRY420031Result();
        List<PayResultList> list = new ArrayList<PayResultList>();
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
                JSONArray billList = resultObj.getJSONArray("SO_MEMBER_DEAL");
                List<BillResult> billResultList = new ArrayList<>();
                for (int i = 0; i < billList.size(); i++) {
                    if (billList != null) {
                        JSONObject billObj = (JSONObject) billList.get(i);
                        BillResult billResult = new BillResult();
                        if (billObj != null) {
                            String resultJSON = billObj.getString("RESULTJSON");
                            String resultdesc = billObj.getString("RESULTDESC");
                            String resultcode = billObj.getString("RESULTCODE");
                            billResult.setResultJSON(resultJSON);
                            billResult.setResultCode(resultcode);
                            billResult.setResultDesc(resultdesc);
                            billResultList.add(billResult);
                        }
                    }
                }
                result.setBillResults(billResultList);
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
            logger.error("账务账单查询接口", e);
        }
        return result;
    }
}
